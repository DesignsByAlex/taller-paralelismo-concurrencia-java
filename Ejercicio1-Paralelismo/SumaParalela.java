class SumaHilo extends Thread {

    private int inicio;
    private int fin;
    private long suma = 0;

    public SumaHilo(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        for (int i = inicio; i <= fin; i++) {
            suma += i;
        }
    }

    public long getSuma() {
        return suma;
    }
}

public class SumaParalela {

    public static void main(String[] args) {

        SumaHilo h1 = new SumaHilo(1, 250000);
        SumaHilo h2 = new SumaHilo(250001, 500000);
        SumaHilo h3 = new SumaHilo(500001, 750000);
        SumaHilo h4 = new SumaHilo(750001, 1000000);

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long total = h1.getSuma() + h2.getSuma() + h3.getSuma() + h4.getSuma();

        System.out.println("La suma total es: " + total);
    }
}