package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.auth.client.AuthClient;
import org.example.auth.dto.AuthRequest;
import org.example.auth.factory.AuthFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AuthSteps {

    private AuthRequest authRequest;
    private Response response;

    private final AuthClient authClient = new AuthClient();

    @Given("que possuo credenciais válidas")
    public void quePossuoCredenciaisValidas() {

        authRequest = AuthFactory.validCredentials();
    }

    @When("realizo a autenticação")
    public void realizoAutenticacao() {

        response = authClient.authenticate(authRequest);
    }

    @Then("a autenticação deve ser realizada com sucesso")
    public void autenticacaoDeveSerRealizadaComSucesso() {

        assertEquals(200, response.statusCode());
    }

    @And("devo receber um token de autenticação")
    public void devoReceberUmTokenDeAutenticacao() {

        String token = response.jsonPath().getString("token");

        assertNotNull(token);
        assertFalse(token.isBlank());
    }
}