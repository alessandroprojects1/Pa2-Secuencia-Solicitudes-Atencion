package com.evaluacion.pa2.service;

import com.evaluacion.pa2.model.Nodo;
import com.evaluacion.pa2.model.SolicitudAtencion;

/**
 * Lista Enlazada Simple para la gestión de solicitudes de atención.
 * Adaptada a la base común del equipo (código, solicitante, estado).
 * 
 * Implementa:
 * - ACTIVIDAD 2: Recorrido completo, Inserción al inicio, Inserción al final,
 *                Inserción después de un nodo dado y análisis estructural.
 * - Soporte para ACTIVIDAD 3: Búsqueda, modificación de estado y eliminación por código.
 */
public class ListaEnlazadaSolicitudes {

    private Nodo cabeza;
    private int tamaño;

    public ListaEnlazadaSolicitudes() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    // =========================================================================
    // ACTIVIDAD 2 - OPERACIÓN 1: RECORRIDO COMPLETO
    // =========================================================================
    /**
     * Recorre la lista secuencialmente desde 'cabeza' hasta 'null'.
     * Muestra la tabla detallada y la secuencia visual encadenada.
     * Complejidad: O(n)
     */
    public void recorrerCompleto() {
        if (estaVacia()) {
            System.out.println("(!) La lista está VACÍA. cabeza -> null");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("       RECORRIDO COMPLETO DE SOLICITUDES DE ATENCIÓN");
        System.out.println("=".repeat(60));
        System.out.println(String.format("| %-8s | %-20s | %-16s |", "CÓDIGO", "SOLICITANTE", "ESTADO"));
        System.out.println("-".repeat(60));

        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato().toFilaTabla());
            actual = actual.getSiguiente();
        }

        System.out.println("=".repeat(60));
        System.out.println("Total de nodos: " + tamaño);
        mostrarSecuenciaGrafica();
    }

    /**
     * Imprime la representación gráfica exacta acordada:
     * cabeza → [101 | Ana | Pendiente] → [102 | Luis | En atención] → ... → null
     */
    public void mostrarSecuenciaGrafica() {
        if (estaVacia()) {
            System.out.println("Estructura visual: cabeza → null\n");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("cabeza → ");
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(String.format("[%d | %s | %s]",
                    actual.getCodigo(),
                    actual.getSolicitante(),
                    actual.getEstado()));
            sb.append(" → ");
            actual = actual.getSiguiente();
        }
        sb.append("null");
        System.out.println("\nEstructura visual:");
        System.out.println(sb.toString() + "\n");
    }

    // =========================================================================
    // ACTIVIDAD 2 - OPERACIÓN 2: INSERCIÓN AL INICIO
    // =========================================================================
    /**
     * Inserta una nueva solicitud al inicio de la lista (nueva cabeza).
     * 1. nuevoNodo = new Nodo(solicitud)
     * 2. nuevoNodo.siguiente = cabeza
     * 3. cabeza = nuevoNodo
     * Complejidad: O(1)
     */
    public void insertarAlInicio(SolicitudAtencion solicitud) {
        Nodo nuevo = new Nodo(solicitud);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamaño++;

        System.out.println("[OK] Inserción al INICIO realizada exitosamente:");
        System.out.println("     Nuevo caso: " + solicitud);
    }

    public void insertarAlInicio(int codigo, String solicitante, String estado) {
        insertarAlInicio(new SolicitudAtencion(codigo, solicitante, estado));
    }

    // =========================================================================
    // ACTIVIDAD 2 - OPERACIÓN 3: INSERCIÓN AL FINAL
    // =========================================================================
    /**
     * Inserta una nueva solicitud al final de la lista.
     * 1. nuevoNodo = new Nodo(solicitud)
     * 2. Si cabeza es null -> cabeza = nuevoNodo
     * 3. Si no -> recorrer hasta que actual.siguiente == null y hacer actual.siguiente = nuevoNodo
     * Complejidad: O(n)
     */
    public void insertarAlFinal(SolicitudAtencion solicitud) {
        Nodo nuevo = new Nodo(solicitud);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;

        System.out.println("[OK] Inserción al FINAL realizada exitosamente:");
        System.out.println("     Nuevo caso: " + solicitud);
    }

    public void insertarAlFinal(int codigo, String solicitante, String estado) {
        insertarAlFinal(new SolicitudAtencion(codigo, solicitante, estado));
    }

    // =========================================================================
    // ACTIVIDAD 2 - OPERACIÓN 4: INSERCIÓN DESPUÉS DE UN NODO DADO
    // =========================================================================
    /**
     * Inserta una nueva solicitud inmediatamente después del nodo con el código indicado.
     * 1. Buscar el nodo con codigoReferencia.
     * 2. Si existe:
     *    nuevoNodo.siguiente = actual.siguiente
     *    actual.siguiente = nuevoNodo
     * Complejidad: O(n)
     */
    public boolean insertarDespuesDe(int codigoReferencia, SolicitudAtencion solicitud) {
        if (estaVacia()) {
            System.out.println("(!) Error: La lista está vacía. No existe el nodo " + codigoReferencia);
            return false;
        }

        Nodo actual = cabeza;
        while (actual != null && actual.getCodigo() != codigoReferencia) {
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            System.out.println("(!) Error: No se encontró ningún nodo con el código: " + codigoReferencia);
            return false;
        }

        // Reajuste de enlaces
        Nodo nuevo = new Nodo(solicitud);
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamaño++;

        System.out.println("[OK] Inserción DESPUÉS del código " + codigoReferencia + " exitosa:");
        System.out.println("     Nuevo caso: " + solicitud);
        return true;
    }

    public boolean insertarDespuesDe(int codigoReferencia, int codigo, String solicitante, String estado) {
        return insertarDespuesDe(codigoReferencia, new SolicitudAtencion(codigo, solicitante, estado));
    }

    // =========================================================================
    // MÉTODOS DE APOYO PARA ACTIVIDAD 3 (Búsqueda, Modificación y Eliminación)
    // =========================================================================
    /**
     * Busca un nodo por su código único.
     * Complejidad: O(n)
     */
    public Nodo buscarPorCodigo(int codigo) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getCodigo() == codigo) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * Modifica el estado de una solicitud existente (ej. código 102 a "Resuelto").
     */
    public boolean modificarEstado(int codigo, String nuevoEstado) {
        Nodo encontrado = buscarPorCodigo(codigo);
        if (encontrado != null) {
            String anterior = encontrado.getEstado();
            encontrado.getDato().setEstado(nuevoEstado);
            System.out.println("[OK] Estado del caso " + codigo + " modificado de '" + anterior + "' a '" + nuevoEstado + "'.");
            return true;
        }
        System.out.println("(!) No se encontró el caso con código " + codigo + " para modificar.");
        return false;
    }

    /**
     * Elimina el nodo con el código especificado reajustando los enlaces.
     * Complejidad: O(n)
     */
    public boolean eliminarPorCodigo(int codigo) {
        if (estaVacia()) {
            System.out.println("(!) La lista está vacía. No se puede eliminar.");
            return false;
        }

        // Si es el primer nodo
        if (cabeza.getCodigo() == codigo) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            System.out.println("[OK] Nodo con código " + codigo + " eliminado de la cabeza.");
            return true;
        }

        // Buscar nodo anterior al que se eliminará
        Nodo anterior = cabeza;
        while (anterior.getSiguiente() != null && anterior.getSiguiente().getCodigo() != codigo) {
            anterior = anterior.getSiguiente();
        }

        if (anterior.getSiguiente() == null) {
            System.out.println("(!) Nodo con código " + codigo + " no encontrado para eliminar.");
            return false;
        }

        // Reajuste de punteros: el anterior salta al sucesor del nodo a eliminar
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        tamaño--;
        System.out.println("[OK] Nodo con código " + codigo + " eliminado exitosamente.");
        return true;
    }

    /**
     * Carga exactamente los 5 nodos de ejemplo definidos como base común del equipo:
     * cabeza → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → null
     */
    public void cargarListaBaseComun() {
        vaciar();
        insertarAlFinal(new SolicitudAtencion(101, "Ana", "Pendiente"));
        insertarAlFinal(new SolicitudAtencion(102, "Luis", "En atención"));
        insertarAlFinal(new SolicitudAtencion(103, "Rosa", "Pendiente"));
        insertarAlFinal(new SolicitudAtencion(104, "Carlos", "Pendiente"));
        insertarAlFinal(new SolicitudAtencion(105, "Marta", "Resuelto"));
    }

    public void vaciar() {
        cabeza = null;
        tamaño = 0;
    }

    // =========================================================================
    // EXPLICACIÓN DEL CAMBIO ESTRUCTURAL
    // =========================================================================
    public static void imprimirExplicacionEstructural() {
        System.out.println("\n" + "=".repeat(85));
        System.out.println("   ACTIVIDAD 2: EXPLICACIÓN DE CÓMO CAMBIA LA ESTRUCTURA TRAS CADA OPERACIÓN");
        System.out.println("=".repeat(85));
        System.out.println("""
1. RECORRIDO COMPLETO:
   - No altera los enlaces en memoria.
   - Un puntero auxiliar 'actual' avanza de forma secuencial: actual = actual.siguiente.
   - Termina cuando actual == null. Complejidad: O(n).

2. INSERCIÓN AL INICIO (ej. caso 100):
   - El nuevo nodo [100] apunta a la actual cabeza [101].
   - La referencia 'cabeza' pasa a apuntar a [100].
   - Diagrama:
     ANTES:   cabeza → [101] → [102] → [103] → [104] → [105] → null
     DESPUÉS: cabeza → [100] → [101] → [102] → [103] → [104] → [105] → null
   - Complejidad: O(1) tiempo constante.

3. INSERCIÓN AL FINAL (ej. caso 106):
   - Se recorre hasta el último nodo ([105]).
   - El puntero 'siguiente' de [105] se reasigna para apuntar al nuevo nodo [106].
   - [106].siguiente queda apuntando a null.
   - Diagrama:
     ANTES:   ... → [105] → null
     DESPUÉS: ... → [105] → [106] → null
   - Complejidad: O(n) tiempo lineal.

4. INSERCIÓN DESPUÉS DE UN NODO DADO (ej. caso 107 después del 102):
   - Se localiza el nodo de referencia [102].
   - Paso crítico 1: nuevoNodo[107].siguiente = nodo[102].siguiente (apunta a [103]).
   - Paso crítico 2: nodo[102].siguiente = nuevoNodo[107].
   - Diagrama:
     ANTES:   ... → [102] ------------------------> [103] → ...
     DESPUÉS: ... → [102] → [NUEVO: 107] ---------> [103] → ...
   - Complejidad: O(n) en el peor caso.
""");
        System.out.println("=".repeat(85) + "\n");
    }
}
