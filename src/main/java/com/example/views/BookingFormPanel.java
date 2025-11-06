package com.example.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Models.Booking;
import com.example.Models.Email;
import com.example.Models.RegNr;

public class BookingFormPanel extends JPanel {
    private boolean isRepair = false;
    private boolean validReg = false;
    private boolean validEmail = false;
    private boolean validYear = false;
    private boolean validDate = false;
    private Runnable onChange = null;

    private String initialRegNr = "";
    private String initialModel = "";
    private String initialYearModel = "";
    private String initialEmail = "";
    private String initialDate = "";
    private String initialRepairDescription = "";

    private JPanel repairPanel;
    private ValidatedTextField regField;
    private JTextField modelField;
    private ValidatedTextField yearField;
    private ValidatedTextField emailField;
    private ValidatedTextField dateField;
    private JTextField repairField;
    private JLabel priceLabel;

    public BookingFormPanel() {
        this(null);
    }

    public BookingFormPanel(Booking booking) {
        if (booking != null) {
            this.initialRegNr = booking.getVehicle().getRegNr().getRegNr();
            this.initialModel = booking.getVehicle().getModel();
            this.initialYearModel = String.valueOf(booking.getVehicle().getProductionYear());
            this.initialEmail = booking.getEmail().getEmail();
            this.initialDate = booking.getDate().toString();
            if (booking instanceof Booking.Repair) {
                this.isRepair = true;
                this.initialRepairDescription = ((Booking.Repair) booking).getDescription();
            }
        }
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        regField = new ValidatedTextField(6, RegNr::isValid);
        regField.setText(initialRegNr);

        // Additional text field for repair details in a fixed-size panel
        repairPanel = new JPanel();
        repairPanel.setLayout(new BoxLayout(repairPanel, BoxLayout.Y_AXIS));
        repairPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel repairLabel = new JLabel("Reparationsdetaljer:");
        repairField = new JTextField(20);
        repairField.setText(initialRepairDescription);

        repairPanel.add(repairLabel);
        repairPanel.add(repairField);
        repairPanel.setVisible(isRepair);

        modelField = new JTextField(20);
        modelField.setText(initialModel);
        yearField = new ValidatedTextField(4, text -> {
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
        yearField.setText(initialYearModel);
        emailField = new ValidatedTextField(20, Email::isValid);
        emailField.setText(initialEmail);
        dateField = new ValidatedTextField(20, text -> {
            try {
                java.time.LocalDate.parse(text);
                return true;
            } catch (Exception ex) {
                return false;
            }
        });
        dateField.setText(initialDate);

        regField.setValidationListener((valid) -> {
            validReg = valid;
            onFormChanged();
        });
        yearField.setValidationListener((valid) -> {
            validYear = valid;
            onFormChanged();
        });
        emailField.setValidationListener((valid) -> {
            validEmail = valid;
            onFormChanged();
        });
        dateField.setValidationListener((valid) -> {
            validDate = valid;
            onFormChanged();
        });

        priceLabel = new JLabel();
        setPrice(0);

        createComponents();
    }

    private void createComponents() {
        removeAll();

        add(new JLabel("Reg.nr:"));
        add(regField);
        add(new JLabel("Modell:"));
        add(modelField);
        add(new JLabel("Årsmodell:"));
        add(yearField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Datum:"));
        add(dateField);
        add(repairPanel);
        add(priceLabel);

        repairPanel.setVisible(isRepair);

        revalidate();
        repaint();
    }

    public void setOnChangeListener(Runnable onChange) {
        this.onChange = onChange;
    }

    public boolean isFormValid() {
        return validReg && validYear && validEmail && validDate;
    }

    public String getRegNr() {
        return regField.getText();
    }

    public String getModel() {
        return modelField.getText();
    }

    public String getYearModel() {
        return yearField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getDate() {
        return dateField.getText();
    }

    public void setIsRepair(boolean isRepair) {
        this.isRepair = isRepair;
        createComponents();
    }

    public boolean isRepair() {
        return isRepair;
    }

    public String getRepairDescription() {
        return repairField.getText();
    }

    public void setPrice(float price) {
        if (price <= 0) {
            priceLabel.setText("Pris: Baserad på bokningstyp");
            return;
        }
        priceLabel.setText("Pris: " + price + " SEK");
        createComponents();
    }

    private void onFormChanged() {
        if (onChange != null) {
            onChange.run();
        }
    }
}
