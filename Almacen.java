public class Almacen {
    // Arreglo que representa las existencias de bebidas en el almacén
    private int[] almacen = new int[5];
    
    // Variable que lleva el registro de la contabilidad del almacén
    private int contabilidad = -200;

    // Método para obtener la contabilidad actual del almacén
    public int getContabilidad() {
        return contabilidad;
    }

    // Constructor de la clase Almacen, inicializa el almacén con 5 unidades de bebida
    public Almacen() {
        for (int i = 0; i < 5; i++) {
            almacen[i] = 8;
        }
    }

    // Método para rellenar completamente el almacén con 8 unidades de cada bebida
    public void rellenar() {
        for (int i = 0; i < 5; i++) {
            almacen[i] = 8;
        }
    }

    // Método para verificar la disponibilidad de una unidad de bebida específica
    // Si la bebida está disponible, se decrementa su cantidad en el almacén y se actualiza la contabilidad
    public boolean verificarBebida(int bebida) {
        // Verificar que la bebida solicitada esté en el rango válido
        if (bebida >= 0 && bebida < almacen.length) {
            // Verificar si hay existencias de la bebida en el almacén
            if (almacen[bebida] > 0) {
                almacen[bebida]--;  // Decrementar la cantidad de la bebida en el almacén
                contabilidad++;    // Incrementar la contabilidad
                return true;        // Indicar que la operación fue exitosa
            }
        }
        
        return false;  // Indicar que la bebida no está disponible en el almacén
    }
}

