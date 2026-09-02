package org.example.booking.dto;

public record CreateBookingResponse(
        int bookingid,
        BookingRequest booking
) {
}
