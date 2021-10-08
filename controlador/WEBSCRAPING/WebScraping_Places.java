package controlador.WEBSCRAPING;

import controlador.SQL.SQLProcessData;
import controlador.Support_Functions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.geografia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraping_Places {
    public String lugares_placeFromLink(String string, char char1, int index1, char char2, int index2){
        String new_text = "";
        int i = 1;
        int j = 1;
        boolean flag = false;
        for (char c : string.toCharArray()) {          
            if (c == char2 && j == index2) flag = false;            
            if (flag) new_text += String.valueOf(c);
            if (c == char1 && i == index1) flag = true;
            if (c == char1) i++;
            if (c == char2) j++;
        }
        return new_text;
    }
    public String lugares_cutIssuesInText(String text, char character){
        String new_text = "";
        List<String> caracteres = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.toCharArray()[i] == character) {
                caracteres.remove(caracteres.size()-1);
                break;
            }
            if (!Support_Functions.isNumber(String.valueOf(text.toCharArray()[i]))) {
                if (text.toCharArray()[i] != '.' && !(text.toCharArray()[i] == ' ' && text.toCharArray()[i-1] == '.')) {
                    caracteres.add(String.valueOf(text.toCharArray()[i]));
                }
            }
        }
        for (String c : caracteres) {
            new_text += c;
        }
        return new_text.toLowerCase();
    }
    public void lugares_set(){
        List<String> oceania = new ArrayList<>();
        oceania.add("nueva-zelanda");
        lugares_llenar(1, "oceania", oceania);
        
        List<String> america_norte = new ArrayList<>();
        america_norte.add("estados-unidos");
        america_norte.add("jamaica");
        lugares_llenar(2, "america_norte", america_norte);
        
        List<String> america_central = new ArrayList<>();
        america_central.add("mexico");
        america_central.add("guatemala");
        america_central.add("costa-rica");
        america_central.add("panama");
        america_central.add("el-salvador");
        america_central.add("honduras");
        america_central.add("nicaragua");
        lugares_llenar(3, "america_central", america_central);
        
        List<String> america_sur = new ArrayList<>();
        america_sur.add("peru");
        america_sur.add("argentina");
        america_sur.add("brasil");
        america_sur.add("chile");
        america_sur.add("bolivia");
        america_sur.add("venezuela");
        america_sur.add("ecuador");
        lugares_llenar(4, "america_sur", america_sur);
        
        List<String> asia = new ArrayList<>();
        asia.add("japon");
        asia.add("china");
        asia.add("india");
        asia.add("tailandia");
        lugares_llenar(5, "asia", asia);
        
        List<String> africa = new ArrayList<>();
        africa.add("egipto");
        lugares_llenar(6, "africa", africa);
        
        List<String> europa = new ArrayList<>();
        europa.add("alemania");
        europa.add("espana");
        europa.add("francia");
        europa.add("italia");
        europa.add("inglaterra");
        europa.add("grecia");
        europa.add("noruega");
        lugares_llenar(7, "europa", europa);
    }
    public void lugares_llenar(int ccode, String continente, List<String> paises){
        SQLProcessData sql = new SQLProcessData();
        int i = 1;
        //For CODE========================
        String pdigits;
        String pldigits;
        int pcount = 1;
        int plcount = 1;
        String codigo;
        //================================
        for (String pais : paises) {
            List<String> lugares = crawl_lugares(pais);
            for (String lugar : lugares) {
                //Assign Codes================================
                pdigits = ""+pcount;
                if (pcount < 10) pdigits = "0"+pcount;
                pldigits = ""+plcount;
                if (plcount < 10) pldigits = "0"+plcount;
                codigo = ccode + pdigits + pldigits;
                //============================================
                //Adding to Database==========================
                geografia g = new geografia(codigo,continente, pais, lugar);
                sql.addLugar(g);
                //============================================
                System.out.println("Adding new place ["+i+"]: "+codigo+" | "+continente+" | "+pais+" | "+lugar);
                plcount++;
                i++;
            }
            pcount++;
            plcount = 1;
            i = 1;
        }
        System.out.println("=======================================================================================");
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
    public List<String> crawl_lugares(String pais){
        List<String> places = new ArrayList<>();
        String url = "https://lanzateyviaja.com/"+pais+"/lugares-turisticos";
        String selected_element = "h2";
        int con = 1;       
        if (pais.equals(lugares_placeFromLink(getHTML(url).location(),'/',3,'/',4))) {
            Elements lugares = null;
            do {
                try {
                    lugares = getHTML(url).select(selected_element);
                    System.out.println("Trying to find list of elements: ["+selected_element+"], attemp: "+con++);
                    if (!lugares.isEmpty()) {
                        System.out.println("List finded");
                        System.out.println("Successful process");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (lugares.isEmpty());
            int i = 1;
            for (Element lugar : lugares) {
                if (Support_Functions.isNumber(String.valueOf(lugar.text().toCharArray()[0]))) {
                    places.add(Support_Functions.rit(Support_Functions.normalCutAfterText(lugares_cutIssuesInText(lugar.text(), '('), ','), ' ', '-'));
                    if (i == 15) break;
                    i++;
                }
            }
        } else {
            System.out.println("Not found Country ["+pais+"] in WebSide");
        }  
        return places;
    }
    
    public static void main(String[] args) {
        WebScraping_Places object = new WebScraping_Places();
        //object.lugares_set();
    }
}
