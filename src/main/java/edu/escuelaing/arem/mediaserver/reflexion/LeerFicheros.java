package edu.escuelaing.arem.mediaserver.reflexion;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.Arrays;
import java.util.TreeMap;

public class LeerFicheros {
	public static Class c;
	public static TreeMap<String, Method> UrlMethod;

	public LeerFicheros() {
		try {
			UrlMethod = new TreeMap();
			c = Class.forName("edu.escuelaing.arem.mediaserver.reflexion.FrameWork");
			Method[] m = c.getDeclaredMethods();
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
