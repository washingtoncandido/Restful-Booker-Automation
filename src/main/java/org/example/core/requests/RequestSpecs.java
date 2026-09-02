package org.example.core.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.core.config.ConfigurationManager;

public final class RequestSpecs {

    private RequestSpecs() {
    }

    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigurationManager.get("base.url"))
                .setContentType("application/json")
                .build();
    }
}