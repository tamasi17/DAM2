package ejerciciosUD2.ejercicio20;


class HilosIncDec {

  private static final int NUM_HILOS_INC = 10;
  private static final int NUM_HILOS_DEC = 10;

  public static void main(String[] args) {
    Contador c = new Contador(0);
    Thread[] hilosInc = new Thread[NUM_HILOS_INC];
    for (int i = 0; i < NUM_HILOS_INC; i++) {
      Thread th = new Thread(new HiloIncr("INC"+i, c));
      hilosInc[i] = th;
    }
    for (int i = 0; i < NUM_HILOS_INC; i++) {
      hilosInc[i].start();
    }
    Thread[] hilosDec = new Thread[NUM_HILOS_DEC];
    for (int i = 0; i < NUM_HILOS_DEC; i++) {
      Thread th = new Thread(new HiloDecr("DEC"+i, c));
      hilosDec[i] = th;
    }
    for (int i = 0; i < NUM_HILOS_DEC; i++) {
      hilosDec[i].start();
    }
  }

}
