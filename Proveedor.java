import java.util.ArrayList;

public class Proveedor extends Persona {
    private final String idProveedor;
    private final ArrayList<Producto> productos;

    public Proveedor(String idProveedor, String nombre, String telefono, String email) {
        super(nombre, telefono, email);
        this.idProveedor = idProveedor;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("ID: " + idProveedor);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        for (Producto p : productos) {
            System.out.println(" - " + p.getNombre() + " ($" + p.getPrecioUnitario() + ")");
        }
    }

    public String getIdProveedor() { return idProveedor; }
    public ArrayList<Producto> getProductos() { return productos; }
}
