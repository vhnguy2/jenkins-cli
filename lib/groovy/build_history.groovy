jenkins = jenkins.model.Jenkins.instance

if(this.args.length < 1) {
  println("build_history.groovy needs at least 1 parameter (i.e. job, optional is limit)")
  return
}

jobName = this.args[0]
numRunsToShow = 10
if(this.args.length == 2) {
  numRunsToShow = Integer.parseInt(this.args[1])
}

println(jobName)

jenkins.items.each {
  job ->
    if( job.isBuildable() && job.name.equals( jobName ) ) {
      def builds = job.getBuildsAsMap().values()
      int i = 0
      for(hudson.model.Run build : builds) {
        println("  + " + build.number + " => " + build.getResult())
        i++
        if(i == numRunsToShow) {
          break
        }
      }
    }
}
