#
# Cookbook Name:: deploywizard
# Recipe:: default
#
# Copyright 2014, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

microservice 'dw-eexample1' do
  name 'dw-example1'
end

template "/opt/microservices/dw-example1/dw-example1.yml" do
  source 'dw-example1.yml.erb'
  owner node['deploywizard']['user']
end
