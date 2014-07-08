#base-ubuntu.rb

name "base-ubuntu"
description "Base fot ubuntu servers"
run_list "recipe[apt]","recipe[chef-client::cron]","recipe[ntp]","recipe[timezone-ii]"
default_attributes(
  :java => {
     :oracle => {
       "accept_oracle_download_terms" => true
     }
   },
  :chef_client => {
  	:cron =>{
  		'minute' => '*/10',
  		'hour' => '*',
  		'path' => nil,
  		'environment_variables' => nil,
  		'log_file' => '/dev/null',
  		'use_cron_d' => false,
  		'mailto' => nil,
  	}
  },
  :tz => 'Europe/Warsaw'
)

