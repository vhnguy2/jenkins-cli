#!/usr/bin/env ruby

require "trollop"

DEFAULT_JENKINS_URL = "http://localhost:8080"

# TODO(viet): Add the ability to search jobs with prefixes.
# TODO(viet): Add the ability to show more detail of specific jobs (e.g. labels, repos, lastSuccess, jobStatus)

OPTIONS = Trollop::options do
  opt :jenkins_url, "Jenkins main URL", :default => DEFAULT_JENKINS_URL
  opt :user, "Jenkins username", :default => nil, :type => String
  opt :password, "Jenkins password", :default => nil, :type => String  
end

jenkins_username = "--username #{OPTIONS[:user]}"
jenkins_username = "" if OPTIONS[:user].nil?
jenkins_password = "--password #{OPTIONS[:password]}"
jenkins_password = "" if OPTIONS[:password].nil?

base_dir = File.dirname(__FILE__)

cmd = "java -jar #{base_dir}/jenkins-cli.jar -s #{OPTIONS[:jenkins_url]} groovy #{jenkins_username} #{jenkins_password} #{base_dir}/groovy/list_failing_jobs.groovy"

system( cmd )
