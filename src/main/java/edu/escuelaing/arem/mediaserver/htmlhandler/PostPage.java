/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.mediaserver.htmlhandler;

import edu.escuelaing.arem.mediaserver.htmlhandler.URLRequest;
import edu.escuelaing.arem.mediaserver.reflexion.LeerFicheros;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * Declaracion de la clase PostPage, genera la salida del request solicitado en
 * la clase URLRequest, despues de haber generado el respectivo html en la clase
 * HTMLOutput muestra la pagina web visualizada en el servidor
 * 
 * @author Andres Florez
 */
public class PostPage {
	// declaracion de atributos privados
	private static Socket clientSocket;
	public static String[] par;

	/**
	 * identifica que tipo de solicitud fue pedida al servidor, si se solicito una
	 * pagina, una imagen o si no se encontro la solicitud
	 * 
	 * @param adress se necesita saber cual es el nombre o direccion del archivo que
	 *               contiene el codigo html a mostrar
	 * @param cliSoc se debe conocer desde que socket se esta haciendo la solicitud
	 * @throws IOException se estan leyendo archivos
	 */
	public static void postType(String adress, Socket cliSoc) throws IOException {
		PostPage.clientSocket = cliSoc;
		LeerFicheros leer = new LeerFicheros();
		TreeMap<String, Method> UrlMethod = leer.UrlMethod;
		System.out.println("ADRESS POSTTYPE: " + adress);
		if (adress.contains(".html")) {
			postPage(adress);
		} else if (adress.contains(".png")) {
			postImage(adress);
		} else if (adress.contains(".jpg")) {
			postImage(adress);

		} else if (adress.contains(".css")) {
			postCss(adress);

		} else if (adress.contains(".js")) {
			postJs(adress);
		
		}else if(adress.contains(":")) { 
			par = adress.split(":");
			
		
			if (UrlMethod.containsKey(par[0])) {
				try {
					appWeb(par[0]);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				notFound();
			}
		}

		else

		{
			notFound();
		}
	}

	/**
	 * publica en el servidor una pagina web html buscando el archivo .html de la
	 * solicitud (adress), leyendolo y posteandolo para hacerlo visible en el
	 * servidor en forma de pagina web
	 * 
	 * @param adress se necesita saber cual es el nombre o direccion del archivo que
	 *               contiene el codigo html a mostrar
	 */
	private static void postPage(String adress) {
		try {
			HTMLOutput htmlOut = new HTMLOutput();
			String outputLine;
			String page = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" +
			// con el nombre del archivo busquelo y añada el codigo html a la variable page
			// parapublicarlo
					"\r\n" + htmlOut.readHTML(adress);
			outputLine = page;
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			// hace visible la pagina en el servidor
			out.println(outputLine);
			out.close();
		} catch (IOException ex) {
			// si no encontro nada mande el error
			notFound();
			System.err.println("Err: Unread File");
		}
	}

	/**
	 * publica en el servidor la imagen buscando el archivo .png de la solicitud
	 * (adress), leyendolo y posteandolo para hacerlo visible en el servidor en
	 * forma de imagen PNG
	 * 
	 * @param adress se necesita saber cual es el nombre o direccion del archivo que
	 *               contiene el codigo html a mostrar
	 */
	private static void postImage(String adress) {
		try {
			HTMLOutput htmlOut = new HTMLOutput();
			byte[] imageBytes;
			// con el nombre de la direccion, busquela y traiga los bytes de la imagen
			imageBytes = htmlOut.readImage(adress);

			DataOutputStream imageCode;
			imageCode = new DataOutputStream(clientSocket.getOutputStream());
			imageCode.writeBytes("HTTP/1.1 200 OK \r\n");
			if (adress.contains(".png"))
				imageCode.writeBytes("Content-Type: image/png\r\n");
			else
				imageCode.writeBytes("Content-Type: image/jpeg\r\n");
			imageCode.writeBytes("Content-Length: " + imageBytes.length);
			imageCode.writeBytes("\r\n\r\n");
			// hace visible la imagen en el servidor
			imageCode.write(imageBytes);
			imageCode.close();
		} catch (IOException ex) {
			// si no encontro nada mande el error
			notFound();
			System.err.println("Err: Unread Image");
		}
	}

	private static void postCss(String adress) {
		try {
			HTMLOutput htmlOut = new HTMLOutput();
			String outputLine;
			String page = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/css\r\n" +
			// con el nombre del archivo busquelo y añada el codigo html a la variable page
			// parapublicarlo
					"\r\n" + htmlOut.readCSS(adress);
			outputLine = page;
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			// hace visible la pagina en el servidor
			out.println(outputLine);
			out.close();
		} catch (IOException ex) {
			// si no encontro nada mande el error
			notFound();
			System.err.println("Err: Unread File");
		}
	}

	private static void postJs(String adress) {
		try {
			HTMLOutput htmlOut = new HTMLOutput();
			String outputLine;
			String page = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/js\r\n" +
			// con el nombre del archivo busquelo y añada el codigo html a la variable page
			// parapublicarlo
					"\r\n" + htmlOut.readJs(adress);
			outputLine = page;
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			// hace visible la pagina en el servidor
			out.println(outputLine);
			out.close();
		} catch (IOException ex) {
			// si no encontro nada mande el error
			notFound();
			System.err.println("Err: Unread File");
		}
	}

	private static void appWeb(String adress) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		LeerFicheros leer = new LeerFicheros();
		TreeMap<String, Method> UrlMethod = leer.UrlMethod;
		Method m = UrlMethod.get(adress);
		try {
			String l = null ;

			if(par.length==1)l = (String) m.invoke(null, null);
			if(par.length==2)l = (String) m.invoke(null, par[1]);
			if(par.length==3)l = (String) m.invoke(null, par[1],par[2]);
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			System.out.println(l);
			PrintWriter response = new PrintWriter(clientSocket.getOutputStream(), true);
			response.println("HTTP/1.1 200 OK");
			response.println("Content-Type: text/html" + "\r\n");
			response.println("<!DOCTYPE html>" + "\r\n");
			response.println("<html>" + "\r\n");
			response.println("<head>" + "\r\n");
			response.println("<meta charset=\"UTF-8\">" + "\r\n");
			response.println("<title>Title of the document</title>" + "\r\n");
			response.println("</head>" + "\r\n");
			response.println("<body>" + "\r\n");
			response.println(" <style>\n" + 
					"        @import url('https://fonts.googleapis.com/css?family=Rubik');\n" + 
					"\n" + 
					"        * {\n" + 
					"            font-family: 'Rubik', sans-serif;\n" + 
					"        }\n" + 
					"\n" + 
					"        h1 {\n" + 
					"            background: #001f3f;\n" + 
					"            padding: 2.5%;\n" + 
					"            color: #fff;\n" + 
					"        }\n" + 
					"\n" + 
					"        #syllabus h3 {\n" + 
					"            background: #85144b;\n" + 
					"            padding: 1.5%;\n" + 
					"            color: #fff;\n" + 
					"            font-size: 0.5cm;\n" + 
					"            display: inline-block;\n" + 
					"            margin: 0;\n" + 
					"\n" + 
					"\n" + 
					"        }\n" + 
					"    </style>");
			response.println("<div id=\"syllabus\">\n" + 
					"        <h3>Pagina para app:</h3>");
			response.println("</div>");
			response.println("<h3>El resultado es:"+l+"</h3>"+ "\r\n");
			response.println("</body>" + "\r\n");
			response.println("</html>" + "\r\n");
			response.flush();
			response.close();

		
		} catch (IOException ex) {
			// si no encontro nada mande el error
			notFound();
			System.err.println("Err: Unread File");
		}

	}

	/**
	 * publica en el servidor una pagina con un mensaje de error en caso de no
	 * encontrar el archivo solicitado,
	 */
	private static void notFound() {
		try {
			HTMLOutput htmlOut = new HTMLOutput();
			String outputLine;
			String page = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" +
			// busque y publique la pagina "notfound.html"
					"\r\n" + htmlOut.readHTML("/notfound.html");

			outputLine = page;
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(outputLine);
			out.close();
		} catch (IOException ex) {
			System.err.println("Err:");
		}
	}

}
