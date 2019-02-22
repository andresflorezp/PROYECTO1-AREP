package edu.escuelaing.arem.mediaserver.socket;

import java.net.*;
import java.io.*;

/**
 * Declaracion de la clase ServerSckt,
 * crea servidor, espera comunicacion del cliente,
 * recibe peticiones
 * envia respuestas
 * @author Andres Florez
 */
public class ServerSckt {
    
    /**
     * inicia un servidor por medio de un socket,
     * para empezar a recibir solicitudes del cliente
     * @return serverSocket socket servidor desde el cual se van a recibir las solicitudes del cliente
     */
    public static ServerSocket runServer() {
        
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + getPort());
            System.exit(1);
        }
        return serverSocket;
    }    
        
    /**
     * obtiene el numero del puerto a cual se conecta el servidor
     * @return 9999 puerto por defecto donde corre la aplicacion
     */
    public static int getPort() {
    if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}