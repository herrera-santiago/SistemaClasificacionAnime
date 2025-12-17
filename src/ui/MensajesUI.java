package ui;

import javax.swing.*;

public class MensajesUI {

    public static void mostrarError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(String msg) {
        int opt = JOptionPane.showConfirmDialog(null, msg, "Confirmar",
                JOptionPane.YES_NO_OPTION);
        return opt == JOptionPane.YES_OPTION;
    }
}
