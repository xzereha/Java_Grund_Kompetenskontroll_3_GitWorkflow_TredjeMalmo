package com.example.views;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;

public class EditBookingWindow extends JFrame {
    private final Booking booking;
    private boolean validReg = true;
    private boolean validEmail = true;
    private boolean validYear = true;
    private boolean validDate = true;

    public EditBookingWindow(Booking booking) {
        this.booking = booking;
        setTitle("Redigera Bokning");
        setSize(400, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton saveButton = new JButton("Spara ändringar");
        ValidatedTextField regNr = new ValidatedTextField(6, RegNr::isValid);
        regNr.setText(booking.getVehicle().getRegNr().getRegNr());

        // Additional text field for repair details in a fixed-size panel
        boolean isRepair = booking instanceof Booking.Repair;

        JPanel repairPanel = new JPanel();
        if (isRepair) {
            JLabel repairLabel = new JLabel("Reparationsdetaljer:");
            repairPanel.add(repairLabel);

            Booking.Repair repairBooking = (Booking.Repair) booking;
            JTextField repairField = new JTextField(20);
            repairField.setText(repairBooking.getDescription());
            repairPanel.add(repairField);
            repairPanel.setVisible(true);
        }

        JTextField model = new JTextField(20);
        model.setText(booking.getVehicle().getModel());
        ValidatedTextField yearModel = new ValidatedTextField(4, text -> {
            if (text.isEmpty()) {
                return false;
            }
            if (text.length() != 4) {
                return false;
            }
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        });
        yearModel.setText(String.valueOf(booking.getVehicle().getProductionYear()));
        ValidatedTextField email = new ValidatedTextField(20, Email::isValid);
        email.setText(booking.getEmail().getEmail());
        ValidatedTextField date = new ValidatedTextField(20, text -> {
            try {
                java.time.LocalDate.parse(text);
                return true;
            } catch (Exception ex) {
                return false;
            }
        });
        date.setText(booking.getDate().toString());

        regNr.setValidationListener((valid) -> {
            validReg = valid;
            updateButtonState(saveButton);
        });
        yearModel.setValidationListener((valid) -> {
            validYear = valid;
            updateButtonState(saveButton);
        });
        email.setValidationListener((valid) -> {
            validEmail = valid;
            updateButtonState(saveButton);
        });
        date.setValidationListener((valid) -> {
            validDate = valid;
            updateButtonState(saveButton);
        });

        panel.add(new JLabel("Reg.nr:"));
        panel.add(regNr);
        panel.add(new JLabel("Modell:"));
        panel.add(model);
        panel.add(new JLabel("Årsmodell:"));
        panel.add(yearModel);
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(new JLabel("Datum:"));
        panel.add(date);
        panel.add(repairPanel);

        saveButton.setEnabled(true);
        saveButton.addActionListener(ev -> {
            String regString = regNr.getText();
            String modelString = model.getText();
            String yearModelString = yearModel.getText();
            String emailString = email.getText();
            String dateString = date.getText();
            var year = Integer.parseInt(yearModelString);
            // TODO: Assert that date is valid
            var date_ = java.time.LocalDate.parse(dateString);

            booking.getVehicle().setRegNr(new RegNr(regString));
            booking.getVehicle().setModel(modelString);
            booking.getVehicle().setProductionYear(year);
            booking.setEmail(new Email(emailString));
            booking.setDate(date_);

            if (isRepair) {
                Booking.Repair repairBooking = (Booking.Repair) booking;
                JTextField repairField = (JTextField) repairPanel.getComponent(1);
                String repairDetails = repairField.getText();
                repairBooking.setDescription(repairDetails);
            }

            dispose();
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(saveButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void updateButtonState(JButton button) {
        button.setEnabled(validReg && validEmail && validYear && validDate);
    }
}