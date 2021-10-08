package controlador;

import java.text.Normalizer;

public class Support_Functions {
    public static String normalCutAfterText(String text, char character){
        String new_text = "";
        for (char c : text.toCharArray()) {
            if (c == character) {
                break;
            }
            new_text += String.valueOf(c);
        }
        return new_text;
    }
    public static String rit(String text, char c1, char c2){
        String new_text = "";
        for (char c : text.toCharArray()) {
            if (c == c1) new_text += String.valueOf(c2);
            else new_text += String.valueOf(c);
        }
        return new_text;
    }
    public static String toUpperEachFirstChar(String string){
        String new_text = "";
        for (int i = 0; i < string.length(); i++) {
            char c = string.toCharArray()[i];
            if (i == 0) {
                c = Character.toUpperCase(c);
            }
            if (c == ' ') {
                new_text += String.valueOf(c);
                i++;
                c = Character.toUpperCase(string.toCharArray()[i]);
            }
            new_text += String.valueOf(c);
        }
        return new_text;
    }
    public static String normalizeString(String string){
        string = Normalizer.normalize(string, Normalizer.Form.NFKD);
        string = string.replaceAll("[^a-z,^A-Z,^0-9]", "");
        return string;
    }
    public static boolean isNumber(String text){
        double n;
        if (text == null || text.equals("")) {
            return false;
        } else {
            try {
                n = Double.parseDouble(text);
                return true;
            } catch (NumberFormatException e) {}
            return false;
        }
    }
    public static String textToAscii(String cad) {
        String hiddenWolf = "";
        for (char c : cad.toCharArray()) {
            hiddenWolf += (int)c + "&";
        }
        return hiddenWolf;
    }
    public static String asciiToText(String hiddenWolf) {
        String new_text = "";
        String ascii = "";
        for (char c : hiddenWolf.toCharArray()) {
            if (c != '&') {
                ascii += c;
            }
            if (c == '&') {
                new_text += (char)Integer.parseInt(ascii);
                ascii = "";
            }
        }
        return new_text;
    }
}
