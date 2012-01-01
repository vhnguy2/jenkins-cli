jenkins = jenkins.model.Jenkins.instance

def jobs = jenkins.items

def building = new ArrayList()
def queued = new ArrayList()

def queue = jenkins.getQueue()
def itemsInQueue = queue.getItems()

for(int i = 0 ; i < jobs.size() ; i++) {
  if(jobs[i].isBuildable() && jobs[i].isBuilding()) {
    building.add(jobs[i].name)
  } else if(jobs[i].isBuildable() && jobs[i].isInQueue()) {
    queued.add(jobs[i].name)
  }
}

println("Items currently building:")
for(int i = 0 ; i < building.size();  i++) {
  println("  + " + building[i])
}
println()
println("Items in the build queue:")
for(int i = itemsInQueue.length - 1 ; i >= 0; i--) {
  println("  + " + itemsInQueue[i].task.getName())
}


