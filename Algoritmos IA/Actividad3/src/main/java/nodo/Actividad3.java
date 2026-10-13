package nodo;

public class Actividad3 {

    static class Nodo{
        int codigo;
        String solicitante;
        String estado;
        Nodo siguiente;

        Nodo( int codigo, String solicitante, String estado){

            this.codigo = codigo;
            this.solicitante = solicitante;
            this.estado = estado;
            this.siguiente = null;
        }

    }

    static class ListaEnlazada {
        Nodo cabeza;

        void insertarFinal(int codigo, String solicitante, String estado) {
            Nodo nuevo = new Nodo(codigo, solicitante, estado);
            if (cabeza == null) {
                cabeza = nuevo;
                return;
            }
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        void mostrar() {
            Nodo actual = cabeza;
            while (actual != null) {
                System.out.print("[" + actual.codigo + " | " + actual.solicitante
                        + " | " + actual.estado + "] -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        // Búsqueda por código
        Nodo buscar(int codigo) {
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.codigo == codigo) {
                    return actual;
                }
                actual = actual.siguiente;
            }
            return null;
        }

        // Modificación del estado de un caso
        boolean modificarEstado(int codigo, String nuevoEstado) {
            Nodo encontrado = buscar(codigo);
            if (encontrado == null) {
                return false;
            }
            encontrado.estado = nuevoEstado;
            return true;
        }

        // Eliminación por código
        boolean eliminar(int codigo) {
            if (cabeza == null) return false;

            if (cabeza.codigo == codigo) {
                cabeza = cabeza.siguiente;
                return true;
            }

            Nodo anterior = cabeza;
            Nodo actual = cabeza.siguiente;
            while (actual != null) {
                if (actual.codigo == codigo) {
                    anterior.siguiente = actual.siguiente;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarFinal(101, "Ana", "Pendiente");
        lista.insertarFinal(102, "Luis", "En atencion");
        lista.insertarFinal(103, "Rosa", "Pendiente");
        lista.insertarFinal(104, "Carlos", "Pendiente");
        lista.insertarFinal(105, "Marta", "Resuelto");

        System.out.println("Lista inicial:");
        lista.mostrar();

        Nodo n = lista.buscar(103);
        System.out.println(n != null ? "Encontrado: " + n.solicitante : "No encontrado");

        lista.modificarEstado(102, "Resuelto");
        System.out.println("Tras modificar el 102:");
        lista.mostrar();

        lista.eliminar(103);
        System.out.println("Tras eliminar el 103:");
        lista.mostrar();

        lista.eliminar(105);
        System.out.println("Tras eliminar el 105:");
        lista.mostrar();
    }
}
