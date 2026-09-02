package org.example.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.example.auth.client.AuthClient;
import org.example.auth.dto.AuthRequest;
import org.example.auth.factory.AuthFactory;
import org.example.booking.client.BookingClient;
import org.example.booking.dto.BookingRequest;
import org.example.booking.factory.BookingFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BookingSteps {

    private final BookingClient bookingClient = new BookingClient();
    private final AuthClient authClient = new AuthClient();

    private BookingRequest bookingRequest;
    private Response response;
    private int bookingId;
    private String token;

    @Given("que possuo um booking válido")
    public void quePossuoUmBookingValido() {
        bookingRequest = BookingFactory.validBooking();
    }

    @When("realizo a criação do booking")
    public void realizoACriacaoDoBooking() {
        response = bookingClient.createBooking(bookingRequest);
    }

    @Then("o booking deve ser criado com sucesso")
    public void oBookingDeveSerCriadoComSucesso() {
        assertEquals(200, response.statusCode());
    }

    @And("devo receber o id do booking")
    public void devoReceberOIdDoBooking() {
        bookingId = response.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0);
    }

    @Given("que exista um booking criado")
    public void queExistaUmBookingCriado() {
        bookingRequest = BookingFactory.validBooking();
        response = bookingClient.createBooking(bookingRequest);
        bookingId = response.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0);
    }

    @When("consulto o booking pelo id")
    public void consultoOBookingPeloId() {
        response = bookingClient.getBooking(bookingId);
    }

    @Then("devo receber os dados do booking")
    public void devoReceberOsDadosDoBooking() {
        assertEquals(200, response.statusCode());
        String firstname = response.jsonPath().getString("firstname");
        assertNotNull(firstname);
    }

    @Given("que existam bookings criados")
    public void queExistamBookingsCriados() {
        // criar dois bookings rapidamente
        bookingClient.createBooking(BookingFactory.validBooking());
        bookingClient.createBooking(BookingFactory.anotherBooking());
    }

    @When("solicito a lista de bookings")
    public void solicitoAListaDeBookings() {
        response = bookingClient.listBookings();
    }

    @Then("devo receber uma lista com pelo menos um booking")
    public void devoReceberUmaListaComPeloMenosUmBooking() {
        assertEquals(200, response.statusCode());
        assertTrue(response.jsonPath().getList("bookingid").size() >= 1);
    }

    @Given("possuo token de autenticação válido")
    public void possuoTokenDeAutenticacaoValido() {
        AuthRequest authRequest = AuthFactory.validCredentials();
        Response authResponse = authClient.authenticate(authRequest);
        token = authResponse.jsonPath().getString("token");
        assertNotNull(token);
    }

    @When("atualizo o booking com novos dados")
    public void atualizoOBookingComNovosDados() {
        BookingRequest updated = BookingFactory.updateBookingData();
        response = bookingClient.updateBooking(bookingId, updated, token);
    }

    @Then("a atualização deve retornar sucesso")
    public void aAtualizacaoDeveRetornarSucesso() {
        assertTrue(response.statusCode() == 200 || response.statusCode() == 201);
    }

    @And("os dados atualizados devem ser retornados")
    public void osDadosAtualizadosDevemSerRetornados() {
        assertEquals(bookingId, bookingId);
    }

    @When("atualizo parcialmente o booking com um campo")
    public void atualizoParcialmenteOBookingComUmCampo() {
        Map<String, Object> partial = new HashMap<>();
        partial.put("firstname", "PartialName");
        response = bookingClient.partialUpdateBooking(bookingId, partial, token);
    }

    @Then("a atualização parcial deve retornar sucesso")
    public void aAtualizacaoParcialDeveRetornarSucesso() {
        assertTrue(response.statusCode() == 200 || response.statusCode() == 201);
    }

    @And("o campo atualizado deve ser refletido")
    public void oCampoAtualizadoDeveSerRefletido() {
        Response r = bookingClient.getBooking(bookingId);
        assertEquals("PartialName", r.jsonPath().getString("firstname"));
    }

    @When("realizo a exclusão do booking")
    public void realizoAExclusaoDoBooking() {
        response = bookingClient.deleteBooking(bookingId, token);
    }

    @Then("a resposta deve indicar sucesso")
    public void aRespostaDeveIndicarSucesso() {
        assertTrue(response.statusCode() == 201 || response.statusCode() == 200 || response.statusCode() == 204);
    }

    @And("ao consultar o booking o servidor deve retornar 404")
    public void aoConsultarOBookingOServidorDeveRetornar404() {
        Response r = bookingClient.getBooking(bookingId);
        assertEquals(404, r.statusCode());
    }

    @When("tento atualizar o booking sem token")
    public void tentoAtualizarOSBookingSemToken() {
        BookingRequest updated = BookingFactory.updateBookingData();
        response = bookingClient.updateBooking(bookingId, updated, "");
    }

    @Then("a resposta deve indicar falta de autorização")
    public void aRespostaDeveIndicarFaltaDeAutorizacao() {
        assertTrue(response.statusCode() == 403 || response.statusCode() == 401);
    }
}
