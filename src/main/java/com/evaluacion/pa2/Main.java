package com.evaluacion.pa2;

import com.evaluacion.pa2.model.Nodo;
import com.evaluacion.pa2.model.SolicitudAtencion;
import com.evaluacion.pa2.service.ListaEnlazadaSolicitudes;

import java.util.Scanner;

/**
 * Clase principal adaptada a la BASE COMÚN del equipo.
 * Ejecutable en Apache NetBeans (F6 o Shift+F6).
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ListaEnlazadaSolicitudes lista = new ListaEnlazadaSolicitudes();

    public static void main(String[] args) {
        // Cargar por defecto la lista base común de 5 nodos
        lista.cargarListaBaseComun();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String entrada = scanner.nextLine().trim();

            switch (entrada) {
                case "1":
                    // 1. Recorrido completo
                    lista.recorrerCompleto();
                    pausar();
                    break;
                case "2":
                    // 2. Inserción al inicio
                    ejecutarInsercionInicio();
                    pausar();
                    break;
                case "3":
                    // 3. Inserción al final
                    ejecutarInsercionFinal();
                    pausar();
                    break;
                case "4":
                    // 4. Inserción después de un nodo dado
                    ejecutarInsercionDespuesDe();
                    pausar();
                    break;
                case "5":
                    // Restablecer la lista a los 5 nodos base del equipo
                    lista.cargarListaBaseComun();
                    System.out.println("\n[OK] Lista restablecida a los 5 nodos base del equipo:");
                    lista.mostrarSecuenciaGrafica();
                    pausar();
                    break;
                case "6":
                    // DEMOSTRACIÓN GUIADA EXACTA DE LA ACTIVIDAD 2 CON LOS CASOS DEL EQUIPO (100, 106, 107 tras 102)
                    ejecutarDemostracionActividad2();
                    pausar();
                    break;
                case "7":
                    // DEMOSTRACIÓN DE APOYO ACTIVIDAD 3 (Buscar 103, Modificar 102, Eliminar 105)
                    ejecutarDemostracionActividad3();
                    pausar();
                    break;
                case "8":
                    // Explicación de cómo cambia la estructura
                    ListaEnlazadaSolicitudes.imprimirExplicacionEstructural();
                    pausar();
                    break;
                case "0":
                    salir = true;
                    System.out.println("\n¡Gracias por utilizar el sistema! Programa finalizado.");
                    break;
                default:
                    System.out.println("(!) Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(78));
        System.out.println("       SECUENCIA DINÁMICA DE SOLICITUDES DE ATENCIÓN - PA2 (JAVA)");
        System.out.println("                  BASE COMÚN Y ACTIVIDADES 2 Y 3");
        System.out.println("=".repeat(78));
        System.out.println(" [1] Recorrido completo de la lista");
        System.out.println(" [2] Insertar solicitud al INICIO de la lista");
        System.out.println(" [3] Insertar solicitud al FINAL de la lista");
        System.out.println(" [4] Insertar solicitud DESPUÉS de un nodo dado (por código)");
        System.out.println(" [5] Restablecer los 5 nodos base del equipo (101 al 105)");
        System.out.println(" [6] Ejecutar DEMOSTRACIÓN ACTIVIDAD 2 (Casos 100, 106 y 107 tras 102)");
        System.out.println(" [7] Ejecutar DEMOSTRACIÓN ACTIVIDAD 3 (Buscar 103, Modificar 102, Eliminar 105)");
        System.out.println(" [8] Ver explicación técnica de CÓMO CAMBIA LA ESTRUCTURA");
        System.out.println(" [0] Salir");
        System.out.println("=".repeat(78));
    }

    private static void ejecutarInsercionInicio() {
        System.out.println("\n>>> OPERACIÓN: INSERCIÓN AL INICIO <<<");
        SolicitudAtencion nueva = pedirDatosSolicitud();
        if (nueva != null) {
            System.out.println("\n--- Estado ANTES de la inserción ---");
            lista.mostrarSecuenciaGrafica();

            lista.insertarAlInicio(nueva);

            System.out.println("\n--- Estado DESPUÉS de la inserción al INICIO ---");
            lista.mostrarSecuenciaGrafica();
        }
    }

    private static void ejecutarInsercionFinal() {
        System.out.println("\n>>> OPERACIÓN: INSERCIÓN AL FINAL <<<");
        SolicitudAtencion nueva = pedirDatosSolicitud();
        if (nueva != null) {
            System.out.println("\n--- Estado ANTES de la inserción ---");
            lista.mostrarSecuenciaGrafica();

            lista.insertarAlFinal(nueva);

            System.out.println("\n--- Estado DESPUÉS de la inserción al FINAL ---");
            lista.mostrarSecuenciaGrafica();
        }
    }

    private static void ejecutarInsercionDespuesDe() {
        System.out.println("\n>>> OPERACIÓN: INSERCIÓN DESPUÉS DE UN NODO DADO <<<");
        if (lista.estaVacia()) {
            System.out.println("(!) La lista está vacía.");
            return;
        }

        System.out.println("Nodos disponibles:");
        lista.mostrarSecuenciaGrafica();

        System.out.print("Ingrese el código del nodo de referencia: ");
        int codRef;
        try {
            codRef = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("(!) Código inválido. Debe ser numérico.");
            return;
        }

        Nodo ref = lista.buscarPorCodigo(codRef);
        if (ref == null) {
            System.out.println("(!) No existe ninguna solicitud con el código " + codRef);
            return;
        }

        System.out.println("Nodo de referencia seleccionado: " + ref.getDato());
        SolicitudAtencion nueva = pedirDatosSolicitud();
        if (nueva != null) {
            System.out.println("\n--- Estado ANTES de la inserción ---");
            lista.mostrarSecuenciaGrafica();

            boolean exito = lista.insertarDespuesDe(codRef, nueva);
            if (exito) {
                System.out.println("\n--- Estado DESPUÉS de la inserción ---");
                lista.mostrarSecuenciaGrafica();
            }
        }
    }

    private static SolicitudAtencion pedirDatosSolicitud() {
        try {
            System.out.print("Código único (ej. 100, 106, 107): ");
            int codigo = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Nombre del Solicitante: ");
            String solicitante = scanner.nextLine().trim();
            if (solicitante.isEmpty()) solicitante = "Usuario";

            System.out.println("Estado (1: Pendiente, 2: En atención, 3: Resuelto): ");
            System.out.print("Seleccione [1-3]: ");
            String opc = scanner.nextLine().trim();
            String estado = switch (opc) {
                case "1" -> "Pendiente";
                case "2" -> "En atención";
                case "3" -> "Resuelto";
                default -> "Pendiente";
            };

            return new SolicitudAtencion(codigo, solicitante, estado);
        } catch (Exception e) {
            System.out.println("(!) Datos inválidos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Ejecuta exactamente el guion de prueba definido por el equipo para la ACTIVIDAD 2:
     * - Lista inicial: [101 | Ana] -> [102 | Luis] -> [103 | Rosa] -> [104 | Carlos] -> [105 | Marta] -> null
     * - Insertar al inicio: caso 100
     * - Insertar al final: caso 106
     * - Insertar después de 102: caso 107
     */
    private static void ejecutarDemostracionActividad2() {
        System.out.println("\n" + "=".repeat(85));
        System.out.println("       DEMOSTRACIÓN COMPLETA DE ACTIVIDAD 2 CON LOS CASOS OFICIALES DEL EQUIPO");
        System.out.println("=".repeat(85));

        // Paso 0: Cargar base común
        lista.cargarListaBaseComun();
        System.out.println("\n--- PASO 1: Recorrido completo de la lista de ejemplo base (5 nodos) ---");
        lista.recorrerCompleto();

        // Paso 2: Inserción al inicio con el caso 100
        System.out.println("\n--- PASO 2: Inserción al INICIO de un caso nuevo (código 100) ---");
        System.out.println("Insertando: [100 | Sofía | Pendiente]");
        lista.insertarAlInicio(100, "Sofía", "Pendiente");
        lista.mostrarSecuenciaGrafica();
        System.out.println("Cambio estructural: 'cabeza' apunta ahora a [100]. [100].siguiente apunta a [101]. O(1).");

        // Paso 3: Inserción al final con el caso 106
        System.out.println("\n--- PASO 3: Inserción al FINAL de un caso nuevo (código 106) ---");
        System.out.println("Insertando: [106 | Pedro | Pendiente]");
        lista.insertarAlFinal(106, "Pedro", "Pendiente");
        lista.mostrarSecuenciaGrafica();
        System.out.println("Cambio estructural: Se recorrió hasta [105]. [105].siguiente apunta a [106]. [106].siguiente apunta a null. O(n).");

        // Paso 4: Inserción después de un nodo dado (107 después del 102)
        System.out.println("\n--- PASO 4: Inserción DESPUÉS del nodo 102 (nuevo caso 107) ---");
        System.out.println("Insertando: [107 | Elena | En atención] después de [102 | Luis]");
        lista.insertarDespuesDe(102, 107, "Elena", "En atención");
        lista.mostrarSecuenciaGrafica();
        System.out.println("Cambio estructural: [107].siguiente apunta a [103]; [102].siguiente apunta a [107]. Cadena preservada. O(n).");

        // Paso 5: Recorrido final
        System.out.println("\n--- PASO 5: Recorrido completo final tras todas las inserciones ---");
        lista.recorrerCompleto();
        System.out.println("\n[Demostración de Actividad 2 completada con éxito]");
    }

    /**
     * Demostración complementaria para la ACTIVIDAD 3 requerida por el integrante:
     * - Buscar por código: 103
     * - Modificar estado: 102 a "Resuelto"
     * - Eliminar: 105
     */
    private static void ejecutarDemostracionActividad3() {
        System.out.println("\n" + "=".repeat(85));
        System.out.println("       DEMOSTRACIÓN DE APOYO ACTIVIDAD 3 (BÚSQUEDA, MODIFICACIÓN Y ELIMINACIÓN)");
        System.out.println("=".repeat(85));

        // Partir de la lista base
        lista.cargarListaBaseComun();
        System.out.println("\nEstado inicial de la lista:");
        lista.mostrarSecuenciaGrafica();

        // 1. Búsqueda de 103
        System.out.println("\n--- OPERACIÓN 1: Buscar por código 103 ---");
        Nodo encontrado = lista.buscarPorCodigo(103);
        if (encontrado != null) {
            System.out.println("Nodo encontrado: " + encontrado.getDato());
        }

        // 2. Modificar estado del 102
        System.out.println("\n--- OPERACIÓN 2: Modificar estado del código 102 a 'Resuelto' ---");
        lista.modificarEstado(102, "Resuelto");
        lista.mostrarSecuenciaGrafica();

        // 3. Eliminar el nodo 105
        System.out.println("\n--- OPERACIÓN 3: Eliminar el nodo con código 105 ---");
        lista.eliminarPorCodigo(105);
        lista.mostrarSecuenciaGrafica();

        System.out.println("\nRecorrido tras operaciones de Actividad 3:");
        lista.recorrerCompleto();
    }

    private static void pausar() {
        System.out.print("\nPresione ENTER para continuar...");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
