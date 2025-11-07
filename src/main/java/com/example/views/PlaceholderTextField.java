package com.example.views;

import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    public PlaceholderTextField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            java.awt.FontMetrics fm = g.getFontMetrics();
            int padding = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g.setColor(java.awt.Color.GRAY);
            g.drawString(placeholder, 5, padding);
        }
    }
}
