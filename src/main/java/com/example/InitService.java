package com.example;

import java.time.LocalDate;

public class InitService {
    public static void init(BookingService bookingService) {
        bookingService.bookService("ABC123", "Fiat Panda", 2022, "anna.persson@gmail.com",
                LocalDate.of(2025, 11, 28));

        bookingService.bookService("HJA234", "Mercedes GLE", 2000, "lars.karlsson@outlook.com",
                LocalDate.of(2025, 12, 2));

        bookingService.bookService("HJB234", "Tesla", 2022, "emma.nilsson@hotmail.se",
                LocalDate.of(2025, 12, 3));

        bookingService.bookService("KJE345", "Toyota Yaris GR", 2022, "johan.eriksson@domain.se",
                LocalDate.of(2025, 12, 12));

        bookingService.bookInspection("KLE234", "Volvo V60", 2022, "maria.svensson@gmail.com",
                LocalDate.of(2025, 11, 22));

        bookingService.bookRepair("OUT234", "BMW 530d", 2022, "anders.lindberg@domain.se",
                LocalDate.of(2024, 11, 26), "carFarts");

        bookingService.bookService("LOL137", "Volvo V70", 2022, "sofia.johansson@hotmail.se",
                LocalDate.of(2025, 11, 28));

        bookingService.bookInspection("DAS345", "Koenigsegg Agera", 2022, "henrik.larsson@gmail.com",
                LocalDate.of(2025, 11, 29));

        bookingService.bookRepair("BOO355", "Saab 9-3", 2022, "elin.berg@outlook.com",
                LocalDate.of(2024, 11, 26), "carFarts");

        bookingService.bookService("KLD456", "Volvo V60", 2022, "oliver.andersson@domain.se",
                LocalDate.of(2025, 6, 26));

        bookingService.bookInspection("LEK567", "Nissan Micra", 2022, "lina.holm@gmail.com",
                LocalDate.of(2025, 11, 26));

        bookingService.bookRepair("OST678", "Mercedes E-klass", 2022, "erik.sandberg@hotmail.se",
                LocalDate.of(2026, 3, 26), "carFarts");

        bookingService.bookService("GLO789", "Alpina B10", 2022, "victor.fransson@domain.se",
                LocalDate.of(2026, 6, 26));

        bookingService.bookRepair("FUS890", "Kia EV5", 2022, "sara.lundqvist@gmail.com",
                LocalDate.of(2025, 8, 26), "carFarts");

        bookingService.bookInspection("LUP901", "Audi A4", 2022, "fredrik.nilsson@hotmail.se",
                LocalDate.of(2026, 5, 3));
    }
}
