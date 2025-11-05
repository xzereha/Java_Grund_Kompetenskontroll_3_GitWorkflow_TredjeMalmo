
package com.example.views;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import com.example.BookingService;
import com.example.Models.Booking;

public class BookingView extends JPanel {
    private static final int PREFERRED_WIDTH = 400;
    private static final int PREFERRED_HEIGHT = 80;
    private static final int EXPANDED_HEIGHT = 200;

    private boolean expanded = false;
    private final BookingService bookingService;
    private final Booking booking;
    private OnBookingChanged bookingChangedListener;

    public BookingView(Booking booking, BookingService bookingService) {
        this(booking, bookingService, null);
    }

    public BookingView(Booking booking, BookingService bookingService, OnBookingChanged listener) {
        this.booking = booking;
        this.bookingService = bookingService;
        this.bookingChangedListener = listener;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setBorder(BorderFactory.createRaisedSoftBevelBorder());
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

    public void setBookingChangedListener(OnBookingChanged listener) {
        this.bookingChangedListener = listener;
    }

    private void renderCompact() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        drawTypeInfo(this);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(new JLabel("ID: " + booking.getId()));
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(new JLabel("Datum: " + booking.getDate().toString()));
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(new JLabel("Status : " + booking.getStatus()));
        add(Box.createHorizontalGlue());
    }

    private void renderExpanded() {
        setLayout(new FlowLayout());

        var dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setAlignmentX(LEFT_ALIGNMENT);
        drawTypeInfo(dataPanel);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dataPanel.add(new JLabel("ID: " + booking.getId()));
        dataPanel.add(new JLabel("Datum: " + booking.getDate().toString()));
        dataPanel.add(new JLabel("Email: " + booking.getEmail().getEmail()));
        drawVehicleInfo(dataPanel);
        dataPanel.add(new JLabel("Pris: " + booking.getPrice()));
        dataPanel.add(new JLabel("Status: " + booking.getStatus()));
        if (booking instanceof Booking.Repair repair) {
            dataPanel.add(new JLabel("Reparationsdetaljer: " + repair.getDescription()));
        }
        add(dataPanel);

        var controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        JButton finishButton = new JButton("Avsluta bokning");
        finishButton.addActionListener(e -> {
            if (booking instanceof Booking.Repair) {
                var input = JOptionPane.showInputDialog(this,
                        "Ange pris för reparation:",
                        "Slutför reparation",
                        JOptionPane.PLAIN_MESSAGE);
                if (input == null) {
                    return; // User cancelled
                }
                float price;
                try {
                    price = Float.parseFloat(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Ogiltigt pris angivet. Försök igen.",
                            "Fel",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                bookingService.changeBookingStatusToComplete(booking, price);
            } else {
                bookingService.changeBookingStatusToComplete(booking);
            }
            if (bookingChangedListener != null) {
                bookingChangedListener.bookingChanged();
            }
        });
        controlPanel.add(finishButton);
        JButton editButton = new JButton("Ändra bokning");
        editButton.addActionListener(e -> {
            new EditBookingWindow(booking).setVisible(true);
            if (bookingChangedListener != null) {
                bookingChangedListener.bookingChanged();
            }
        });
        controlPanel.add(editButton);
        JButton cancelButton = new JButton("Avboka bokning");
        cancelButton.addActionListener(e -> {
            bookingService.removeBooking(booking);
            if (bookingChangedListener != null) {
                bookingChangedListener.bookingChanged();
            }
        });
        controlPanel.add(cancelButton);
        add(controlPanel);
    }

    private void drawTypeInfo(JPanel panel) {
        if (booking instanceof Booking.Inspection) {
            panel.add(new JLabel("Besiktning"));
        } else if (booking instanceof Booking.Service) {
            panel.add(new JLabel("Service"));
        } else if (booking instanceof Booking.Repair) {
            panel.add(new JLabel("Reparation"));
        }
    }

    private void drawVehicleInfo(JPanel panel) {
        var vehicle = booking.getVehicle();
        panel.add(new JLabel("Fordon: " + vehicle.getRegNr().getRegNr() + ", " + vehicle.getModel() + ", "
                + vehicle.getProductionYear()));
    }
}