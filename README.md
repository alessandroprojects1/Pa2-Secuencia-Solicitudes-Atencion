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


---------------------------------------------------------------------------------------------------------------------------------
## ACTIVIDAD 3 - Lista enlazada simple: búsqueda, modificación y eliminación 
  ### 1. Problema
   
  Una organización gestiona una secuencia dinámica de solicitudes de atención. Constantemente ingresan casos nuevos, se eliminan otros y algunos deben reubicarse. Cada solicitud se representa como un nodo con los siguientes datos:
   
  - **codigo:** número entero único que identifica el caso.
  - **solicitante:** nombre de la persona que hizo la solicitud.
  - **estado:** situación del caso (Pendiente, En atención o Resuelto).
  - **siguiente:** enlace al siguiente nodo de la lista.
  La lista de trabajo del equipo tiene siempre estos 5 casos:
   
  `cabeza -> [101 | Ana | Pendiente] -> [102 | Luis | En atención] -> [103 | Rosa | Pendiente] -> [104 | Carlos | Pendiente] -> [105 | Marta | Resuelto] -> null`
   
  El problema consiste en poder **localizar** una solicitud concreta, **actualizar** su estado y **retirarla** de la lista cuando corresponda, sin perder el orden ni los enlaces de los demás casos.
   
  ### 2. Objetivo
   
  Diseñar y desarrollar, sobre una lista enlazada simple, los algoritmos de búsqueda, modificación y eliminación de un nodo, explicar cómo se reajustan los enlaces al eliminar, y comparar la eficiencia básica de insertar y eliminar según el punto de intervención (inicio, medio o final).
   
  ### 3. Solución desarrollada
   
  #### 3.1 Algoritmo de búsqueda
   
  **Criterio definido por el equipo:** el código del caso. Se eligió porque es único, así que la búsqueda nunca devuelve más de un nodo ni genera ambigüedad (buscar por nombre podría coincidir con varios casos).
   
  **Pasos:**
  1. Se coloca una referencia auxiliar en el primer nodo de la lista (la cabeza).
  2. Mientras la referencia no sea nula, se compara el código del nodo actual con el código buscado.
  3. Si coinciden, se devuelve ese nodo y termina la búsqueda.
  4. Si no coinciden, la referencia avanza al nodo siguiente.
  5. Si se llega al final de la lista (referencia nula) sin coincidencias, se informa que el caso no existe.
  **Ejemplo:** al buscar el código 103, se revisan en orden el 101, el 102 y el 103, donde se encuentra la coincidencia. Al buscar el 999, se recorre toda la lista y se informa que no existe.
   
  #### 3.2 Algoritmo de modificación
   
  **Pasos:**
  1. Se usa el algoritmo de búsqueda para localizar el nodo con el código indicado.
  2. Si el nodo no existe, la operación termina informando que no se pudo modificar.
  3. Si existe, se reemplaza el valor del campo estado por el nuevo estado.
  4. Los enlaces no se tocan: el nodo conserva su posición y su código.
  **Ejemplo:** se modifica el caso 102, que pasa de "En atención" a "Resuelto".
   
  `cabeza -> [101 | Ana | Pendiente] -> [102 | Luis | Resuelto] -> [103 | Rosa | Pendiente] -> [104 | Carlos | Pendiente] -> [105 | Marta | Resuelto] -> null`
   
  #### 3.3 Algoritmo de eliminación y reajuste de enlaces
   
  **Pasos:**
  1. Si la lista está vacía, no hay nada que eliminar.
  2. Si el nodo a eliminar es el primero, la cabeza pasa a apuntar al segundo nodo.
  3. Si no es el primero, se recorre la lista manteniendo dos referencias: la del nodo actual y la del nodo anterior a él.
  4. Cuando el código del nodo actual coincide con el buscado, el enlace "siguiente" del nodo anterior se redirige al nodo que sigue al actual. De esta forma el nodo eliminado queda "saltado".
  5. El nodo eliminado ya no tiene ninguna referencia que lo alcance, por lo que Java lo libera automáticamente.
  6. Si se llega al final sin encontrar el código, se informa que el caso no existe.
  **Cómo se reajustan los enlaces:**
   
  - **Eliminar un nodo del medio (caso 103):** el nodo 102 deja de apuntar al 103 y pasa a apuntar al 104.
    - Antes: `[101] -> [102] -> [103] -> [104] -> [105] -> null`
    - Después: `[101] -> [102] -> [104] -> [105] -> null`
  - **Eliminar el último nodo (caso 105):** el nodo anterior (104) pasa a apuntar a `null`, por lo que se convierte en el nuevo último.
    - Antes: `[101] -> [102] -> [104] -> [105] -> null`
    - Después: `[101] -> [102] -> [104] -> null`
  - **Eliminar el primer nodo (caso 101):** no hay nodo anterior; la cabeza avanza al segundo nodo.
  #### 3.4 Comparación de eficiencia: inserción y eliminación según el punto de intervención
   
  La lista simple solo conoce el primer nodo (la cabeza) y cada nodo solo sabe cuál es el siguiente. Para llegar a cualquier otro punto hay que recorrer la lista desde el inicio, por eso la eficiencia depende de dónde se haga el cambio.
   
  - **Al inicio:** es la operación más eficiente. Solo se modifica la cabeza: al insertar, el nuevo nodo apunta a la antigua cabeza; al eliminar, la cabeza pasa al segundo nodo. Son siempre los mismos pasos, sin importar cuántos casos haya.
  - **En el medio:** primero hay que recorrer la lista hasta encontrar el nodo de referencia (y su anterior, al eliminar). Ese recorrido es lo que más cuesta. Una vez ubicado, reajustar los enlaces es rápido: basta cambiar uno o dos enlaces.
  - **Al final:** es menos eficiente, porque la lista no sabe dónde termina y hay que recorrerla completa hasta el último nodo. Para eliminar el último, además se necesita llegar al penúltimo y hacer que apunte a `null`.
  | Operación | Inicio | Medio | Final |
  |---|---|---|---|
  | Inserción | Muy eficiente | Requiere recorrido previo | Requiere recorrer toda la lista |
  | Eliminación | Muy eficiente | Requiere recorrido previo | Requiere recorrer toda la lista |
   
  **Ejemplo con la lista del caso:** eliminar el 101 toma 1 paso, mientras que eliminar el 105 obliga a pasar por los nodos 101, 102, 103 y 104.
   
  **Conclusión aplicada al caso:** atender o retirar la solicitud más antigua (al inicio) es rápido, pero registrar o cerrar casos al final se vuelve más lento a medida que crece la lista. Esta es una limitación de la lista simple que las listas dobles ayudan a resolver (Actividad 4).

  
### 4. Comparación de eficiencia en una lista enlazada simple
La eficiencia de insertar y eliminar depende de dónde se hace el cambio, porque la lista simple solo conoce el primer nodo (`cabeza`) y cada nodo solo sabe cuál es el siguiente. Para llegar a cualquier otro punto hay que recorrer la lista desde el inicio.
 
#### Al inicio
 
Es la operación más eficiente. Solo se modifica la `cabeza`: al insertar, el nuevo nodo apunta a la antigua cabeza; al eliminar, la cabeza pasa al segundo nodo. Son siempre los mismos pasos, sin importar cuántos casos haya en la lista.
 
#### En el medio
 
Primero hay que recorrer la lista hasta encontrar el nodo de referencia (y su anterior, en el caso de eliminar). Ese recorrido es lo que más cuesta. Una vez ubicado, reajustar los enlaces es rápido: basta cambiar uno o dos punteros. Por ejemplo, para eliminar el caso 103 se recorre hasta el 102 y se hace que apunte al 104.
 
#### Al final
 
Es menos eficiente, porque la lista no sabe dónde termina y hay que recorrerla completa hasta el último nodo. Para eliminar el último se necesita además llegar al penúltimo y hacer que apunte a `null`.
 
#### Resumen
 
| Operación | Inicio | Medio | Final |
|---|---|---|---|
| Inserción | Muy eficiente | Requiere recorrido previo | Requiere recorrer toda la lista |
| Eliminación | Muy eficiente | Requiere recorrido previo | Requiere recorrer toda la lista |
 
#### Conclusión aplicada al caso
 
Atender o retirar la solicitud más antigua (al inicio) es rápido, pero registrar o cerrar casos al final se vuelve más lento a medida que crece la lista. Esto muestra una limitación de la lista simple, que las listas dobles ayudan a resolver en la Actividad 4.


## ACTIVIDAD 4 - Listas dobles y circulares

