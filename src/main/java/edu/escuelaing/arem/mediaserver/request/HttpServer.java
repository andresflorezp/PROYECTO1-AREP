package edu.escuelaing.arem.mediaserver.request;

import edu.escuelaing.arem.mediaserver.htmlhandler.PostPage;
import edu.escuelaing.arem.mediaserver.htmlhandler.URLRequest;
import edu.escuelaing.arem.mediaserver.socket.ClientSckt;
import edu.escuelaing.arem.mediaserver.socket.ServerSckt;
import java.io.*;
import java.net.*;

/**
 * Declaracion de la clase HttpServer,
 * main principal del proyecto, desde donde corre el servidor
 * @author Pedro Mayorga
 * @version 2.0
 */
public class HttpServer {
    //declaracion atributos privados
    private static ServerSocket serverSocket;
    private static Socket receiver;
    private static URLRequest request = new URLRequest();
    private static PostPage post = new PostPage();
    
    /**
     * Creacion del main, que ejecutara todo el proyecto
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //recibe solicitudes no concurrentes
        while (true == true){
            //creacion sockets server y client
            serverSocket = ServerSckt.runServer();
            receiver = ClientSckt.receiveRequest(serverSocket);
            //inicializa el servidor para recibir la entrada de la direccion de una pagina
            request.setRequest(receiver);       
            //hace publica la pagina solicitada en el paso anterior
            post.postType(request.getAdress(),receiver);
            request.closeInput();
            //cierrra sockets
            receiver.close();
            serverSocket.close();
        }        
    }      
}