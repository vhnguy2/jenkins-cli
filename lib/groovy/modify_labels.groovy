jenkins = jenkins.model.Jenkins.instance

if(this.args.length != 2) {
  println("modify_labels.groovy needs exactly 2 parameter (i.e. <job> <label_string>)")
  return
}

targetJobName = this.args[0]
labelStr = this.args[1]

allItems = jenkins.items
activeJobs = allItems.each {
  job ->
    if(job.isBuildable() && job.name.equals( targetJobName )) {
      def labelAtom = new hudson.model.labels.LabelAtom(labelStr)
      job.setAssignedLabel( labelAtom )
      println("Raw Input: " + labelStr)
      println("Input: " + labelAtom.getExpression())
      println("Assigned label string is " + job.getAssignedLabelString())
    }
}



