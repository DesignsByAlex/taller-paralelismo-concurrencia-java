class Cuenta {

    private int saldo = 1000;

    // método sincronizado para evitar problemas de concurrencia
    public synchronized void retirar(String cliente, int cantidad) {

        if (saldo >= cantidad) {

            System.out.println(cliente + " quiere retirar $" + cantidad);

            saldo -= cantidad;

            System.out.println(cliente + " realizó el retiro.");
            System.out.println("Saldo restante: $" + saldo);
            System.out.println("---------------------------");

        } else {

            System.out.println(cliente + " intentó retirar $" + cantidad);
            System.out.println("Saldo insuficiente. Saldo actual: $" + saldo);
            System.out.println("---------------------------");

        }
    }
}

class Cliente extends Thread {

    private Cuenta cuenta;

    public Cliente(Cuenta cuenta, String nombre) {
        super(nombre);
        this.cuenta = cuenta;
    }

    public void run() {
        cuenta.retirar(getName(), 400);
    }
}

public class CuentaBancaria {

    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta();

        Cliente cliente1 = new Cliente(cuenta, "Cliente 1");
        Cliente cliente2 = new Cliente(cuenta, "Cliente 2");
        Cliente cliente3 = new Cliente(cuenta, "Cliente 3");

        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
}