package edu.escuelaing.arem.mediaserver.reflexion;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.*;
/**
 * Esta clase permite escanear el .class que contiene las anotaciones @Web y ademas asociar cada path a su metodo
 * @author Andres
 *
 */
public class LeerFicheros {
	public static Class frame;//Esta variable va tener una instancia de la clase , clase para para obtener los metodos
	public static TreeMap<String, Method> UrlMethod;//Esta variable permite almacenar el path con el metodo asignado en el POJO
	/**
	 * Constructor de la clase el cual contiene toda la logica para crear la asociacion entre metodo y anotacion
	 */
	public LeerFicheros() {
		try {
			UrlMethod = new TreeMap();
			frame = Class.forName("edu.escuelaing.arem.mediaserver.reflexion.FrameWork");
			Method[] m = frame.getDeclaredMethods();
			for (Method ml : m) {
				System.out.println(ml.getDeclaredAnnotations()[0]);
				String anotacionM = ml.getDeclaredAnnotations()[0].toString();
				if (anotacionM.contains(".Web")) {
					String data = anotacionM.substring(anotacionM.indexOf('/'), anotacionM.length() - 1);
					System.out.println(data);
					UrlMethod.put(data, ml);

				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
