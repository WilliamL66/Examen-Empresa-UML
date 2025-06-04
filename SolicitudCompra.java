import java.util.ArrayList;

public class SolicitudCompra implements Calculable {
    private String numeroSolicitud;
    private String departamento;
    private EstadoSolicitud estado;
    private ArrayList<ItemSolicitud> items;

    public SolicitudCompra(String numeroSolicitud, String departamento) {
        this.numeroSolicitud = numeroSolicitud;
        this.departamento = departamento;
        this.estado = EstadoSolicitud.SOLICITADA;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemSolicitud item) {
        items.add(item);
    }

    @Override
    public double calcularCostoTotal() {
        double total = 0;
        for (Calculable item : items) {
            total += item.calcularCostoTotal(); 
        }
        return total;
    }

    public void cambiarEstado(EstadoSolicitud nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getNumeroSolicitud() { return numeroSolicitud; }
    public EstadoSolicitud getEstado() { return estado; }
    public ArrayList<ItemSolicitud> getItems() { return items; }
    public String getDepartamento() { return departamento; }
}
