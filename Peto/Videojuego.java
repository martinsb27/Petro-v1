// Clase que representa un videojuego
public class Videojuego {
    private String nombre;
    private String plataforma;
    private String genero;
    private double precio;
    private int cantidad;

    public Videojuego(String nombre, String plataforma, String genero, double precio, int cantidad) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
}

