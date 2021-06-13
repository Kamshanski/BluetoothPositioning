package com.kamshanski.blepositioning.view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public abstract class TextChangedListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            onTextChanged(e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            onTextChanged(e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    public abstract void onTextChanged(String value);
}
