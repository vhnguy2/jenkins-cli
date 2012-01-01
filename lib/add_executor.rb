#!/usr/bin/env ruby

require "trollop"

DEFAULT_SSH_USER = "role-c3"
DEFAULT_JENKINS_URL = "http://localhost:8080"

OPTIONS = Trollop::options do
  opt :name, "Slave name", :default => nil, :type => String
  opt :user, "Jenkins username", :default => nil, :type => String
  opt :password, "Jenkins password", :default => nil, :type => String
  opt :ssh_user, "SSH username", :default => DEFAULT_SSH_USER
  opt :jenkins_url, "Jenkins main URL", :default => DEFAULT_JENKINS_URL
  opt :mode, "Jenkins slave mode", :default => "exclusive"
  opt :slave_url, "Slave URL", :default => nil, :type => String
  opt :v, "Verbose", :default => false
end

p OPTIONS

Trollop::die :slave_url, "Must be set with a protocol" if OPTIONS[:slave_url].nil?
Trollop::die :name, "Must be set" if OPTIONS[:name].nil?
Trollop::die :mode, "Must be either exclusive or normal" unless ["exclusive", "normal"].include?(OPTIONS[:mode])

jenkins_username = "--username #{OPTIONS[:user]}"
jenkins_username = "" if OPTIONS[:user].nil?
jenkins_password = "--password #{OPTIONS[:password]}"
jenkins_password = "" if OPTIONS[:password].nil?

groovy_params = "#{OPTIONS[:name]} #{OPTIONS[:mode]} #{OPTIONS[:slave_url]} #{OPTIONS[:ssh_user]}"

base_dir = File.dirname(__FILE__)

cmd = "java -jar #{base_dir}/jenkins-cli.jar -s #{OPTIONS[:jenkins_url]} groovy #{jenkins_username} #{jenkins_password} #{base_dir}/groovy/add_slave.groovy #{groovy_params}"

puts "Command: #{cmd}" if OPTIONS[:v]
system( cmd )

