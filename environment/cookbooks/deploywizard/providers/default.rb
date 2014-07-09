def install_microservice(new_resource) do
  directory "/opt/microservices" do
    action :create
    owner node['deploywizard']['user']
  end
  directory "/opt/microservices/#{new_resource.name}" do
    action :create
    owner node['deploywizard']['user']
  end

  maven "manager" do
    group_id new_resource.group_id
    version new_resource.version
    artifact_id 'manager'
    packaging 'war'
    dest "/opt/microservices/#{new_resource.name}/"
    repositories [new_resource.repository]
    owner node['deploywizard']['user']
    action :install
  end

  template "/opt/microservices/#{new_resource.name}/#{new_resource.name}-#{new_resource.version}.jar" do
    source 'microservice.init.erb'
    owner node['deploywizard']['user']
    variables ({
      :service_name => new_resource.name,
      :service_version => new_resource.version,
      :owner => node['deploywizard']['user']
    })
  end
end

action :microservice do
  converge_by("Install microservice #{new_resource}") do
    install_microservice('install', new_resource)
  end
end
