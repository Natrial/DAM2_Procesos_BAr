import java.util.Random;

class Cliente extends Thread {
    private int nombre;             // Identificador único del cliente
    private Bar bar;                // Referencia al objeto Bar al que el cliente realizará pedidos
    private LineaDeTiempo horario;  // Referencia a una línea de tiempo que controla el horario de funcionamiento

    // Constructor de la clase Cliente
    public Cliente(int nombre, Bar bar, LineaDeTiempo horario) {
        this.nombre = nombre;
        this.bar = bar;
        this.horario = horario;
    }

    // Método que se ejecuta cuando se inicia el hilo del cliente
    @Override
    public void run() {
        // Bucle principal que simula la actividad del cliente mientras el bar esté abierto
        while (horario.getTiempoInicio() < 300_000) {
            Random random = new Random();
            int tiempoEntrePedidos;
            int probabilidad;
            int tipoBebida;
            int choice;

            // Determina los parámetros de los pedidos según el horario
            if (horario.getTiempoInicio() < 120_000) {
                tiempoEntrePedidos = 10000 + random.nextInt(5000);
                probabilidad = 70;
                tipoBebida = random.nextInt(100) % 100;
                int[] bebidaProbabilities = {40, 20, 20, 10, 10};
                choice = getBebidaChoice(tipoBebida, bebidaProbabilities);
            } else {
                tiempoEntrePedidos = 5000 + random.nextInt(5000);
                probabilidad = 80;
                tipoBebida = random.nextInt(100) % 100;
                int[] bebidaProbabilities = {20, 20, 20, 20, 20};
                choice = getBebidaChoice(tipoBebida, bebidaProbabilities);
            }

            // Simula el tiempo entre pedidos
            try {
                Thread.sleep(tiempoEntrePedidos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Realiza un pedido al bar si se cumplen ciertas condiciones
            if (horario.getTiempoInicio() < 300_000) {
                if (random.nextInt(100) < probabilidad) {
                    System.out.println("Me estoy inscribiendo. Soy: " + nombre + " y quiero " + choice);
                    bar.setClientesEsperando(nombre, choice);

                    synchronized (bar) {
                        try {
                            // Espera hasta que se haya procesado el pedido
                            while (bar.getResultados(nombre) == 2) {
                                if (horario.getTiempoInicio() > 300_000) {
                                    break;
                                }
                                bar.wait();
                            }

                            // Muestra el resultado del pedido y actualiza el estado
                            int control = bar.getResultados(nombre);
                            System.out.println("Soy " + nombre + ", me han atendido y hay " + control);
                            bar.setResultados(nombre);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                break;
            }
        }
    }

    // Método auxiliar para determinar el tipo de bebida elegida según las probabilidades
    private int getBebidaChoice(int tipoBebida, int[] bebidaProbabilities) {
        int cumulativeProbability = 0;
        int choice = 0;
        for (int i = 0; i < 5; i++) {
            cumulativeProbability += bebidaProbabilities[i];
            if (tipoBebida < cumulativeProbability) {
                choice = i;
                break;
            }
        }
        return choice;
    }
}



