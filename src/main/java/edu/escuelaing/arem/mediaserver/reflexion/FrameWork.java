package edu.escuelaing.arem.mediaserver.reflexion;

public class FrameWork {
	
	@Web("/cuadrado")
	public static String cuadrado(int val) {
		int re=val*val;
		return String.valueOf(re);
	}
	
	@Prueba("/Data")
	public static String pp() {
		return "tt";
	}

}
