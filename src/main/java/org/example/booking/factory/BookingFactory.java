package org.example.booking.factory;

import org.example.booking.dto.BookingDates;
import org.example.booking.dto.BookingRequest;

public final class BookingFactory {

    private BookingFactory() {}

    public static BookingRequest validBooking() {
        return new BookingRequest(
                "John",
                "Doe",
                150,
                true,
                new BookingDates("2023-01-01", "2023-01-10"),
                "Breakfast"
        );
    }

    public static BookingRequest anotherBooking() {
        return new BookingRequest(
                "Jane",
                "Smith",
                200,
                false,
                new BookingDates("2023-02-01", "2023-02-05"),
                "Late checkout"
        );
    }

    public static BookingRequest updateBookingData() {
        return new BookingRequest(
                "Updated",
                "Name",
                250,
                true,
                new BookingDates("2023-03-01", "2023-03-07"),
                "Lunch"
        );
    }
}
