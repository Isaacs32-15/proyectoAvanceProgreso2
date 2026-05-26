public class Usuario {
    private int Codigo;
    private String Nombre;
    private String Titulo;

    public Usuario(int codigo, String nombre, String titulo) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Titulo = titulo;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    @Override
    public String toString() {
        return "|| Usuario: " +
                "-Codigo: " + Codigo +
                ",-Nombre: '" + Nombre + '\'' +
                ", -Titulo: '" + Titulo + '\'' + "-\n" ;
    }
}
