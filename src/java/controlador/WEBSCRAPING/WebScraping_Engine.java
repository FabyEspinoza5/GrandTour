package controlador.WEBSCRAPING;

import controlador.SQL.SQLProcessData;
import controlador.Support_Functions;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.ArrayList;
import modelo.paquete;

public class WebScraping_Engine {
    List<paquete> lista = new ArrayList<>();
    public void paquetes_print_list(){
        int i = 1;
        for (paquete pack:lista) {
            System.out.println("Paquete ["+(i++)+"] ");
            System.out.println("CODIGO: "+pack.getCodigo());
            System.out.println("LINK-IMAGEN: "+pack.getImg());
            System.out.println("NOMBRE: "+pack.getName());
            System.out.println("DESCRIPCION: "+pack.getDescription());
            System.out.println("COSTO: "+pack.getCost());
            System.out.println("LINK: "+pack.getLink().length());
            System.out.println("=====================================================================================");
        }      
    }
    public String paquetes_prepareTextForHTML(List<String> things, Element paquete) {
        String new_text = "";
        for (String thing : things) {
            if (!paquete.select(thing).eq(0).text().equals("")) {
                if (!things.get(0).equals(thing)) {
                    new_text += "<br>";
                }
                new_text += paquete.select(thing).eq(0).text();
            }
        }
        return new_text;
    }
    public String paquetes_cutLinksForRef(String string, String rat){
        String aux = "";
        String clear_link = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.toCharArray()[i] == '&') {
                for (int j = i; j < i+5; j++) {
                    aux += String.valueOf(string.toCharArray()[j]);
                }
            }
            if (aux.equals(rat)) {
                break;
            }
            clear_link += String.valueOf(string.toCharArray()[i]);
            aux = "";
        }
        return clear_link;
    }
    public Document getHTML(String url){
        Document html = null;
        while (true){
            try {
                html = Jsoup.connect(url).get();
                System.out.print("Successful HTML Connection | ");
                break;
            }catch (IOException e){
                System.out.println("Error al obtener codigo HTML");
            }
        }
        return html;
    }
    @SuppressWarnings("null")
    public void crawl(String packCode, String urlwp, String block, String cimg, String cname, List<String> ldesc, String ccosto, String cplink, String wclink) {
        String selected_element = block;
        int con = 1;
        Elements paquetes = null;
        //TRYING TO GET LIST==================================================================================================================
        do {
            try {
                paquetes = getHTML(urlwp).select(selected_element);
                System.out.println("Trying to find list of elements: ["+selected_element+"], with index: [0], attemp: "+con++);
                if (!paquetes.isEmpty()) {
                    System.out.println("List finded.");
                    System.out.println("Successful process");
                }
            } catch(Exception e) {
                 System.out.println(e);
            }
        } while (paquetes.isEmpty());
        //====================================================================================================================================
        int u = 1;
        SQLProcessData sql = new SQLProcessData();
        //GETTING ELEMENTS FROM LIST==========================================================================================================
        for (Element pack : paquetes) {
            try {
                //=CODIGO => String============================================================|
                String indexdigit = ""+u;
                if (u < 10) indexdigit = "0"+u;
                String codigo = packCode + indexdigit;
                //=IMAGEN => String============================================================|
                String img = pack.select(cimg).eq(0).attr("src");
                //=NOMBRE => String============================================================|
                String name = pack.select(cname).eq(0).text();
                //=DESCRIPCION => List<String>=================================================|
                String description = paquetes_prepareTextForHTML(ldesc, pack);
                //=COSTO => Double=============================================================|
                String a = pack.select(ccosto).eq(0).text();
                String b = Support_Functions.rit(a, 'S', ' ');
                String c = Support_Functions.rit(b, '/', ' ');
                String d = Support_Functions.rit(c, '.', ' ');
                String e = Support_Functions.normalizeString(d);
                String f = Support_Functions.rit(e, ',', '.');
                double cost = Double.parseDouble(f);
                //=LINK => String==============================================================|
                String link = paquetes_cutLinksForRef(pack.select(cplink).eq(0).select("a").eq(0).attr("href"), wclink);
                //Agregar a la BD==============================================|
                paquete p = new paquete(codigo, img, name, description, cost, link);
                sql.addPaquete(p);
                //=============================================================|
                System.out.println("Adding package N°: ["+(u++)+"]");
                //lista.add(new paquete(codigo,img,name,description,cost,link));
            } catch(NumberFormatException e) {
                 System.out.println(e);
            }
        }
        //====================================================================================================================================
    }
    
    public static void main(String[] args){
        WebScraping_Engine object = new WebScraping_Engine();
        /*=Ingredients for Crawl================================================
        | Codigo de Paquete [Tipo (H/T/V) + Codigo de Lugar]                   |
        | URL [URL de la pagina + Lugar insertado]                             |
        | Bloque de producto                                                   |
        | CSS-Class de Imagen                                                  |
        | CSS-Class de Nombre                                                  |
        | Lista de CSS-Class de Descripciones                                  |
        | CSS-Class de Costo                                                   |
        | CSS-Class de Contenedor de Link                                      |
        ========================================================================*/
        
        //Booking.com Ingredients for Crawl================================================================================
        String packCode = 'H'+"30108";
        String place = "catedral-de-guadalajara";
        String urlwp = "https://www.booking.com/searchresults.es.html?label=mx-XGBzwpzeodm1y8PeVD_DKAS392514988522%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atikwd-63345650%3Alp9073192%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YdnZzv7u3SiOco5fpqS0M1M&sid=5f26170f99c7ae1e4b7ef28c4b5bdc6c&aid=306396&sb=1&sb_lp=1&src=index&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Findex.es.html%3Faid%3D306396%3Blabel%3Dmx-XGBzwpzeodm1y8PeVD_DKAS392514988522%253Apl%253Ata%253Ap1%253Ap2%253Aac%253Aap%253Aneg%253Afi%253Atikwd-63345650%253Alp9073192%253Ali%253Adec%253Adm%253Appccp%253DUmFuZG9tSVYkc2RlIyh9YdnZzv7u3SiOco5fpqS0M1M%3Bsid%3D5f26170f99c7ae1e4b7ef28c4b5bdc6c%3Bsb_price_type%3Dtotal%3Bsrpvid%3De6516e8f480400e0%26%3B&ss="+place+"&offset="+0+"&checkin_year="+2022+"&checkin_month="+1+"&checkin_monthday="+10+"&checkout_year="+2022+"&checkout_month="+1+"&checkout_monthday="+11+"&order=price";
        String block = "div.a233d9c18f";
        String cimg = "img.cbe8eb8ba3";
        String cname = "div.b2e4e409fd";
        List<String> desc_list = new ArrayList<>();
            desc_list.add("span._5e1912b06f");
            desc_list.add("div._0b20a2246c");
            desc_list.add("div._0a9e07b4f0");
            desc_list.add("div.a78316079f");
        String ccosto = "span._2de857cfd1";
        String cplink = "div._63299949e4";
            String wclink = "&sid=";
        //=================================================================================================================
        
        object.crawl(packCode, urlwp, block, cimg, cname, desc_list, ccosto, cplink, wclink);
    }   
}
