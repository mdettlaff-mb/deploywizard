package com.example.helloworld.health;

import com.codahale.metrics.health.HealthCheck;

public class Example1HealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
