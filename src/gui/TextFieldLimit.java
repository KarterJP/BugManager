package gui;

import javax.swing.text.*;

class TextFieldLimit extends PlainDocument {
    private int limit;
    TextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    TextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}