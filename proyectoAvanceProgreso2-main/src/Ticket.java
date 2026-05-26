public class Ticket {

    private int codigo;
    private String titulo;
    private String descripcion;
    private String estado;
    private Usuario usuario;

    public Ticket(int codigo, String titulo, String descripcion, String estado, Usuario usuario) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Ticket-" +
                " Codigo: " + codigo +
                ", Titulo: '" + titulo + '\'' +
                ", Descripcion: '" + descripcion + '\'' +
                ", Estado: '" + estado + '\'' +
                ", Usuario: " + usuario;
    }
}