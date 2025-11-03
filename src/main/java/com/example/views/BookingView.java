
package com.example.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.example.Models.Booking;

public class BookingView extends JPanel {
    private static final int PREFERRED_WIDTH = 400;
    private static final int PREFERRED_HEIGHT = 80;
    private static final int EXPANDED_HEIGHT = 160;

    private boolean expanded = false;
    private final Booking booking;

    public BookingView(Booking booking) {
        this.booking = booking;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setBackground(new Color(245, 245, 245));
        setMaximumSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        setMinimumSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        renderCompact();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                expanded = !expanded;
                removeAll();
                if (expanded) {
                    renderExpanded();
                    setMaximumSize(new Dimension(PREFERRED_WIDTH, EXPANDED_HEIGHT));
                    setPreferredSize(new Dimension(PREFERRED_WIDTH, EXPANDED_HEIGHT));
                    setMinimumSize(new Dimension(PREFERRED_WIDTH, EXPANDED_HEIGHT));
                } else {
                    renderCompact();
                    setMaximumSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
                    setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
                    setMinimumSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
                }
                revalidate();
                repaint();
            }
        });
    }

    private void renderCompact() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(new JLabel("ID: " + booking.getId()));
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(new JLabel("Datum: " + booking.getDate().toString()));
        add(Box.createHorizontalGlue());
    }

    private void renderExpanded() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("ID: " + booking.getId()));
        add(new JLabel("Datum: " + booking.getDate().toString()));
        add(new JLabel("Email: " + booking.getEmail().getEmail()));
        add(new JLabel("Fordon: " + booking.getVehicle()));
        add(new JLabel("Pris: " + booking.getPrice()));
        add(new JLabel("Status: " + booking.getStatus()));
        add(Box.createVerticalGlue());
    }
}