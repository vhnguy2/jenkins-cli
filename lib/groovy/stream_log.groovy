jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items

if(this.args.length != 1) {
  println("job_details.groovy needs exactly 1 parameter (i.e. job)")
    return
    }

targetJobName = this.args[0]

allItems.each {
  job ->
    if(job.isBuildable() && job.name.equals( targetJobName )) {
      inputStream = job.getLastBuild().getLogInputStream()
      println(inputStream)
      def c = ' '
      def firstTime = true
      while( firstTime || job.isBuilding() ) {
        firstTime = false
        while( (c = inputStream.read()) != -1 ) {
          print((char)c)
        }
        Thread.sleep(1000)
      }
    }
}

println()
println("*** END OF LOG ***")



