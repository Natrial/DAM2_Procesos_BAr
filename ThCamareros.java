public class ThCamareros extends Thread {
    private Boolean oOcupado = false;   // Variable que indica si el camarero está ocupado
    private Bar bar;                    // Referencia al objeto Bar al que el camarero atiende
    private Almacen almacen;            // Referencia al objeto Almacen del que se obtienen las bebidas
    private int priority;               // Prioridad del camarero (posición en la lista de orden)
    private LineaDeTiempo horario;      // Referencia a la línea de tiempo que controla el horario

    // Constructor de la clase ThCamareros
    public ThCamareros(Bar bar, int priority, LineaDeTiempo horario, Almacen almacen) {
        this.bar = bar;
        this.priority = priority;
        this.horario = horario;
        this.almacen = almacen;
    }

    // Método que se ejecuta cuando se inicia el hilo del camarero
    public void run() {
        while (horario.getTiempoInicio() < 300_000) {
            try {
                descanso();
                if (horario.getTiempoInicio() >= 300_000) {
                    System.out.println(this.almacen.getContabilidad());
                    break;
                }

                setoOcupado(true);

                int clienteIndex = bar.getOrden().get(priority);
                System.out.println("Atendiendo cliente: " + clienteIndex);

                int tipoBebida = bar.getClientesEsperando().get(clienteIndex);
                int resultado = DarBebida(tipoBebida);

                Thread.sleep(2000);

                // Actualiza la lista de resultados
                bar.addResultado(resultado);

                setoOcupado(false);
                descanso();

            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    // Método para simular el descanso del camarero cuando no hay pedidos
    public void descanso() {
        System.out.println("No hay pedidos. Descansando... \n\t-atte, Camarero " + getName().charAt(7));

        synchronized (bar) {
            while ((bar.getOrden().size() == 0 || bar.getOrden().size() <= priority) && horario.getTiempoInicio() < 300_000) {
                try {
                    // Se espera a que haya pedidos o hasta que termine la jornada
                    bar.wait(1000);
                } catch (InterruptedException e) {
                    // Handle the exception if needed
                    //e.printStackTrace();
                }
            }
        }
    }

    // Getter para obtener el estado de ocupación del camarero
    public Boolean getoOcupado() {
        return oOcupado;
    }

    // Setter para establecer el estado de ocupación del camarero
    public void setoOcupado(Boolean oOcupado) {
        this.oOcupado = oOcupado;
    }

    // Método para simular la entrega de bebidas por parte del camarero
    public int DarBebida(int bebida) {
        if (almacen.verificarBebida(bebida)) {
            return 1;  // Indica que la bebida fue entregada con éxito
        }
        return 0;      // Indica que la bebida no estaba disponible en el almacén
    }
}



