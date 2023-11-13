import java.util.Random;

public class Proveedores extends Thread {
    private Almacen almacen;  // Referencia al objeto Almacen que el proveedor va a rellenar
    private int id;            // Identificador único del proveedor
    private long tiempoInicio = System.currentTimeMillis();  // Tiempo de inicio del proveedor

    // Constructor de la clase Proveedores
    public Proveedores(Almacen almacen, int id) {
        this.almacen = almacen;
        this.id = id;
    }

    // Método que se ejecuta cuando se inicia el hilo del proveedor
    @Override
    public void run() {
        try {
            // El proveedor espera 60 segundos antes de comenzar a proveer
            sleep(60_000);

            Random rand = new Random();
            int tiempo = rand.nextInt(30);
            // El proveedor espera un tiempo aleatorio antes de entrar al bar
            sleep(tiempo * id * 1000);

            // El proveedor notifica que está entrando al bar
            System.out.println("Soy un proveedor y estoy entrando a las " + (System.currentTimeMillis() - tiempoInicio));

            // El proveedor rellena el almacén
            almacen.rellenar();

            // El proveedor espera 10 segundos antes de salir
            sleep(10_000);

            // El proveedor notifica que está saliendo
            System.out.println("Soy el proveedor y estoy saliendo");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

