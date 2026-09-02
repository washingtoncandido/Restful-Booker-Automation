package org.example.auth.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.auth.dto.AuthRequest;
import org.example.core.config.ConfigurationManager;
import org.example.core.requests.RequestSpecs;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response authenticate(AuthRequest request) {
        RestAssured.useRelaxedHTTPSValidation();
        return given()
                .spec(RequestSpecs.defaultSpec())
                .body(request)
                .when()
                .post("/auth");
    }
}