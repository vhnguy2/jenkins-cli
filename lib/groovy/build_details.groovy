jenkins = jenkins.model.Jenkins.instance

if(this.args.length != 2) {
  println("build_details.groovy needs 1 parameter (i.e. job, build number)")
  return
}

jobName = this.args[0]
buildNumber = Integer.parseInt(this.args[1])

println(jobName + " #" + buildNumber)

jenkins.items.each {
  job ->
    if( job.isBuildable() && job.name.equals( jobName ) ) {
      def build = job.getBuildByNumber( buildNumber )
      // Start date
      println("  Built " + build.getTimestampString() + " ago")
      // Duration
      println("  Duration: " + build.getDurationString())
      // Committers
      println("  Potential Culprits:")
      def culprits = build.getCulprits()
      for(String c : culprits) {
        println("      + " + c)
      }
      // SCM changes
      println("  Changeset: ")
      def changeSet = build.getChangeSet()
      def items = changeSet.getItems()
      for(int i = 0 ; i < items.length; i++) {
        gitChangeSet = items[i]
        author = gitChangeSet.getAuthorName()
        comment = gitChangeSet.getComment().replaceAll("\\n", " ")
        rev = gitChangeSet.getRevision()
        paths = gitChangeSet.getPaths()
        println("      " + (i+1) + ". " + comment)
        println("          Commit: " + rev + " by (" + author + ")")
        for(hudson.plugins.git.GitChangeSet.Path path : paths) {
          editType = path.getEditType()
          if(editType == hudson.scm.EditType.ADD) {
            println("             +  " + path.getPath())
          } else if(editType == hudson.scm.EditType.DELETE) {
            println("             -  " + path.getPath())
          } else if(editType == hudson.scm.EditType.EDIT) {
            println("             E  " + path.getPath())
          }
        }
      }

      return
    }
}
