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

import com.example.BookingService;
import com.example.PriceList;
import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;

public class AddBookingView extends JDialog {
    private BookingFormPanel formPanel;

    public AddBookingView(BookingService bookingService) {
        setTitle("Ny bokning");
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addBookingBtn = new JButton("Add");

        formPanel = new BookingFormPanel();
        formPanel.setOnChangeListener(() -> updateButtonState(addBookingBtn));
        panel.add(formPanel);

        String[] options = { "Besiktning", "Service", "Reparation" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        panel.add(comboBox);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "Besiktning":
                    formPanel.setPrice(PriceList.getInspectionPrice());
                    formPanel.setIsRepair(false);
                    break;
                case "Service":
                    if (formPanel.getYearModel().length() == 4) {
                        int year = Integer.parseInt(formPanel.getYearModel());
                        formPanel.setPrice(PriceList.getServicePrice(year));
                    } else {
                        formPanel.setPrice(0); // Reset price if year model is invalid
                    }
                    formPanel.setIsRepair(false);
                    break;
                case "Reparation":
                    formPanel.setPrice(0); // Reset price for repairs
                    formPanel.setIsRepair(true);
                    break;
                default:
                    formPanel.setPrice(0);
                    formPanel.setIsRepair(false);
                    break;
            }
            pack();
            panel.revalidate();
            panel.repaint();
        });

        addBookingBtn.setEnabled(false);
        addBookingBtn.addActionListener(ev -> {
            String regString = formPanel.getRegNr();
            String modelString = formPanel.getModel();
            String yearModelString = formPanel.getYearModel();
            String emailString = formPanel.getEmail();
            String dateString = formPanel.getDate();
            // NOTE(Oliver) No need to validate, button is disabled until all fields are
            // valid
            var year = Integer.parseInt(yearModelString);
            var date_ = java.time.LocalDate.parse(dateString);

            var bookingType = (String) comboBox.getSelectedItem();
            switch (bookingType) {
                case "Besiktning":
                    bookingService.bookInspection(regString, modelString, year, emailString, date_);
                    break;
                case "Service":
                    bookingService.bookService(regString, modelString, year, emailString, date_);
                    break;
                case "Reparation":
                    String repairDetails = formPanel.getRepairDescription();
                    bookingService.bookRepair(regString, modelString, year, emailString, date_, repairDetails);
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
        button.setEnabled(formPanel.isFormValid());
    }
}
