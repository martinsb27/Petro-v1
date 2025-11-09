import javax.swing.UIManager;
import javax.swing.SwingUtilities;

// Clase principal que ejecuta el sistema
public class Main {
    public static void main(String[] args) {
        // Aplicar el estilo visual del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(InventarioFrame::new);
    }
}
