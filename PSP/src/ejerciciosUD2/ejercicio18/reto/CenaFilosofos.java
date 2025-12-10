package ejerciciosUD2.ejercicio18.reto;



public class CenaFilosofos {

	public static void main(String[] args) throws Exception {

		Filosofo[] filosofos = new Filosofo[5];
		Palillo[] palillos = new Palillo[filosofos.length];

		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = new Palillo(i+1);
		}

		for (int i = 0; i < filosofos.length; i++) {

			var palilloDerecho = palillos[i];
			var palilloIzquierdo = palillos[(i + 1) % palillos.length];

			filosofos[i] = new Filosofo(palilloIzquierdo, palilloDerecho);

			Thread thread = new Thread(filosofos[i], "Filosofo " + (i + 1));
			thread.start();
		}
	
		
	}
}
