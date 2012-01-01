import hudson.plugins.emailext.*

jenkins = jenkins.model.Jenkins.instance

allItems = jenkins.items

allItems.each {
  run ->
  list = run.getPublishersList()
  list.each {
    publisher ->
    if(publisher.class == hudson.plugins.emailext.ExtendedEmailPublisher) {
      println(publisher)

      emailType = new hudson.plugins.emailext.EmailType()
      emailType.setSendToDevelopers(true)
      emailType.setSendToRecipientList(true)

      unstableTrigger = new hudson.plugins.emailext.plugins.trigger.UnstableTrigger()
      unstableTrigger.setEmail(emailType)

      publisher.getConfiguredTriggers().add( unstableTrigger )

      triggers = publisher.getConfiguredTriggers()
      triggers.each {
        trigger ->
          email = trigger.getEmail()
          println("Send to Developers: " + email.getSendToDevelopers())

      }
    }
  }
}



