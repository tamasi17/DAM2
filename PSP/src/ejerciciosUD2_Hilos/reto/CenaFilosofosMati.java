package ejerciciosUD2_Hilos.reto;

/*  @author:   */

public class CenaFilosofosMati {

	public static void main(String[] args) throws Exception {

		FilosofoMati[] filosofoMatis = new FilosofoMati[5];
		PalilloMati[] palillos = new PalilloMati[filosofoMatis.length];

		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = new PalilloMati(i+1);
		}

		for (int i = 0; i < filosofoMatis.length; i++) {

			var palilloDerecho = palillos[i];
			var palilloIzquierdo = palillos[(i + 1) % palillos.length];

			filosofoMatis[i] = new FilosofoMati(palilloIzquierdo, palilloDerecho, i);

			Thread thread = new Thread(filosofoMatis[i], "Filosofo " + (i + 1));
			thread.start();
		}
	
		
	}
}
