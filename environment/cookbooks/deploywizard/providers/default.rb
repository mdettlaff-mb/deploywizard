def install_microservice(new_resource)
  user node['deploywizard']['user'] do
    system true
    action :create
  end

  directory "/opt/microservices" do
    action :create
    owner node['deploywizard']['user']
  end
  directory "/opt/microservices/#{new_resource.name}" do
    action :create
    owner node['deploywizard']['user']
  end
  directory "/opt/microservices/#{new_resource.name}/work" do
    action :create
    owner node['deploywizard']['user']
  end
  directory "/var/log/microservices" do
    action :create
    owner node['deploywizard']['user']
  end

  maven "#{new_resource.artifact_id}-artifact" do
    group_id new_resource.group_id
    version new_resource.version
    artifact_id new_resource.artifact_id
    packaging 'jar'
    dest "/opt/microservices/#{new_resource.name}/"
    repositories [new_resource.repository]
    owner node['deploywizard']['user']
    action :install
  end

  template "/etc/init.d/#{new_resource.name}" do
    source 'microservice.init.erb'
    owner "root"
    group "root"
    mode 00755
    action :create
    variables ({
      :service_name => new_resource.name,
      :artifact_id => new_resource.artifact_id,
      :service_version => new_resource.version,
      :owner => node['deploywizard']['user']
    })
  end

  service "#{new_resource.name}" do
    action :enable
  end
  service "#{new_resource.name}" do
    action :start
  end
end

action :install do
  converge_by("Install microservice #{new_resource}") do
    install_microservice(new_resource)
  end
end
