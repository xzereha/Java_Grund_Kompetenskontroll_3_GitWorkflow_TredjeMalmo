package com.example.views;

import javax.swing.JTextField;

public class ValidatedTextField extends JTextField {
    private boolean valid;
    private ITextValidation validator;

    public ValidatedTextField(int columns, ITextValidation validator) {
        super(columns);
        this.valid = false;
        this.validator = validator;

        this.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validate();
            }
        });
    }

    public boolean isValid() {
        return valid;
    }

    public void validate() {
        valid = validator.isValid(getText());
        if (valid) {
            setBorder(javax.swing.UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        } else {
            setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
        }
    }
}
