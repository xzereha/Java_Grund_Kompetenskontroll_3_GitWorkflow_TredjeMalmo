package com.example.views;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.BookingRepository;
import com.example.PriceList;
import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;
import com.example.Models.Vehicle;

public class AddBookingView extends JDialog {
    private boolean validReg = false;
    private boolean validEmail = false;
    private boolean validYear = false;
    private boolean validDate = false;

    public AddBookingView(BookingRepository bookingRepository) {
        setTitle("Ny bokning");
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addBookingBtn = new JButton("Add");
        ValidatedTextField regNr = new ValidatedTextField(6, RegNr::isValid);

        // Additional text field for repair details in a fixed-size panel
        JPanel repairPanel = new JPanel();
        repairPanel.setLayout(new BoxLayout(repairPanel, BoxLayout.Y_AXIS));
        repairPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        JLabel repairLabel = new JLabel("Reparationsdetaljer:");
        JTextField repairField = new JTextField(20);
        repairPanel.add(repairLabel);
        repairPanel.add(repairField);
        repairPanel.setVisible(false);

        JTextField model = new JTextField(20);
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
        ValidatedTextField email = new ValidatedTextField(20, Email::isValid);
        ValidatedTextField date = new ValidatedTextField(20, text -> {
            try {
                java.time.LocalDate.parse(text);
                return true;
            } catch (Exception ex) {
                return false;
            }
        });

        regNr.setValidationListener((valid) -> {
            validReg = valid;
            updateButtonState(addBookingBtn);
        });
        yearModel.setValidationListener((valid) -> {
            validYear = valid;
            updateButtonState(addBookingBtn);
        });
        email.setValidationListener((valid) -> {
            validEmail = valid;
            updateButtonState(addBookingBtn);
        });
        date.setValidationListener((valid) -> {
            validDate = valid;
            updateButtonState(addBookingBtn);
        });

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
                    priceLabel.setText("Pris: " + PriceList.getInspectionPrice() + " SEK");
                    repairPanel.setVisible(false);
                    break;
                case "Service":
                    // TODO: Read from pricelist
                    if (yearModel.getText().length() == 4) {
                        int year = Integer.parseInt(yearModel.getText());
                        priceLabel.setText("Pris: " + PriceList.getServicePrice(year) + " SEK");
                    } else {
                        priceLabel.setText("Pris: Beror på årsmodell");
                    }
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
            // TODO: Assert that date is valid
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
        button.setEnabled(validReg && validEmail && validYear && validDate);
    }
}
