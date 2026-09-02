package org.example.booking.dto;

public record BookingRequest(
        String firstname,
        String lastname,
        int totalprice,
        boolean depositpaid,
        BookingDates bookingdates,
        String additionalneeds
) {
}
