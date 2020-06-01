package gui;

/**
 * Listens for preferences dialog for MainFrame
 */
public interface PrefsListener {
    void preferencesSet(String user, String password, int port);
}
