require 'bundler'
Bundler::GemHelper.install_tasks

load "#{ENV['REPO_ROOT']}/ooyala/shared_lib/ooyala_gems/ooyala_gems.rake"

namespace :jenkins_cli do
  desc "Push latest jenkins-cli gem to gems.sv2"
  task :push_gem do
    gem_to_push = nil
    Dir.glob('jenkins-cli*.gem').each do |f|
      gem_to_push = f
    end
    puts `rake gem:push #{gem_to_push}` if gem_to_push
  end
end
