public class Producto {
    private String idProducto;
    private String nombre;
    private double precioUnitario;

    public Producto(String idProducto, String nombre, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public String getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
}
