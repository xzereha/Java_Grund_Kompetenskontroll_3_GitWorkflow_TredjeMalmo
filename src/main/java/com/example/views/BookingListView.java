package com.example.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;

import com.example.BookingRepository;

public class BookingListView extends JPanel {
    private final BookingRepository bookingRepository;
    private final JPanel listPanel;

    public BookingListView(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel title = new JLabel("Bokningar", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);
        listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        refreshBookings();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        // Add button to add a new booking
        JButton addButton = new JButton("Lägg till bokning");
        addButton.addActionListener(e -> {
            AddBookingView addBookingView = new AddBookingView(bookingRepository);
            addBookingView.setVisible(true);
            refreshBookings();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshBookings() {
        listPanel.removeAll();
        for (var booking : bookingRepository.getBookingList()) {
            BookingView bookingView = new BookingView(booking);
            listPanel.add(bookingView);
        }
        listPanel.revalidate();
        listPanel.repaint();
    }
}
