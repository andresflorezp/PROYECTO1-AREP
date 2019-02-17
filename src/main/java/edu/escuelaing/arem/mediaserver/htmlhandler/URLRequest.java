package edu.escuelaing.arem.mediaserver.htmlhandler;

import edu.escuelaing.arem.mediaserver.htmlhandler.HTMLOutput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Declaracion de la clase URLRequest,
 * entrada de peticiones o solicitudes web que son leidas mediante el GET recibido en la direccion URL¨
 * @author Pedro Mayorga
 */
public class URLRequest {
    //declaracion atributos
    public static String adress = "";
    public static BufferedReader in;
    private static HTMLOutput htmlOut = new HTMLOutput();
    
    /**
     * lectura de la entrada del servidor web desde su clientSocket, obtencion de la solicitud GET en variable adress
     * @param clientSocket se debe conocer desde que socket se esta haciendo la solicitud
     * @throws IOException se estan leyendo archivos
     */
    public static void setRequest(Socket clientSocket) throws IOException{
        URLRequest.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;        
        //lea toda la entrada de la solicitud hecha por el servidor
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
            //encuentra la solicitud get pedida por medio de la url y la guarda
            if(inputLine.contains("GET")){
                adress = inputLine.split(" ")[1];
                System.out.println("Adress to show: "+ adress);
            }
        }
    }
    
    /**
     * da la solicitud realizada al servidor
     * @return adress solicitud pedida al servidor, en este caso la direccion a la cual se quiere acceder
     */
    public static String getAdress(){          
        return adress;
    }
    
    /**
     * cierra el archivo que permite la lectura de la solicitud que se esta haciendo al servidor
     * no se puede cerrar en el metodo setHTML porque se cerraria tambien el socket
     * por esto debe cerrarse despus de que entregue y muestre la respectiva pagina
     * @throws IOException se estan leyendo archivos
     */
    public void closeInput() throws IOException {
        in.close();
    }
    
}
