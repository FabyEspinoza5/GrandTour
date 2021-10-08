package modelo;
public class paquete {
    private String codigo;
    private String img;
    private String name;
    private String description;
    private double cost;
    private String link;

    public paquete(String codigo, String img, String name, String description, double cost, String link) {
        this.codigo = codigo;
        this.img = img;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

