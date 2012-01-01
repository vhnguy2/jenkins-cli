jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items

println("Available Jenkins jobs:")

allItems.each {
  run ->
  println("  + " + run.name)
}


