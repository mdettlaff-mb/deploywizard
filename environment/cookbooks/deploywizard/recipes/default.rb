#
# Cookbook Name:: deploywizard
# Recipe:: default
#
# Copyright 2014, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

maven_repository = 'http://10.0.2.2:8081/nexus/content/repositories/dwexample-snapshot/'

deploywizard 'dw-example1' do
  group_id 'mdettlaff.deploywizard'
  version '1.0.0-SNAPSHOT'
  repository maven_repository
  configuration_template 'services/dropwizard-example.yml.erb'
end

deploywizard 'dw-example2' do
  group_id 'mdettlaff.deploywizard'
  version '1.0.0-SNAPSHOT'
  repository maven_repository
  configuration_template 'services/dropwizard-example2.yml.erb'
end
