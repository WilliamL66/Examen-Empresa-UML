public class ItemSolicitud implements Calculable {
    private Producto producto;
    private int cantidad;

    public ItemSolicitud(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public double calcularCostoTotal() {
        return producto.getPrecioUnitario() * cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}
