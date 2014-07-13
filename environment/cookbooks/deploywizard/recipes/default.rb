#
# Cookbook Name:: deploywizard
# Recipe:: default
#
# Copyright 2014, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

deploywizard 'dropwizard-example' do
  group_id 'io.dropwizard'
  version '0.7.1-SNAPSHOT'
  repository 'http://10.0.2.2:8081/nexus/content/repositories/dwexample-snapshot/'
end

template "/opt/microservices/dropwizard-example/dropwizard-example.yml" do
  source 'services/dropwizard-example.yml.erb'
  owner node['deploywizard']['user']
end


deploywizard 'dropwizard-example2' do
  artifact_id 'dropwizard-example'
  group_id 'io.dropwizard'
  version '0.7.1-SNAPSHOT'
  repository 'http://10.0.2.2:8081/nexus/content/repositories/dwexample-snapshot/'
end

template "/opt/microservices/dropwizard-example2/dropwizard-example2.yml" do
  source 'services/dropwizard-example2.yml.erb'
  owner node['deploywizard']['user']
end
