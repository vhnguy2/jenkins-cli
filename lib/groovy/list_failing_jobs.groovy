jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items
activeJobs = allItems.findAll {
  job ->
    job.isBuildable()
}

failedRuns = activeJobs.findAll {
  job ->    
    job.lastBuild != null && (job.lastBuild.result == hudson.model.Result.FAILURE || job.lastBuild.result == hudson.model.Result.UNSTABLE)
}

println("\nJobs:")
failedRuns.each {
  run ->
    println("  + " + run.name)
}

println("\n" + failedRuns.size + " failed Jenkins jobs")
