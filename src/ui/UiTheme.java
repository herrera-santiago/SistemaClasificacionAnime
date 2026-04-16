package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class UiTheme {

    // Paleta inspiración Boca Juniors
    public static final Color BOCA_AZUL = new Color(0, 42, 121);
    public static final Color BOCA_AZUL_OSCURO = new Color(0, 27, 78);
    public static final Color BOCA_AZUL_CLARO = new Color(25, 75, 170);
    public static final Color BOCA_ORO = new Color(255, 198, 30);
    public static final Color BOCA_ORO_OSCURO = new Color(219, 160, 0);

    public static final Color BG_APP = new Color(242, 246, 255);
    public static final Color BG_CARD = Color.WHITE;
    public static final Color BORDER = new Color(201, 214, 242);
    public static final Color TEXT_MAIN = new Color(15, 23, 42);
    public static final Color TEXT_MUTED = new Color(71, 85, 105);

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
        Font cardTitle = base.deriveFont(Font.BOLD, 18f);

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

        UIManager.put("nimbusBase", BOCA_AZUL);
        UIManager.put("nimbusBlueGrey", new Color(221, 231, 255));
        UIManager.put("control", BG_APP);
        UIManager.put("text", TEXT_MAIN);

        UIManager.put("App.titleFont", title);
        UIManager.put("App.cardTitleFont", cardTitle);
        initialized = true;
    }

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOCA_AZUL, 1),
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
        button.setForeground(BOCA_AZUL_OSCURO);
        button.setBackground(BOCA_ORO);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOCA_ORO_OSCURO),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleSecondaryButton(AbstractButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(BOCA_AZUL);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOCA_AZUL_OSCURO),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleDangerButton(AbstractButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(185, 28, 28));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(127, 29, 29)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
    }

    public static JLabel createCardTitle(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(BOCA_AZUL_OSCURO);
        label.setFont(((Font) UIManager.get("App.cardTitleFont")));
        return label;
    }
}
