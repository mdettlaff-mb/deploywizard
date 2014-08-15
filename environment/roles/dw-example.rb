#dw-example.rb

name "dw-example"
description "Deploywizard example"
run_list(
  "recipe[maven]","recipe[deploywizard]","recipe[rabbitmq]"
)
