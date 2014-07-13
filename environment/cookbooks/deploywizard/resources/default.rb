actions :install
default_action :install

attribute :group_id, :kind_of => String, :required => true
attribute :artifact_id, :kind_of => String
attribute :version, :kind_of => String, :required => true
attribute :repository, :kind_of => String, :required => true

def initialize(*args)
  super
  @artifact_id ||= @name
end
