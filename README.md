# PA2 - Evaluación aplicada sobre listas enlazadas 

> **Curso:** Algoritmo y Estructura de Datos Basados en Inteligencia Artificial
> **Código:** 30710 
> **Evaluación:** PA2

## Integrantes: 

| Integrante | Rol | Aporte principal |
|---|---|---|
| José Fernando Frias Díaz | [Desarrollador de Módulos Matriciales] | [Desarrollo e implementación algorítmica de la Actividad 3 (Matrices y recorrido completo de datos).] |
| Gianfranco Arturo Bernal Saravia | [Analista de Estructuras Especiales] | [Investigación y estructuración técnica de la Actividad 4 (Matrices especiales y decisión técnica).] |
| Martha Sofia Lozano Paredes | [Gestor de Repositorio y Documentación] | [Configuración del repositorio en GitHub, diseño de la presentación ejecutiva (PPT) y redacción de conclusiones.] |
| Yanhira Lizeth Gutiérrez Pérez | [Analista de Requisitos y Fundamentación] | [Análisis conceptual y desarrollo teórico de la Actividad 1 (Análisis del problema y selección de estructura).] |
| Alessandro Ismael Gallo Quiroz | [Desarrollador Backend Principal] | [Modelado, desarrollo de algoritmos vectoriales y lógica de ordenamiento de la Actividad 2] |

## BASE COMÚN DEL PROYECTO (Alineación Grupal)

Para mantener la coherencia transversal en todas las actividades (1, 2, 3 y 4), se definió la siguiente estructura común:

### Estructura del NODO (`SolicitudAtencion`)
- **`codigo`**: Entero, único (identificador del caso).
- **`solicitante`**: Texto (nombre de la persona atendida).
- **`estado`**: Cadena con valores estandarizados (`Pendiente`, `En atención`, `Resuelto`).
- **`siguiente`**: Enlace de referencia al nodo sucesor.

### Lista de Ejemplo Base (5 nodos oficiales)
```text
cabeza → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → null
```

------------------------------------------------------------------------------------------

## ACTIVIDAD 1 - Fundamentos y representación de listas enlazadas 



## ACTIVIDAD 2 - Recorrido e inserción 

### 1. Contextualización y Caso de Trabajo
Una organización requiere gestionar una secuencia dinámica de solicitudes de atención ciudadana o corporativa. Los registros cambian continuamente: ingresan casos prioritarios al inicio, nuevas solicitudes ordinarias al final de la cola o inserciones preferenciales después de un caso en proceso.

Para dar soporte dinámico a este flujo sin el costo de reubicación de memoria de arreglos contiguos de tamaño fijo, se implementa una **Lista Enlazada Simple (Singly Linked List)** donde cada nodo almacena la solicitud (`codigo`, `solicitante`, `estado`) y el enlace `siguiente`.

---

### 2. Estructura del Proyecto en Java (Apache NetBeans)

El proyecto está estructurado con el estándar **Maven**, lo que garantiza apertura inmediata y nativa en **Apache NetBeans (versiones 12 a 25+)**:

```text
Pa2-Secuencia-Solicitudes-Atencion/
├── pom.xml                                      # Configuración Maven y compatibilidad NetBeans
├── README.md                                    # Documentación técnica del proyecto
├── .gitignore                                   # Filtro de artefactos temporales
└── src/
    └── main/
        └── java/
            └── com/
                └── evaluacion/
                    └── pa2/
                        ├── Main.java            # Menú interactivo en consola y demostraciones guiadas
                        ├── model/
                        │   ├── SolicitudAtencion.java # Modelo común (codigo, solicitante, estado)
                        │   └── Nodo.java              # Nodo enlazado simple
                        └── service/
                            └── ListaEnlazadaSolicitudes.java # Algoritmos de Actividad 2 y métodos de apoyo para Actividad 3
```

---

### 3. Guía Paso a Paso para Abrir y Ejecutar en Apache NetBeans

1. **Abrir Apache NetBeans**: Inicie el IDE en su equipo.
2. **Abrir el Proyecto**:
   - En la barra superior, haga clic en `File` -> `Open Project...` (o `Ctrl + Shift + O`).
   - Seleccione la carpeta del repositorio (`Pa2-Secuencia-Solicitudes-Atencion`).
   - NetBeans reconocerá el proyecto Maven de inmediato (icono con la taza de café y letra `m`).
   - Haga clic en **Open Project**.
3. **Compilar y Ejecutar**:
   - Presione la tecla **`F6`** (Run Project) o clic derecho en `Main.java` -> `Run File` (`Shift + F6`).
4. **Interacción**:
   - En la consola inferior **Output**, presione **`[6]`** para ejecutar la demostración guiada oficial de la Actividad 2 o **`[1-5]`** para operaciones manuales.

---

### 4. Algoritmos Diseñados (Pseudocódigo y Lógica)

#### 4.1. Algoritmo de Recorrido Completo de la Lista
Visita de forma secuencial cada nodo desde la `cabeza` hasta `null` imprimiendo sus datos sin alterar la lista.

**Pseudocódigo:**
```pascal
Algoritmo RecorrerLista(cabeza)
    Si cabeza es NULO Entonces
        Escribir "La lista está vacía"
        Retornar
    FinSi
    
    actual <- cabeza
    Mientras actual <> NULO Hacer
        Mostrar actual.dato.codigo, actual.dato.solicitante, actual.dato.estado
        actual <- actual.siguiente
    FinMientras
FinAlgoritmo
```
* **Complejidad Temporal:** $O(n)$ — Visita cada uno de los $n$ elementos exactamente una vez.
* **Complejidad Espacial:** $O(1)$ — Solo utiliza un puntero auxiliar `actual`.

---

#### 4.2. Algoritmo de Inserción al Inicio (Caso: 100 | Sofía | Pendiente)
Permite ingresar solicitudes prioritarias que deben ser atendidas de forma inmediata al frente de la secuencia.

**Pseudocódigo:**
```pascal
Algoritmo InsertarAlInicio(cabeza, nuevaSolicitud)
    nuevoNodo <- CrearNodo(nuevaSolicitud)
    nuevoNodo.siguiente <- cabeza
    cabeza <- nuevoNodo
    tamaño <- tamaño + 1
FinAlgoritmo
```
* **Complejidad Temporal:** $O(1)$ — Tiempo constante independiente del número de nodos en la lista.
* **Complejidad Espacial:** $O(1)$.

---

#### 4.3. Algoritmo de Inserción al Final (Caso: 106 | Pedro | Pendiente)
Registra solicitudes ordinarias al final de la cola de espera.

**Pseudocódigo:**
```pascal
Algoritmo InsertarAlFinal(cabeza, nuevaSolicitud)
    nuevoNodo <- CrearNodo(nuevaSolicitud)
    nuevoNodo.siguiente <- NULO
    
    Si cabeza es NULO Entonces
        cabeza <- nuevoNodo
    Sino
        actual <- cabeza
        Mientras actual.siguiente <> NULO Hacer
            actual <- actual.siguiente
        FinMientras
        actual.siguiente <- nuevoNodo
    FinSi
    tamaño <- tamaño + 1
FinAlgoritmo
```
* **Complejidad Temporal:** $O(n)$ — Requiere recorrer hasta el último nodo de la lista simple.
* **Complejidad Espacial:** $O(1)$.

---

#### 4.4. Algoritmo de Inserción Después de un Nodo Dado (Caso: 107 después del 102)
Inserta una solicitud preferencial o derivada inmediatamente después de un caso específico identificado por su código.

**Pseudocódigo:**
```pascal
Algoritmo InsertarDespuesDe(cabeza, codigoReferencia, nuevaSolicitud)
    Si cabeza es NULO Entonces
        Retornar Falso
    FinSi
    
    actual <- cabeza
    Mientras actual <> NULO y actual.dato.codigo <> codigoReferencia Hacer
        actual <- actual.siguiente
    FinMientras
    
    Si actual es NULO Entonces
        Escribir "Nodo de referencia no encontrado"
        Retornar Falso
    FinSi
    
    nuevoNodo <- CrearNodo(nuevaSolicitud)
    nuevoNodo.siguiente <- actual.siguiente
    actual.siguiente <- nuevoNodo
    tamaño <- tamaño + 1
    Retornar Verdadero
FinAlgoritmo
```
* **Complejidad Temporal:** $O(n)$ en el peor caso (por la búsqueda del código).
* **Complejidad Espacial:** $O(1)$.

---

### 5. Demostración y Evolución Estructural Paso a Paso

#### Estado Inicial (5 nodos base del equipo):
```text
cabeza → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → null
```

#### Paso 1: Inserción al INICIO del caso [100 | Sofía | Pendiente]
* **Acción en enlaces:** `nodo(100).siguiente = cabeza (101)`; `cabeza = nodo(100)`.
* **Cómo queda la lista:**
```text
cabeza → [100 | Sofía | Pendiente] → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → null
```

#### Paso 2: Inserción al FINAL del caso [106 | Pedro | Pendiente]
* **Acción en enlaces:** Se recorre hasta [105]. `nodo(105).siguiente = nodo(106)`; `nodo(106).siguiente = null`.
* **Cómo queda la lista:**
```text
cabeza → [100 | Sofía | Pendiente] → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → [106 | Pedro | Pendiente] → null
```

#### Paso 3: Inserción DESPUÉS del nodo 102 del caso [107 | Elena | En atención]
* **Acción en enlaces:**
  1. `nodo(107).siguiente = nodo(102).siguiente` *(apunta a [103])*
  2. `nodo(102).siguiente = nodo(107)`
  *(Este orden evita romper o perder la subsecuencia en memoria).*
* **Cómo queda la lista tras la inserción:**
```text
cabeza → [100 | Sofía | Pendiente] → [101 | Ana | Pendiente] → [102 | Luis | En atención] → [107 | Elena | En atención] → [103 | Rosa | Pendiente] → [104 | Carlos | Pendiente] → [105 | Marta | Resuelto] → [106 | Pedro | Pendiente] → null
```

---

### 6. Cuadro Resumen de Cambios Estructurales y Complejidad

| Operación | Estado de la Estructura | Cambio de Apuntadores / Enlaces | Complejidad |
|---|---|---|:---:|
| **Recorrido Completo** | **Invariable** (Solo lectura) | Se crea un puntero temporal `actual` que avanza nodo a nodo (`actual = actual.siguiente`) hasta llegar a `null`. La estructura física no cambia. | $O(n)$ |
| **Inserción al Inicio** | **Expansión frontal** (+1 nodo) | 1. El nuevo nodo apunta a la antigua cabeza (`nuevo.siguiente = cabeza`).<br>2. La variable `cabeza` se reasigna al nuevo nodo (`cabeza = nuevo`). No se itera la lista. | $O(1)$ |
| **Inserción al Final** | **Expansión posterior** (+1 nodo) | 1. Se recorre hasta el último nodo (`actual.siguiente == null`).<br>2. El último nodo apunta al nuevo (`actual.siguiente = nuevo`).<br>3. El nuevo apunta a `null`. | $O(n)$ |
| **Inserción Después de un Nodo Dado** | **Intercalación intermedia** (+1 nodo) | 1. Se busca el nodo de referencia `actual`.<br>2. `nuevo.siguiente = actual.siguiente`.<br>3. `actual.siguiente = nuevo`. | $O(n)$ |

--- 


## ACTIVIDAD 3 - Lista enlazada simple: búsqueda, modificación y eliminación 


## ACTIVIDAD 4 - Listas dobles y circulares

