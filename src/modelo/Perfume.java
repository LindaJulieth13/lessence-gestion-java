package modelo;

public class Perfume {

    private int id;
    private String nombre;
    private String marca;
    private String categoria;
    private double precio;
    private int stock;

    public Perfume(String nombre, String marca, String categoria,
                   double precio, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Marca: " + marca +
                " | Categoria: " + categoria +
                " | Precio: $" + precio +
                " | Stock: " + stock;
    }
}