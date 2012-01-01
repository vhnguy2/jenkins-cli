jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items

def toRemove = new HashSet()

for(int i = 0 ; i < this.args.length; i++) {
  toRemove.add(this.args[i].toLowerCase())
}

allItems.each {
  run ->
  list = run.getPublishersList()
  list.each {
    publisher ->
    if(publisher.class == hudson.plugins.emailext.ExtendedEmailPublisher) {
      if(toRemove.contains(run.name.toLowerCase())) {
        println("Disabled email for " + run.name)
        list.remove(publisher)
        return
      }
    }
  }
}



