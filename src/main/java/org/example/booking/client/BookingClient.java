package org.example.booking.client;

import io.restassured.response.Response;
import org.example.booking.dto.BookingRequest;
import org.example.core.requests.RequestSpecs;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response createBooking(BookingRequest request) {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .body(request)
                .when()
                .post("/booking");
    }

    public Response getBooking(int id) {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .when()
                .get("/booking/{id}", id);
    }

    public Response listBookings() {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .when()
                .get("/booking");
    }

    public Response updateBooking(int id, BookingRequest request, String token) {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .header("Cookie", "token=" + token)
                .body(request)
                .when()
                .put("/booking/{id}", id);
    }

    public Response partialUpdateBooking(int id, Object partialBody, String token) {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .header("Cookie", "token=" + token)
                .body(partialBody)
                .when()
                .patch("/booking/{id}", id);
    }

    public Response deleteBooking(int id, String token) {
        return given()
                .spec(RequestSpecs.defaultSpec())
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/{id}", id);
    }
}
