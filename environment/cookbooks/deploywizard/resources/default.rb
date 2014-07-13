actions :install

attribute :name, :kind_of => String, :required => true
attribute :group_id, :kind_of => String, :required => true
attribute :version, :kind_of => String, :required => true
attribute :repository, :kind_of => String, :required => true

def initialize(*args)
  super
  @action = :install
end
