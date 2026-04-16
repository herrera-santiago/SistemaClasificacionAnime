package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class UiTheme {

    public static final Color BG_APP = new Color(245, 247, 252);
    public static final Color BG_CARD = Color.WHITE;
    public static final Color BORDER = new Color(224, 229, 240);
    public static final Color PRIMARY = new Color(79, 70, 229);
    public static final Color PRIMARY_DARK = new Color(67, 56, 202);
    public static final Color DANGER = new Color(220, 38, 38);
    public static final Color TEXT_MAIN = new Color(17, 24, 39);
    public static final Color TEXT_MUTED = new Color(107, 114, 128);

    private static boolean initialized = false;

    private UiTheme() {
    }

    public static void initializeGlobalTheme() {
        if (initialized) {
            return;
        }

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            // seguimos con LAF por defecto
        }

        Font base = new Font("SansSerif", Font.PLAIN, 14);
        Font title = base.deriveFont(Font.BOLD, 24f);

        UIManager.put("defaultFont", base);
        UIManager.put("Label.font", base);
        UIManager.put("Button.font", base.deriveFont(Font.BOLD));
        UIManager.put("TextField.font", base);
        UIManager.put("ComboBox.font", base);
        UIManager.put("Table.font", base);
        UIManager.put("TableHeader.font", base.deriveFont(Font.BOLD));
        UIManager.put("Spinner.font", base);
        UIManager.put("OptionPane.messageFont", base);
        UIManager.put("OptionPane.buttonFont", base.deriveFont(Font.BOLD));
        UIManager.put("Panel.background", BG_APP);
        UIManager.put("OptionPane.background", BG_APP);

        UIManager.put("nimbusBase", PRIMARY);
        UIManager.put("nimbusBlueGrey", new Color(228, 232, 244));
        UIManager.put("control", BG_APP);
        UIManager.put("text", TEXT_MAIN);

        UIManager.put("App.titleFont", title);
        initialized = true;
    }

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );
    }

    public static JPanel cardLayout(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setOpaque(true);
        panel.setBackground(BG_CARD);
        panel.setBorder(cardBorder());
        return panel;
    }

    public static void stylePrimaryButton(AbstractButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_DARK),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleSecondaryButton(AbstractButton button) {
        button.setForeground(TEXT_MAIN);
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleDangerButton(AbstractButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(DANGER);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(153, 27, 27)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }
}
