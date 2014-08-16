#dw-example.rb

name "dw-example"
description "Deploywizard example"
run_list(
  "recipe[rabbitmq]","recipe[maven]","recipe[deploywizard]"
)
