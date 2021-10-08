package modelo;

public class geografia {
    private String codigo;
    private String continente;
    private String pais;
    private String lugar;

    public geografia(String codigo, String continente, String pais, String lugar) {
        this.codigo = codigo;
        this.continente = continente;
        this.pais = pais;
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
}
