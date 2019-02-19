package edu.escuelaing.arem.mediaserver.reflexion;

public class FrameWork {
	
	@Web("/cuadrado")
	public static String cuadrado() {
		//int re=val*val;
		return "4";
	}
	
	@Prueba("/Data")
	public static String pp() {
		return "tt";
	}

}
