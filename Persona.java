public abstract class Persona {
    protected String nombre;
    protected String telefono;
    protected String email;

    public Persona(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public abstract void mostrarInformacion();

    // Getters
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
}
