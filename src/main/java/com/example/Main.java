package com.example;

import com.example.views.Menu;

public class Main {
        public static void main(String[] args) {

                BookingRepository repo = new BookingRepository();
                InitService.init(repo);
                BookingService bookingService = new BookingService(repo);
                Menu menu = new Menu(bookingService);
        }
}