package com.example.views;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.example.Models.Email;
import com.example.Models.RegNr;

public class AddBookingView extends JDialog {
    private boolean validReg = false;
    private boolean validEmail = false;

    public AddBookingView() {
        setTitle("Ny bokning");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton addBookingBtn = new JButton("Add");
        JTextField regNr = new JTextField(20);
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
        JTextField vehicle = new JTextField(20);
        panel.add(new JLabel("Reg.nr:"));
        panel.add(regNr);
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(new JLabel("Datum:"));
        panel.add(date);
        panel.add(new JLabel("Fordon:"));
        panel.add(vehicle);
        addBookingBtn.setEnabled(false);
        addBookingBtn.addActionListener(ev -> {
            String val1 = regNr.getText();
            String val2 = email.getText();
            String val3 = date.getText();
            String val4 = vehicle.getText();
            // TODO: Add your logic here to handle the values
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
