package modelo;

public class voto {
    private int codcom;
    private int codus;
    private int valor;

    public voto(int codcom, int codus, int valor) {
        this.codcom = codcom;
        this.codus = codus;
        this.valor = valor;
    }

    public int getCodcom() {
        return codcom;
    }

    public void setCodcom(int codcom) {
        this.codcom = codcom;
    }

    public int getCodus() {
        return codus;
    }

    public void setCodus(int codus) {
        this.codus = codus;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
