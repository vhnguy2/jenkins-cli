import hudson.model.*
import hudson.slaves.*
import hudson.plugins.sshslaves.SSHLauncher

if(this.args.length != 4) {
  println("Usage: add_slave.groovy <slave_name> <exclusive/normal> <slave_url> <user>")
  return
}

def slave_name = this.args[0]
def mode = this.args[1].equals("exclusive") ? Node.Mode.EXCLUSIVE : Node.Mode.NORMAL
def slave_url = this.args[2]
def user = this.args[3]

jenkins = jenkins.model.Jenkins.instance

def slaveExists = false
def slaves = jenkins.getNodes()
def currSlave = null
slaves.each {
  slave ->
    if(slave.getNodeName().equals( slave_name )) {
      slaveExists = true
      currSlave = slave
      jenkins.removeNode(slave)
      return
    }
}

println("Attempting to modify/add Jenkins node: " + slave_name )
println(" + Slave URL:  " + slave_url)
println(" + Slave Name: " + slave_name)
println(" + Mode:       " + mode)
println(" + User:       " + user)

sshLauncher = new SSHLauncher(
                    slave_url,
                    22,
                    user,
                    "",
                    "",
                    "")

if( !slaveExists ) {
  // Preparing a new Node to add
  def node = new DumbSlave(
                slave_name,
                "",
                "/var/lib/jenkins",
                "1",
                mode,
                "",
                sshLauncher,
                RetentionStrategy.Always.INSTANCE,
                new ArrayList<NodeProperty>())

  jenkins.addNode( node )
} else {
  // Modifying an existing Node
  currSlave.setLauncher( sshLauncher )
  currSlave.setMode( mode )
  currSlave.setNodeName( slave_name )
  // TODO
  jenkins.addNode( currSlave )
}
