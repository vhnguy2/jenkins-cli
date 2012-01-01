import hudson.plugins.sshslaves.SSHLauncher

jenkins = jenkins.model.Jenkins.instance

def nodes = jenkins.getNodes()

nodes.each {
  node ->
    println(node.getLauncher())
}
