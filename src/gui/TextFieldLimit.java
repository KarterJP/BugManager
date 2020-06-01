package gui;

import javax.swing.text.*;

/**
 * Serves to set limits on text fields
 */
class TextFieldLimit extends PlainDocument {
    private int limit;

    /**
     *
     * @param limit - max number of characters allowed
     */
    TextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     *
     * @param limit
     * @param upper
     */
    TextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    /**
     *
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}