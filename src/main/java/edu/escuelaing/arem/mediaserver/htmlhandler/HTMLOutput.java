package edu.escuelaing.arem.mediaserver.htmlhandler;

import java.io.*;
import java.net.*;

/**
 * Declaracion de la clase HTMLOutput,
 * lee archivos .html,.png,.jpg,.js,.css guardados en el proyecto y los interpreta,
 * para despues publicarlos en el servidor por medio de la clase PostPage
 * @author Andres Florez
 */
public class HTMLOutput {
    //declaracion atributos
    private static String html = "";//cadena para concatenar la pagina html
    private static String css = "";//cadena para concatenar la pagina css
    private static String js = "";//cadena para concatenar la pagina javascript

    /**
     * lee una pagina web .html ubicada en una direccion y parsea su contenido a un string
     * @param adress se necesita saber cual es el nombre o direccion del archivo que contiene el codigo html a mostrar
     * @return html string que contiene el codigo html de la pagina guardada
     * @throws MalformedURLException se esta leyendo una direccion
     */
    public static String readHTML(String adress) throws MalformedURLException {        
        try {            
            FileReader file = new FileReader("src/main/java/edu/escuelaing/arem/mediaserver/webpages"+adress);
            BufferedReader reader = new BufferedReader(file);
            String inputLine = "";
            html = "";
            while ((inputLine = reader.readLine()) != null) {
                html += inputLine + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return html;
    }
    
    /**
     * lee una imagen .png ubicada en una direccion y parsea su contenido en Bytes para poder visualizarla luego
     * @param adress se necesita saber cual es el nombre o direccion del archivo que contiene el codigo html a mostrar
     * @return imageBytes imagen parseada a Bytes para poder visualizarla desde el servidor
     * @throws MalformedURLException  excepcion
     */
    public static byte[] readImage(String adress) throws MalformedURLException {        
        byte[] imageBytes = null;
        try {     
            File image = new File("src/main/java/edu/escuelaing/arem/mediaserver/images"+adress);
            FileInputStream inputImage = new FileInputStream(image);
            imageBytes = new byte[(int) image.length()];
            inputImage.read(imageBytes);
            
        } catch (IOException x) {
            System.err.println(x);
        }
        return imageBytes;
    }
    
   
    /**
     * Lee un archivo css ubicado en una direcciony guarda su contenido en un string
     * @param adress path para archivo css
     * @return css string que contiene toda la lectura del archivo css
     * @throws MalformedURLException excepcion
     */
    public static String readCSS(String adress) throws MalformedURLException {        
        try {            
            FileReader file = new FileReader("src/main/java/edu/escuelaing/arem/mediaserver/css"+adress);
            BufferedReader reader = new BufferedReader(file);
            String inputLine = "";
            css = "";
            while ((inputLine = reader.readLine()) != null) {
                css += inputLine + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return css;
    }
    /**
     * Lee un archivo javaScript ubicado en una direcciony guarda su contenido en un string
     * @param adress path para archivo javascript
     * @return js string que contiene toda la lectura del archivo js
     * @throws MalformedURLException excepcion
     */
    public static String readJs(String adress) throws MalformedURLException {        
        try {            
            FileReader file = new FileReader("src/main/java/edu/escuelaing/arem/mediaserver/javascript"+adress);
            BufferedReader reader = new BufferedReader(file);
            String inputLine = "";
            js = "";
            while ((inputLine = reader.readLine()) != null) {
                js += inputLine + "\n";
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return js;
    }
    /**
     * crea un archivo .html a partir de codigo HTML en un string
     * @throws IOException se estan leyendo archivos
     */
    public static void createHTML() throws IOException {
        FileWriter file = new FileWriter("src/main/java/edu/escuelaing/arem/webpages/generatedpage.html");
        file.write(html);
        file.close();
    }
}
