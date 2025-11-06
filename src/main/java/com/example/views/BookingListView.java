package com.example.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.*;

import com.example.BookingService;
import com.example.sorting.Sort;
import com.example.sorting.SortByDate;
import com.example.sorting.SortByID;
import com.example.sorting.SortByStatus;

public class BookingListView extends JPanel {
    private final BookingService bookingService;
    private final JPanel listPanel;
    private Sort currentSorting;

    public BookingListView(BookingService bookingService) {
        this.bookingService = bookingService;
        this.currentSorting = new SortByID();

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

        var sortingOptions = new String[]{
                "Sortera efter ID",
                "Sortera efter datum",
                "Sortera efter status"
        };
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);

        sortingComboBox.addActionListener(e -> {
            int selectedIndex = sortingComboBox.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> currentSorting = new SortByID();
                case 1 -> currentSorting = new SortByDate(true);
                case 2 -> currentSorting = new SortByStatus();
            }
            refreshBookings();
        });

        buttonPanel.add(sortingComboBox);
    }

    /**
     * Refresh the list of bookings displayed in the panel.
     */
    private void refreshBookings() {
        listPanel.removeAll();

        for (var booking : currentSorting.apply(bookingService.listBookings())) {
            BookingView bookingView = new BookingView(booking, bookingService, () -> refreshBookings());
            listPanel.add(bookingView);
            // Add spacing between booking views
            listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        listPanel.revalidate();
        listPanel.repaint();
    }
}
