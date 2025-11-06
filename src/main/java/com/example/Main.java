package com.example;

import com.example.views.Menu;

public class Main {
    public static void main(String[] args) {
        BookingRepository repo = new BookingRepository();
        BookingService bookingService = new BookingService(repo);
        InitService.init(bookingService);
        Menu menu = new Menu(bookingService);
    }
}