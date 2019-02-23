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
 * @author Andres Florez
 * @version 2.0
 */
public class HttpServer {
    //declaracion atributos privados
    private static ServerSocket serverSocket;//variable asociada al serversocket
    private static Socket receiver;//variable asociada al socket cliente
    private static URLRequest request = new URLRequest();//Obtiene la URL
    private static PostPage post = new PostPage();//Permite colocar los encabezados de las paginas para mostrarlos
    
    /**
     * Creacion del main, que ejecutara todo el proyecto
     * @param args argumentos del main
     * @throws IOException excepcion de entrada
     */
    public static void main(String[] args) throws IOException {
        //recibe solicitudes no concurrentes
        for(;;){
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