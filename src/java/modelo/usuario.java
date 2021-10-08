package modelo;

public class usuario {
    private int codigo;
    private String email;
    private String nombre;
    private String password;

    public usuario(int codigo, String email, String nombre, String password) {
        this.codigo = codigo;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
