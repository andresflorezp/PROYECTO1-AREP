package edu.escuelaing.arem.mediaserver.socket;

import java.io.*;
import java.net.*;

/**
 * Declaracion de la clase ClientSckt,
 * socket, se comunica con el servidor,
 * le envia peticiones
 * recibe respuestas
 * @author Pedro Mayorga
 */
public class ClientSckt {

    /**
     * inicia la sesion del cliente por medio de un socket,
     * para comenzar a enviar solicitudes
     * @param serverSocket se debe conocer desde que servidor se esta haciendo la solicitud
     * @return clienSocket socket cliente desde el cual se van a enviar solicitudes al servidor
     */
    public static Socket receiveRequest(ServerSocket serverSocket) {

        Socket request = null;        
        try {
            System.out.println("Ready to receive...");
            request = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        return request;
    }    
}