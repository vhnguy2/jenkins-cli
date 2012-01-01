# -*- encoding: utf-8 -*-
$:.push File.expand_path("../lib", __FILE__)
require "jenkins-cli/version"

Gem::Specification.new do |s|
  s.name        = "jenkins-cli"
  s.version     = JenkinsCLI::VERSION
  s.platform    = Gem::Platform::RUBY
  s.authors     = ["Viet Nguyen"]
  s.email       = ["viet@ooyala.com"]
  s.homepage    = "http://www.ooyala.com"
  s.summary     = "This jenkins-cli gem is an internal tool to Ooyala for managing a Jenkins instance from the command"
  s.description = "Jenkins gem"

  s.rubyforge_project = "jenkins-cli"

  s.add_dependency("trollop")

  s.files         = `git ls-files`.split("\n")
  s.executables   = `git ls-files -- bin/*`.split("\n").map{ |f| File.basename(f) }
  s.require_paths = ["lib"]
end
