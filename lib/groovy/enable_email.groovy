import hudson.plugins.emailext.*

jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items

if(this.args.length != 2) {
  println("enable_email.groovy requires exactly 2 paramters: <projects> <emails>")
  return
}

def projects = this.args[0].split(" ")
def emails = this.args[1]
def toAdd = new HashSet()

for(int i = 0 ; i < projects.length ; i++) {
  toAdd.add(projects[i].toLowerCase())
}

allItems.each {
  run ->
  if(toAdd.contains(run.name.toLowerCase())) {
    def alreadyEnabled = false
    list = run.getPublishersList()
    list.each {
      publisher ->
      if(publisher.class == hudson.plugins.emailext.ExtendedEmailPublisher) {
        publisher.recipientList = emails
        alreadyEnabled = true
        println(run.name.toLowerCase() + " was updated with new email recipients.")
      }
    }
    if( !alreadyEnabled ) {
      // Set up the email publisher
      emailPublisher = new hudson.plugins.emailext.ExtendedEmailPublisher()
      emailPublisher.defaultSubject = '$DEFAULT_SUBJECT'
      emailPublisher.defaultContent = '$DEFAULT_CONTENT'
      emailPublisher.recipientList = emails

      triggers = emailPublisher.getEmailTriggers().clear()
      emailType = new hudson.plugins.emailext.EmailType()
      emailType.setSendToDevelopers(true)
      emailType.setSendToRecipientList(true)
      emailType.setSubject(hudson.plugins.emailext.ExtendedEmailPublisher.PROJECT_DEFAULT_SUBJECT_TEXT)
      emailType.setBody(hudson.plugins.emailext.ExtendedEmailPublisher.PROJECT_DEFAULT_BODY_TEXT)

      failureTrigger = new hudson.plugins.emailext.plugins.trigger.FailureTrigger()
      failureTrigger.setEmail(emailType)

      unstableTrigger = new hudson.plugins.emailext.plugins.trigger.UnstableTrigger()
      unstableTrigger.setEmail(emailType)

      fixedTrigger = new hudson.plugins.emailext.plugins.trigger.FixedTrigger()
      fixedTrigger.setEmail(emailType)

      emailPublisher.addEmailTriggerType( failureTrigger.getDescriptor() )
      emailPublisher.addEmailTriggerType( unstableTrigger.getDescriptor() )
      emailPublisher.addEmailTriggerType( fixedTrigger.getDescriptor() )
      emailPublisher.getConfiguredTriggers().add( failureTrigger )
      emailPublisher.getConfiguredTriggers().add( unstableTrigger )
      emailPublisher.getConfiguredTriggers().add( fixedTrigger )
     
      // Add the email publisher
      list.add(emailPublisher)

      println("Enabled emails for " + run.name.toLowerCase())
    }
  }
}



