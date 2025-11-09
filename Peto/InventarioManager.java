import java.util.ArrayList;
import java.util.List;

// Clase para manejar la lista de videojuegos en el inventario
public class InventarioManager {

    private List<Videojuego> inventario;

    public InventarioManager() {
        inventario = new ArrayList<>();
    }

    // Agregar un videojuego al inventario
    public void agregarVideojuego(Videojuego juego) {
        inventario.add(juego);
    }

    // Obtener toda la lista del inventario
    public List<Videojuego> getInventario() {
        return inventario;
    }

    // Limpiar todo el inventario (si se necesita)
    public void limpiarInventario() {
        inventario.clear();
    }
}
