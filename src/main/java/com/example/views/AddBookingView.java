package com.example.views;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.example.BookingRepository;
import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;

public class AddBookingView extends JDialog {
    private boolean validReg = false;
    private boolean validEmail = false;

    public AddBookingView(BookingRepository bookingRepository) {
        setTitle("Ny bokning");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        // panel.setLayout(new CardLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton addBookingBtn = new JButton("Add");
        JTextField regNr = new JTextField(6);
        // Additional text field for repair details in a fixed-size panel
        JPanel repairPanel = new JPanel();
        repairPanel.setLayout(new BoxLayout(repairPanel, BoxLayout.Y_AXIS));
        repairPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        JLabel repairLabel = new JLabel("Reparationsdetaljer:");
        JTextField repairField = new JTextField(20);
        repairPanel.add(repairLabel);
        repairPanel.add(repairField);
        repairPanel.setVisible(false);
        regNr.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                validReg = RegNr.isValid(regNr.getText());
                markValid(validReg);
                updateButtonState(addBookingBtn);
            }

            private void markValid(boolean valid) {
                if (valid) {
                    regNr.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                } else {
                    regNr.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                }
            }
        });
        JTextField model = new JTextField(20);
        JTextField yearModel = new JTextField(4);
        yearModel.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                String text = yearModel.getText();
                if (text.isEmpty()) {
                    yearModel.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                    return;
                }
                if (text.length() != 4) {
                    yearModel.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                    return;
                }
                try {
                    Integer.parseInt(text);
                    yearModel.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                } catch (NumberFormatException ex) {
                    yearModel.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                }
            }
        });
        JTextField email = new JTextField(20);
        email.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                validEmail = Email.isValid(email.getText());
                markValid(validEmail);
                updateButtonState(addBookingBtn);
            }

            private void markValid(boolean valid) {
                if (valid) {
                    email.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                } else {
                    email.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED));
                }
            }
        });
        JTextField date = new JTextField(20);
        JLabel priceLabel = new JLabel("Pris: Baserad på bokningstyp");
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
        panel.add(priceLabel);
        String[] options = { "Besiktning", "Service", "Reparation" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        panel.add(comboBox);
        panel.add(repairPanel);
        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "Besiktning":
                    // TODO: Read from pricelist
                    priceLabel.setText("Pris: 500 SEK");
                    repairPanel.setVisible(false);
                    break;
                case "Service":
                    // TODO: Read from pricelist
                    priceLabel.setText("Pris: 1500 SEK");
                    repairPanel.setVisible(false);
                    break;
                case "Reparation":
                    priceLabel.setText("Pris: Baserad på reparationens omfattning");
                    repairPanel.setVisible(true);
                    break;
                default:
                    priceLabel.setText("Pris: Baserad på bokningstyp");
                    repairPanel.setVisible(false);
                    break;
            }
            pack();
            panel.revalidate();
            panel.repaint();
        });
        addBookingBtn.setEnabled(false);
        addBookingBtn.addActionListener(ev -> {
            String regString = regNr.getText();
            String modelString = model.getText();
            String yearModelString = yearModel.getText();
            String emailString = email.getText();
            String dateString = date.getText();
            var regNr_ = new RegNr(regString);
            var email_ = new Email(emailString);
            var year = Integer.parseInt(yearModelString);
            var vehicle = new Vehicle(regNr_, modelString, year);
            var date_ = java.time.LocalDate.parse(dateString);
            var bookingType = (String) comboBox.getSelectedItem();
            switch (bookingType) {
                case "Besiktning": {
                    var price = 500; // TODO: Read from pricelist
                    var booking = new Booking.Inspection(vehicle, email_, date_, price);
                    bookingRepository.addBooking(booking);
                }
                    break;
                case "Service": {
                    var price = 1000; // TODO: Read from pricelist
                    var booking = new Booking.Service(vehicle, email_, date_, price);
                    bookingRepository.addBooking(booking);
                }
                    break;
                case "Reparation": {
                    String repairDetails = repairField.getText();
                    var booking = new Booking.Repair(vehicle, email_, date_, repairDetails);
                    bookingRepository.addBooking(booking);
                }
                    break;
                default:
                    break;
            }
            dispose();
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addBookingBtn);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void updateButtonState(JButton button) {
        button.setEnabled(validReg && validEmail);
    }
}
