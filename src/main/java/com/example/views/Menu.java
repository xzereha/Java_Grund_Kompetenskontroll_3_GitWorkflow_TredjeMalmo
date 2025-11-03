package com.example.views;

import javax.swing.JFrame;

import com.example.BookingRepository;

public class Menu extends JFrame {
    public Menu(BookingRepository repo) {
        setTitle("Bokningssystem");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new BookingListView(repo));

        setVisible(true);
    }
}
