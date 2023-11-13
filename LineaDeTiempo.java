class LineaDeTiempo extends Thread {
    // Variable que almacena el tiempo de inicio del hilo en milisegundos desde el epoch
    private long tiempoInicio = System.currentTimeMillis();

    // Método para obtener el tiempo transcurrido desde el inicio del hilo
    public long getTiempoInicio() {
        return System.currentTimeMillis() - tiempoInicio;
    }

    // Método que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        try {
            // Simula una línea de tiempo de 300 segundos (5 minutos)
            for (int i = 0; i < 300; i++) {
                Thread.sleep(1000); // Espera 1 segundo entre iteraciones para simular el paso del tiempo
            }
            // Termina el horario laboral
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

