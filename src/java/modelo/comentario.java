package modelo;

public class comentario {
    private int codcoment;
    private String codpack;
    private String nomus;
    private String coment;
    private String fecha;
    private int codanswer;
    private int voto;

    public comentario(int codcoment, String codpack, String nomus, String coment, String fecha, int codanswer, int voto) {
        this.codcoment = codcoment;
        this.codpack = codpack;
        this.nomus = nomus;
        this.coment = coment;
        this.fecha = fecha;
        this.codanswer = codanswer;
        this.voto = voto;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public int getCodcoment() {
        return codcoment;
    }

    public void setCodcoment(int codcoment) {
        this.codcoment = codcoment;
    }

    public String getCodpack() {
        return codpack;
    }

    public void setCodpack(String codpack) {
        this.codpack = codpack;
    }

    public String getNomus() {
        return nomus;
    }

    public void setNomus(String nomus) {
        this.nomus = nomus;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodanswer() {
        return codanswer;
    }

    public void setCodanswer(int codanswer) {
        this.codanswer = codanswer;
    }

    
}
