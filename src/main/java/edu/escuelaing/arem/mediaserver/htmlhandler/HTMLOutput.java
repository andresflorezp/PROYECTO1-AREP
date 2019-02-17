package edu.escuelaing.arem.mediaserver.htmlhandler;

import java.io.*;
import java.net.*;

/**
 * Declaracion de la clase HTMLOutput,
 * lee archivos .html y .png guardados en el proyecto y los interpreta,
 * para despues publicarlos en el servidor por medio de la clase PostPage
 * @author Pedro Mayorga
 */
public class HTMLOutput {
    //declaracion atributos
    private static String html = "";

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
     * @throws MalformedURLException 
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
     * crea un archivo .html a partir de codigo HTML en un string
     * @throws IOException se estan leyendo archivos
     */
    public static void createHTML() throws IOException {
        FileWriter file = new FileWriter("src/main/java/edu/escuelaing/arem/webpages/generatedpage.html");
        file.write(html);
        file.close();
    }
}
