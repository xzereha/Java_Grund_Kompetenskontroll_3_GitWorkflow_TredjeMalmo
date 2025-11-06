package com.example.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.*;

import com.example.BookingService;
import com.example.Models.Booking;
import com.example.Models.Booking.Status;
import com.example.sorting.Sort;
import com.example.sorting.SortByDate;
import com.example.sorting.SortByID;
import com.example.sorting.SortByStatus;

public class BookingListView extends JPanel {
    private final BookingService bookingService;
    private final JPanel listPanel;
    private Sort currentSorting;
    private Sort currentFilter;

    public BookingListView(BookingService bookingService) {
        this.bookingService = bookingService;
        this.currentSorting = new SortByID(0);
        this.currentFilter = null;

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
            AddBookingView addBookingView = new AddBookingView(bookingService);
            addBookingView.setVisible(true);
            refreshBookings();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
        var sortingOptions = new String[] {
                "Sortera efter ID",
                "Sortera efter datum",
        };
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);
        var filterOptions = new String[] {
                "Inga filter",
                "Endast pågående",
                "Endast slutförda",
        };
        JComboBox<String> filterComboBox = new JComboBox<>(filterOptions);
        sortingComboBox.addActionListener(e -> {
            int selectedIndex = sortingComboBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> currentSorting = new SortByID(0);
                case 1 -> currentSorting = new SortByDate(true);
            }
            refreshBookings();
        });
        buttonPanel.add(sortingComboBox);
        filterComboBox.addActionListener(e -> {
            int selectedIndex = filterComboBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> currentFilter = null;
                case 1 -> currentFilter = new SortByStatus(Status.PENDING);
                case 2 -> currentFilter = new SortByStatus(Status.COMPLETED);
            }
            refreshBookings();
        });
        buttonPanel.add(filterComboBox);
    }

    private void refreshBookings() {
        listPanel.removeAll();

        List<Booking> bookings = null;
        if (currentFilter != null) {
            bookings = currentFilter.apply(bookingService.listBookings());
        } else {
            bookings = bookingService.listBookings();
        }

        for (var booking : currentSorting.apply(bookings)) {
            BookingView bookingView = new BookingView(booking, bookingService, () -> refreshBookings());
            listPanel.add(bookingView);
            // Add spacing between booking views
            listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        listPanel.revalidate();
        listPanel.repaint();
    }
}
