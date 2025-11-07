package com.example.views;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;

public class EditBookingWindow extends JFrame {
    private final BookingFormPanel formPanel;
    private Runnable onSave = null;

    public EditBookingWindow(Booking booking) {
        setTitle("Redigera Bokning");
        setSize(400, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton saveButton = new JButton("Spara ändringar");

        formPanel = new BookingFormPanel(booking);
        panel.add(formPanel);

        saveButton.setEnabled(true);
        saveButton.addActionListener(ev -> {
            String regString = formPanel.getRegNr();
            String modelString = formPanel.getModel();
            String yearModelString = formPanel.getYearModel();
            String emailString = formPanel.getEmail();
            String dateString = formPanel.getDate();

            // NOTE(Oliver) No need to validate again, as button is disabled if invalid
            var year = Integer.parseInt(yearModelString);
            var date_ = java.time.LocalDate.parse(dateString);

            booking.getVehicle().setRegNr(new RegNr(regString));
            booking.getVehicle().setModel(modelString);
            booking.getVehicle().setProductionYear(year);
            booking.setEmail(new Email(emailString));
            booking.setDate(date_);

            if (formPanel.isRepair()) {
                Booking.Repair repairBooking = (Booking.Repair) booking;
                String repairDetails = formPanel.getRepairDescription();
                repairBooking.setDescription(repairDetails);
            }

            if (onSave != null) {
                onSave.run();
            }
            dispose();
        });

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(saveButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public EditBookingWindow(Booking booking, Runnable onSave) {
        this(booking);
        this.onSave = onSave;
    }

    private void updateButtonState(JButton button) {
        button.setEnabled(formPanel.isFormValid());
    }
}