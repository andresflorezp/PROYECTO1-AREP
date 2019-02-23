package edu.escuelaing.arem.mediaserver.reflexion;

/**
 * @author Andres
 * Esta clase es un POJO el cual contiene anotaciones que permiten correr la aplicacion
 * como si fuera un framework
 */
public class FrameWork {

	/**
	 * Este metodo permite elevar al cuadrado un numero
	 * @param num recibe un numero para calcular el cuadrado
	 * @return el resultado de elevar el numero al cuadrado
	 */
	@Web("/cuadrado")
	public static String cuadrado(String num) {
		double re = Double.parseDouble(num) * Double.parseDouble(num);
		return String.valueOf(re);
	}

	/**
	 * Este metodo permite calcular la suma de dos numeros
	 * @param num1 primer numero para la suma
	 * @param num2 segundo numero para la suma
	 * @return la suma de los numero
	 */
	@Web("/suma")
	public static String suma(String num1, String num2) {
		double re = Double.parseDouble(num1) + Double.parseDouble(num2);
		return String.valueOf(re);
	}


	/**
	 * Este metodo permite calcular la resta de dos numeros
	 * @param num1 primer numero para la resta
	 * @param num2 segundo numero para la resta
	 * @return La resta de dos numeros
	 */
	@Web("/resta")
	public static String resta(String num1, String num2) {
		double re = Double.parseDouble(num1) - Double.parseDouble(num2);
		return String.valueOf(re);
	}


	/**
	 * Este metodo permite calcular la multiplicacion de dos numeros
	 * @param num1 primer numero para la multiplicacion
	 * @param num2 segundo numero para la multiplicacion
	 * @return La multiplicacion de dos numeros
	 */
	@Web("/multiplicacion")
	public static String mult(String num1, String num2) {
		double re = Double.parseDouble(num1) * Double.parseDouble(num2);
		return String.valueOf(re);
	}

}
