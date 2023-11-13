public class Main {

    public static void main(String[] args) {
        // Crear instancias de Bar, Almacen y LineaDeTiempo
        Bar bar = new Bar();
        Almacen almacen = new Almacen();
        LineaDeTiempo hora = new LineaDeTiempo();

        // Iniciar la línea de tiempo en un hilo separado
        hora.start();

        // Crear instancias de Proveedores y iniciar sus hilos
        Thread[] Proveedores = new Thread[2];
        for (int i = 0; i < 2; i++) {
            Proveedores proveedor = new Proveedores(almacen, i + 1);
            Proveedores[i] = proveedor;
            proveedor.start();
        }

        // Crear instancias de ThCamareros y iniciar sus hilos
        Thread[] Camarero = new Thread[3];
        for (int i = 0; i < 3; i++) {
            ThCamareros camarero = new ThCamareros(bar, i * 5, hora, almacen);
            Camarero[i] = camarero;
            camarero.start();
        }

        // Crear instancias de Cliente y iniciar sus hilos
        Thread[] Cliente = new Thread[20];
        for (int i = 0; i < 20; i++) {
            Cliente hilo = new Cliente(i, bar, hora);
            Cliente[i] = hilo;
            hilo.start();
        }
    }
}

