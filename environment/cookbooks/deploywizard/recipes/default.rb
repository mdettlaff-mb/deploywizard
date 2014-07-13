#
# Cookbook Name:: deploywizard
# Recipe:: default
#
# Copyright 2014, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

maven_repository = 'http://10.0.2.2:8081/nexus/content/repositories/dwexample-snapshot/'

deploywizard 'dropwizard-example' do
  group_id 'io.dropwizard'
  version '0.7.1-SNAPSHOT'
  repository maven_repository
  configuration_template 'services/dropwizard-example.yml.erb'
end

deploywizard 'dropwizard-example2' do
  artifact_id 'dropwizard-example'
  group_id 'io.dropwizard'
  version '0.7.1-SNAPSHOT'
  repository maven_repository
  configuration_template 'services/dropwizard-example2.yml.erb'
end
