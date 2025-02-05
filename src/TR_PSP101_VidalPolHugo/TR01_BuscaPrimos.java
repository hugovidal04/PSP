package TR_PSP101_VidalPolHugo;

public class TR01_BuscaPrimos implements Runnable {
    private final int inicio, fin;
    private int totalPrimos;

    public TR01_BuscaPrimos(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getTotalPrimos() {
        return totalPrimos;
    }

    private boolean esPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @Override
    public void run() {
        totalPrimos = 0;
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) totalPrimos++;
        }
    }
}