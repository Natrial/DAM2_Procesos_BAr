import java.util.ArrayList;

public class Bar {
    // Lista que almacena los clientes que están esperando realizar un pedido
    private ArrayList<Integer> clientesEsperando = new ArrayList<>();
    
    // Lista que almacena los pedidos realizados por los clientes
    private ArrayList<Integer> orden = new ArrayList<>();
    
    // Lista que almacena los resultados de los pedidos (0 = falso, 1 = verdadero, 2 = nulo)
    private ArrayList<Integer> resultados = new ArrayList<>();

    // Método para obtener la lista de clientes que están esperando
    public ArrayList<Integer> getClientesEsperando() {
        return clientesEsperando;
    }

    // Método para obtener la lista de pedidos realizados
    public ArrayList<Integer> getOrden() {
        return orden;
    }

    // Método para establecer el resultado de un pedido en una posición específica
    public void setResultados(int index) {
        this.resultados.set(index, 2); // 2 indica que el resultado es nulo
    }

    // Constructor de la clase Bar, inicializa las listas de clientes esperando y resultados
    public Bar() {
        for (int i = 0; i < 20; i++) {
            clientesEsperando.add(null); // Inicializa la lista de clientes esperando con valores nulos
            resultados.add(2); // Inicializa la lista de resultados con valores nulos
        }
    }

    // Método sincronizado para que los clientes puedan realizar pedidos de manera segura
    public synchronized void setClientesEsperando(int nombreCliente, int tipoBebida) {
        orden.add(nombreCliente); // Agrega el cliente a la lista de pedidos
        clientesEsperando.set(nombreCliente, tipoBebida); // Establece el tipo de bebida que el cliente desea
        notifyAll(); // Notifica a todos los hilos que están esperando
    }

    // Método para agregar el resultado de un pedido a la lista de resultados
    public void addResultado(int resultado) {
        synchronized (this) {
            resultados.set(orden.get(0), resultado); // Establece el resultado en la posición correspondiente
            orden.remove(0); // Elimina el primer elemento de la lista de pedidos
            notifyAll(); // Notifica a todos los hilos que están esperando
        }
    }

    // Método para obtener el resultado de un pedido en una posición específica
    public int getResultados(int index) {
        return resultados.get(index);
    }
}

