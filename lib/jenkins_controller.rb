class JenkinsController
  def self.build_history(jenkins_url, user, password, job, limit)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    limit = "--limit #{limit}" unless limit.nil?
    system "#{File.dirname(__FILE__)}/build_history.rb #{jenkins_url} #{user} #{password} #{job} #{limit}"
  end

  def self.build_details(jenkins_url, user, password, job, num) 
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    build_number = "--build-number #{num}" unless num.nil?
    system "#{File.dirname(__FILE__)}/build_details.rb #{jenkins_url} #{user} #{password} #{job} #{build_number}"
  end

  def self.log(jenkins_url, user, password, job) 
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    system "#{File.dirname(__FILE__)}/stream_log.rb #{jenkins_url} #{user} #{password} #{job}"
  end

  def self.grant_access(jenkins_url, user, password, grant_user)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    grant_user = "--grant-user #{grant_user}" unless grant_user.nil?
    system "#{File.dirname(__FILE__)}/grant_user.rb #{jenkins_url} #{user} #{password} #{grant_user}"
  end

  def self.job_labels(jenkins_url, user, password, job, labels)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    labels = "--labels '#{labels}'" unless labels.nil?
    system "#{File.dirname(__FILE__)}/job_labels.rb #{jenkins_url} #{user} #{password} #{job} #{labels}"
  end

  def self.queue(jenkins_url, user, password)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    system "#{File.dirname(__FILE__)}/display_queue.rb #{jenkins_url} #{user} #{password}"
  end

  def self.job_details(jenkins_url, user, password, job)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    system "#{File.dirname(__FILE__)}/job_details.rb #{jenkins_url} #{user} #{password} #{job}"
  end

  def self.list_failures(jenkins_url, user, password)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    system "#{File.dirname(__FILE__)}/list_failing_jobs.rb #{jenkins_url} #{user} #{password}"
  end

  def self.start_job(jenkins_url, user, password, job)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    job = "--job #{job}" unless job.nil?
    system "#{File.dirname(__FILE__)}/start_job.rb #{jenkins_url} #{user} #{password} #{job}"
  end

  def self.list_jobs(jenkins_url, user, password)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    system "#{File.dirname(__FILE__)}/list_jobs.rb #{jenkins_url} #{user} #{password}"
  end

  def self.disable_email(jenkins_url, user, password, projects)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    projects = "--projects '#{projects}'" unless projects.nil?
    system "#{File.dirname(__FILE__)}/disable_emails.rb #{jenkins_url} #{user} #{password} #{projects}"
  end

  def self.enable_email(jenkins_url, user, password, projects, emails)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    projects = "--projects '#{projects}'" unless projects.nil?
    emails = "--emails '#{emails}'" unless emails.nil?
    system "#{File.dirname(__FILE__)}/enable_emails.rb #{jenkins_url} #{user} #{password} #{projects} #{emails}"
  end

  def self.add_node(jenkins_url, user, password, name, ssh_user, mode, slave_url, v)
    jenkins_url = "--jenkins-url #{jenkins_url}" unless jenkins_url.nil?
    user = "--user #{user}" unless user.nil?
    password = "--password #{password}" unless password.nil?
    name = "--name #{name}" unless name.nil?
    ssh_user = "--ssh-user #{ssh_user}" unless ssh_user.nil?
    mode = "--mode #{mode}" unless mode.nil?
    slave_url = "--slave-url #{slave_url}" unless slave_url.nil?
    v = "--v #{v}" unless v.nil?
    system "#{File.dirname(__FILE__)}/add_executor.rb #{jenkins_url} #{user} #{password} #{name} #{ssh_user} #{mode} #{slave_url} #{v}"
  end
end
