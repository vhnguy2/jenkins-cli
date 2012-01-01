jenkins = jenkins.model.Jenkins.instance

if(this.args.length != 1) {
  println("job_details.groovy needs exactly 1 parameter (i.e. job)")
  return
}

targetJobName = this.args[0]

allItems = jenkins.items
activeJobs = allItems.each {
  job ->
    if(job.isBuildable() && job.name.equals( targetJobName )) {
      def isQueued = job.isInQueue()
      def isBuilding = job.isBuilding()
      def status = "UNKNOWN"
      if( !isQueued && !isBuilding && job.lastBuild != null) {
        status = job.lastBuild.result
      } else if( isQueued ) {
        status = "Queued"
      } else if( isBuilding ) {
        status = "In Progress"
      }

      def lastSuccess = "N/A"
      now = System.currentTimeMillis()
      if(job.lastSuccessfulBuild != null) {
        jobStartTime = job.lastSuccessfulBuild.timestamp.time.getTime()
        lastSuccess = (now - jobStartTime) / 1000 / 60    // time difference in minutes
      }
      
      def lastFailed = "N/A"
      if(job.lastFailedBuild != null) {
        jobStartTime = job.lastFailedBuild.timestamp.time.getTime()
        lastFailed = (now - jobStartTime) / 1000 / 60     // time difference in minutes
      }

      def recipients = ""
      publisherList = job.getPublishersList()
      publisherList.each {
        publisher ->
        if(publisher.class == hudson.plugins.emailext.ExtendedEmailPublisher) {
          recipients = publisher.recipientList
        }
      }

      println("Job -> " + job.name)
      println("  Status: " + status)
      println("  Last Successful Build: " + (int)lastSuccess + " minutes ago")
      println("  Last Failed Build: " + (int)lastFailed + " minutes ago")
      println("  Label Expression: " + job.getAssignedLabelString())
      println("  Email Recipients: " + recipients)
    }
}



