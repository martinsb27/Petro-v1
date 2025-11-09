import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

// Interfaz gráfica principal del sistema
public class InventarioFrame extends JFrame {

    private JTextField txtNombre, txtPrecio, txtCantidad;
    private JComboBox<String> cmbPlataforma, cmbGenero;
    private InventarioManager manager;

    public InventarioFrame() {
        super("Registro de Inventario - Tienda Peto 🎮");
        manager = new InventarioManager();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campos del formulario
        panel.add(new JLabel("Nombre del videojuego:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Plataforma:"));
        cmbPlataforma = new JComboBox<>(new String[]{"PS5", "Xbox", "Nintendo Switch", "PC"});
        panel.add(cmbPlataforma);

        panel.add(new JLabel("Género:"));
        cmbGenero = new JComboBox<>(new String[]{"Acción", "Aventura", "RPG", "Deportes"});
        panel.add(cmbGenero);

        panel.add(new JLabel("Precio (S/):"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad en stock:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        // Botones
        JButton btnAgregar = new JButton("Agregar al Inventario");
        JButton btnLimpiar = new JButton("Limpiar Campos");
        JButton btnVer = new JButton("Ver Inventario");

        panel.add(btnAgregar);
        panel.add(btnLimpiar);
        panel.add(new JLabel()); // espacio
        panel.add(btnVer);

        add(panel, BorderLayout.CENTER);

        // Acción: Agregar
        btnAgregar.addActionListener((ActionEvent e) -> {
            String nombre = txtNombre.getText().trim();
            String plataforma = (String) cmbPlataforma.getSelectedItem();
            String genero = (String) cmbGenero.getSelectedItem();
            String precioStr = txtPrecio.getText().trim();
            String cantidadStr = txtCantidad.getText().trim();

            if (nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);

                if (precio <= 0 || cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser positivos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                manager.agregarVideojuego(new Videojuego(nombre, plataforma, genero, precio, cantidad));
                JOptionPane.showMessageDialog(this, "Videojuego agregado exitosamente 🎉");
                limpiarCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en precio y cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción: Limpiar campos
        btnLimpiar.addActionListener((ActionEvent e) -> limpiarCampos());

        // Acción: Ver inventario
        btnVer.addActionListener((ActionEvent e) -> mostrarInventario());

        setVisible(true);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        cmbPlataforma.setSelectedIndex(0);
        cmbGenero.setSelectedIndex(0);
    }

    private void mostrarInventario() {
        java.util.List<Videojuego> lista = manager.getInventario();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay videojuegos en el inventario.", "Inventario Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnas = {"Nombre", "Plataforma", "Género", "Precio (S/)", "Cantidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Videojuego v : lista) {
            modelo.addRow(new Object[]{
                    v.getNombre(),
                    v.getPlataforma(),
                    v.getGenero(),
                    v.getPrecio(),
                    v.getCantidad()
            });
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(550, 250));

        JOptionPane.showMessageDialog(this, scroll, "Inventario Actual 🎮", JOptionPane.INFORMATION_MESSAGE);
    }
}
