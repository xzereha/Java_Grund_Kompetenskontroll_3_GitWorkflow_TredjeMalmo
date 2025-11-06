package com.example.views;

import javax.swing.JTextField;

public class ValidatedTextField extends JTextField {
    private boolean valid;
    private ITextValidation validator;
    private ValidationListener listener;

    public ValidatedTextField(int columns, ITextValidation validator) {
        super(columns);
        this.valid = false;
        this.validator = validator;
        this.listener = null;

        this.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validateTextField();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validateTextField();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validateTextField();
            }
        });
    }

    public void setValidationListener(ValidationListener listener) {
        this.listener = listener;
    }

    public void validateTextField() {
        valid = validator.isValid(getText());
        if (valid) {
            setBorder(javax.swing.UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        } else {
            setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));
        }
        if (listener != null) {
            listener.valid(valid);
        }
    }

    @FunctionalInterface
    public interface ValidationListener {
        void valid(boolean isValid);
    }
}
