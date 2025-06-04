import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaCompras {

    private static final ArrayList<Proveedor> proveedores = new ArrayList<>();
    private static final ArrayList<Producto> productos = new ArrayList<>();
    private static final ArrayList<SolicitudCompra> solicitudes = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion = -1;
            
            cargarProveedores();
            cargarProductos();
            cargarSolicitudes();



            do {
                System.out.println("\n\033[1;34m╔══════════════════════════════════════════════╗");
                System.out.println("║       SISTEMA DE GESTIÓN DE COMPRAS ERP      ║");
                System.out.println("╚══════════════════════════════════════════════╝\033[0m");
                System.out.println("1. Registrar proveedor");
                System.out.println("2. Registrar producto");
                System.out.println("3. Registrar solicitud de compra");
                System.out.println("4. Listar proveedores");
                System.out.println("5. Listar productos");
                System.out.println("6. Listar solicitudes de compra");
                System.out.println("7. Buscar proveedor por ID");
                System.out.println("8. Buscar producto por nombre");
                System.out.println("9. Buscar solicitud por número");
                System.out.println("13. Aprobar / Rechazar solicitud de compra");
                System.out.println("14. Calcular total de una solicitud");
                System.out.println("15. Salir");
                
                while (true) {
                    System.out.print("Seleccione una opción: ");
                    if (sc.hasNextInt()) {
                        opcion = sc.nextInt();
                        sc.nextLine();
                        break;
                    } else {
                        System.out.println("Error: Ingrese un número válido.");
                        sc.nextLine(); 
                    }
                }

                System.out.println("─────────────────────────────────────────────");
                
                switch (opcion) {
                    case 1 -> registrarProveedor(sc);
                    case 2 -> registrarProducto(sc);
                    case 3 -> registrarSolicitud(sc);
                    case 4 -> listarProveedores();
                    case 5 -> listarProductos();
                    case 6 -> listarSolicitudes();
                    case 7 -> buscarProveedorPorId(sc);
                    case 8 -> buscarProductoPorNombre(sc);
                    case 9 -> buscarSolicitudPorNumero(sc);
                    case 13 -> aprobarRechazarSolicitud(sc);
                    case 14 -> calcularTotalSolicitud(sc);
                    case 15 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }
                
            } while (opcion != 15);
        }
    }

    private static void registrarProveedor(Scanner sc) {
        System.out.print("ID del proveedor: ");
        String id = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Proveedor proveedor = new Proveedor(id, nombre, telefono, email);
        proveedores.add(proveedor);

        guardarProveedores(); 

        System.out.println("\033[1;32mProveedor registrado correctamente.\033[0m");
    }


    private static void registrarProducto(Scanner sc) {
        System.out.print("ID del producto: ");
        String id = sc.nextLine();

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Precio unitario: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        Producto producto = new Producto(id, nombre, precio);
        productos.add(producto);

        guardarProductos();  

        System.out.println("\033[1;32mProducto registrado correctamente.\033[0m");
    }


    private static void registrarSolicitud(Scanner sc) {
        System.out.print("Número de solicitud: ");
        String numero = sc.nextLine();

        System.out.print("Departamento que solicita: ");
        String departamento = sc.nextLine();

        SolicitudCompra solicitud = new SolicitudCompra(numero, departamento);

        boolean agregarMas;
        do {
            System.out.print("ID del producto a agregar: ");
            String idProducto = sc.nextLine();
            Producto producto = buscarProductoPorId(idProducto);

            if (producto != null) {
                System.out.print("Cantidad: ");
                int cantidad = sc.nextInt();
                sc.nextLine(); 

                ItemSolicitud item = new ItemSolicitud(producto, cantidad);
                solicitud.agregarItem(item);

                System.out.println("Producto agregado.");
            } else {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Agregar otro producto? (s/n): ");
            agregarMas = sc.nextLine().equalsIgnoreCase("s");

        } while (agregarMas);

        solicitudes.add(solicitud);
        System.out.println("Solicitud registrada correctamente.");

        guardarSolicitudes();

    }

    private static void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                p.mostrarInformacion();
                System.out.println("-----");
            }
        }
    }

    private static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                System.out.println("---------------------------------------------");
                System.out.println("ID: " + p.getIdProducto() + " | Nombre: " + p.getNombre() + " | Precio: $" + p.getPrecioUnitario());
                System.out.println("---------------------------------------------");
            }
        }
    }

    private static void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (SolicitudCompra s : solicitudes) {
                System.out.println("Número: " + s.getNumeroSolicitud() +
                                " | Departamento: " + s.getDepartamento() +
                                " | Estado: " + s.getEstado() +
                                " | Total: $" + s.calcularCostoTotal());
            }
        }
    }

    private static Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getIdProducto().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static void buscarProveedorPorId(Scanner sc) {
        System.out.print("Ingrese ID del proveedor: ");
        String id = sc.nextLine();

        for (Proveedor p : proveedores) {
            if (p.getIdProveedor().equalsIgnoreCase(id)) {
                p.mostrarInformacion();
                return;
            }
        }

        System.out.println("Proveedor no encontrado.");
    }

    private static void buscarProductoPorNombre(Scanner sc) {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.nextLine();

        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("ID: " + p.getIdProducto() + " | Nombre: " + p.getNombre() + " | Precio: $" + p.getPrecioUnitario());
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    private static void buscarSolicitudPorNumero(Scanner sc) {
        System.out.print("Ingrese número de solicitud: ");
        String numero = sc.nextLine();

        for (SolicitudCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                System.out.println("Departamento: " + s.getDepartamento());
                System.out.println("Estado: " + s.getEstado());
                System.out.println("Total: $" + s.calcularCostoTotal());
                return;
            }
        }

        System.out.println("Solicitud no encontrada.");
    }

    private static void aprobarRechazarSolicitud(Scanner sc) {
    System.out.print("Ingrese número de solicitud: ");
    String numero = sc.nextLine();

    for (SolicitudCompra s : solicitudes) {
        if (s.getNumeroSolicitud().equalsIgnoreCase(numero)) {
            System.out.print("¿Desea aprobar (A) o rechazar (R) la solicitud? ");
            String opcion = sc.nextLine().toUpperCase();

            if (opcion.equals("A")) {
                s.cambiarEstado(EstadoSolicitud.APROBADA);
                System.out.println("\033[1;32m✓ Solicitud aprobada.\033[0m");
            } else if (opcion.equals("R")) {
                s.cambiarEstado(EstadoSolicitud.RECHAZADA);
                System.out.println("\033[1;31m✗ Solicitud rechazada.\033[0m");
            } else {
                System.out.println("\033[1;33mOpción inválida. Use 'A' para aprobar o 'R' para rechazar.\033[0m");
            }
            return;
        }
    }

    System.out.println("\033[1;31mSolicitud no encontrada.\033[0m");
}


    private static void calcularTotalSolicitud(Scanner sc) {
        System.out.print("Ingrese número de solicitud: ");
        String numero = sc.nextLine();

        for (SolicitudCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                double total = s.calcularCostoTotal();
                System.out.println("Total de la solicitud: $" + total);
                return;
            }
        }

        System.out.println("Solicitud no encontrada.");
    }

    private static void guardarProveedores() {
        List<String> lineas = new ArrayList<>();
        for (Proveedor p : proveedores) {
            lineas.add(p.getIdProveedor() + ";" + p.getNombre() + ";" + p.getTelefono() + ";" + p.getEmail());
        }
        ArchivoUtil.guardarLineas("proveedores.csv", lineas);
    }

    private static void cargarProveedores() {
        List<String> lineas = ArchivoUtil.leerLineas("proveedores.csv");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                Proveedor p = new Proveedor(partes[0], partes[1], partes[2], partes[3]);
                proveedores.add(p);
            }
        }
    }

    private static void guardarProductos() {
        List<String> lineas = new ArrayList<>();
        for (Producto p : productos) {
            lineas.add(p.getIdProducto() + ";" + p.getNombre() + ";" + p.getPrecioUnitario());
        }
        ArchivoUtil.guardarLineas("productos.csv", lineas);
    }

    private static void cargarProductos() {
        List<String> lineas = ArchivoUtil.leerLineas("productos.csv");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length == 3) {
                String id = partes[0];
                String nombre = partes[1];
                double precio = Double.parseDouble(partes[2]);
                Producto producto = new Producto(id, nombre, precio);
                productos.add(producto);
            }
        }
    }


    private static void guardarSolicitudes() {
        List<String> lineas = new ArrayList<>();

        for (SolicitudCompra s : solicitudes) {
            StringBuilder items = new StringBuilder();
            for (ItemSolicitud item : s.getItems()) {
                items.append(item.getProducto().getIdProducto())
                    .append(",")
                    .append(item.getCantidad())
                    .append("|");
            }

            
            if (items.length() > 0) items.setLength(items.length() - 1);

            lineas.add(s.getNumeroSolicitud() + ";" +
                    s.getDepartamento() + ";" +
                    s.getEstado().name() + ";" +
                    items);
        }

        ArchivoUtil.guardarLineas("solicitudes.csv", lineas);
    }

    private static void cargarSolicitudes() {
        List<String> lineas = ArchivoUtil.leerLineas("solicitudes.csv");

        for (String linea : lineas) {
            String[] partes = linea.split(";", 4);
            if (partes.length == 4) {
                String numero = partes[0];
                String departamento = partes[1];
                EstadoSolicitud estado = EstadoSolicitud.valueOf(partes[2]);
                String itemsTexto = partes[3];

                SolicitudCompra solicitud = new SolicitudCompra(numero, departamento);
                solicitud.cambiarEstado(estado);

                String[] items = itemsTexto.split("\\|");
                for (String item : items) {
                    String[] datos = item.split(",");
                    if (datos.length == 2) {
                        String idProd = datos[0];
                        int cantidad = Integer.parseInt(datos[1]);

                        Producto prod = buscarProductoPorId(idProd);
                        if (prod != null) {
                            solicitud.agregarItem(new ItemSolicitud(prod, cantidad));
                        }
                    }
                }

                solicitudes.add(solicitud);
            }
        }
    }






   

}
