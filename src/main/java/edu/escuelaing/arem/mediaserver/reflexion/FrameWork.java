package edu.escuelaing.arem.mediaserver.reflexion;

public class FrameWork {

	@Web("/cuadrado")
	public static String cuadrado(String num) {
		double re = Double.parseDouble(num) * Double.parseDouble(num);
		return String.valueOf(re);
	}

	@Web("/suma")
	public static String suma(String a, String b) {
		double re = Double.parseDouble(a) + Double.parseDouble(b);
		return String.valueOf(re);
	}


	@Web("/resta")
	public static String resta(String a, String b) {
		double re = Double.parseDouble(a) - Double.parseDouble(b);
		return String.valueOf(re);
	}

	@Web("/multiplicacion")
	public static String mult(String a, String b) {
		double re = Double.parseDouble(a) * Double.parseDouble(b);
		return String.valueOf(re);
	}

}
