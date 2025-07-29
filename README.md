![Logo](./resources/logoupscolor.svg)

# Proyecto Final – Estructura de Datos: Resolución de Laberintos con Búsqueda y Optimización

Este proyecto implementa distintas estrategias algorítmicas para la resolución de laberintos, aplicando estructuras de datos, patrones de diseño y técnicas de programación orientada a objetos. El enfoque principal es comparar diversos algoritmos de búsqueda en un entorno visual basado en Java y Swing.

---
## 📌 Información General

- **Título:** Proyecto Final
- **Asignatura:** Estructura de Datos
- **Carrera:** Computación
- **Estudiantes:** Nicolás Cedillo y Mateo Miller
- **Correos:** ecedilloa@est.ups.edu.ec y mmillerm@est.ups.edu.ec
- **Fecha:** 29 de Julio de 2025
- **Profesor:** Ing. Pablo Torres

---

## Estructura del Proyecto (MVC + DAO)

```
src/
├── MazeApp.java                  # Punto de entrada principal
├── controllers/                 # Controlador general de interacción entre vista y modelo
│   └── MazeController.java
├── dao/                        # Interfaces de acceso a datos
│   ├── AlgorithmResultDAO.java
│   └── daoImpl/                # Implementaciones concretas de DAO
│       └── AlgorithmResultDAOFile.java
├── models/                     # Clases de datos y modelos lógicos
│   ├── AlgorithmResult.java
│   ├── Cell.java
│   ├── CellState.java
│   └── SolveResults.java
├── resources/                  # (Reservado para futuros assets)
├── solver/                     # Interfaces de algoritmos de resolución
│   ├── MazeSolver.java
│   └── solverImpl/             # Implementaciones de algoritmos
│       ├── MazeSolverBFS.java
│       ├── MazeSolverDFS.java
│       ├── MazeSolverRecursivo.java
│       ├── MazeSolverRecursivoCompleto.java
│       └── MazeSolverRecursivoCompletoBT.java
└── views/                      # Interfaz gráfica
    ├── MazeFrame.java
    ├── MazePanel.java
    └── ResultadosDialog.java   # Tabla y gráfica de resultados
```

---

## Descripción del Problema

Se busca resolver un laberinto representado como una matriz de celdas transitables o no transitables, buscando encontrar la ruta más corta entre un punto de inicio A y uno de destino B.

---

## Marco Teórico
Cada algoritmo está implementado en una clase diferente dentro de `solverImpl/`:

* Recursivo (2 direcciones)
* Recursivo (4 direcciones)
* Recursivo (4 direcciones + Backtracking)
* BFS (Breadth-First Search)
* DFS (Depth-First Search)

---

## Interfaz de Usuario (Java Swing)

* Permite seleccionar el tamaño del laberinto.
* Permite marcar celdas como inicio, fin o muro.
* Ejecuta el algoritmo seleccionado y muestra visualmente el camino encontrado.
* Visualiza resultados anteriores en una **tabla**.
* Opcion de graficar tiempos de ejecución por algoritmo usando **JFreeChart**.

---

## Resultados y Archivo de Registro

Los resultados se guardan en un archivo `results.csv`, utilizando un DAO (`AlgorithmResultDAO`) para mantener independencia de la lógica de persistencia.

Desde la interfaz:

* Se puede ver una tabla con: nombre del algoritmo, longitud del camino, tiempo de ejecución.
* Se puede graficar los tiempos por algoritmo.
* Se puede limpiar el archivo.


---

## UML

![UML](https://www.plantuml.com/plantuml/svg/xHfTRzisy3s_Ga-RYgK6jkSig6ChdJj_6BQH02lwG4kqmvOIFObAYtRxxwDqyPavdUn4w8e2Mu3QjEw3zttKiQyAAt9Rflhin-1NznSCPjFLOZOUHujb_U1i9-8FOYk3s6GsDrhBl0Y-d0NkBzQYA8A9-2m720DG_VUo1ir59dMmgpuzo4PbGjVmwroaCjXKdnvy8BKELAPY9JHz74kTY6l7m8SihR0oa4wRnFHZMuhcRdszDRcoT-b25gMsm_uioFSh8lJIw7kdIufB3pvMXRse1BbqMXN6hH9HtAX2MU4MHn15gZBZmLLcQy0eI-GdekJRTs_VWQB5d9XkROoM8gjaTw8fCtSi-jN76IBzH9mI4aE_y33lZKfWfu5MyGVfb0WhoJPAY_DQnjZe1a4XxG3Ty0py4RZq2HJMRBTQtWhz56IjqfqIUTW6G1krOX0tDl5SCDhNkKf2JhUeZhiGWZ0hvRs19nuknEvMshq961ZQb80nZogSUi5A0H2IT0mYYk1bsIaNayppiK5h9rdaPHyZ845ePYoG_0rnBBCo1TPUILck-eiL_egcGrpVziVZ1tB7mAjLFLAVNhwy3EwL_0W0IAwdeaCEdK9FJI9rqQiiTWhL1zUwdY98XEarwzWfggAkORr3PNkY2ZKox7AsxmzkkyTzmOa47ggoK-OR4Kkkr7wXyR0jHPw8F2IuK9Fhe010wcSH55L2SXd8h5P8v2kJc29ihmyO8hxuFVTlbEwqtzOO5QwLbe_QM_rWupxyoilJkMZFLRODyjAA5z-P9JmBz5gqxlN-Qeszq-H-I2462Ql9R1YDoOa3I8vvnS5ebtymT6RITIw7KgFjA6RTJBJ9jigMYKE4_inYMINJdJdYvR1rrtD-ctEFh-yZu5CLqsc3bU_CcjnXZGbZ5cbLRnCo-rW32mo7WwcX9_dstRTXyYWskampGGumVdK_8vsEDdHWm0X-xN0uXGP0qMYXf1CTQzLKHhhBdIZk1YQHFWyyUia_Iw6BSBP-BsCRcFNxPkT2_fu91xF9A9h-CSC7rwCfhkVzHJGyeFOde-a1yQO_6CsQBiBwp6Q2T_wy9yTJ5Yc61F3qzVY77BVp9hbdBX_eV4BELYpE8OQXE65vMCx6Dz4Y64rNqUAwFuY27uBHP3wE9j5qrL-DPbDIEg3ZqRxad-s_y7h22SlUXNWAJuLBaK_da0_rsrArUlWjiwJwzWHkzDhWejVppmGy1jToYJOz-AATobFgwdhvJBq-F-WHu-7Su1Cuw-rLu_OR7lQ3bp29mN3ohGWZ9eAEHUh4eqt25rzRAUPzAjaKJJvifG9cLTvBg-uDnqmMDqAhn3oHup4ZBsHSveNRvzytDMxrJGo-BejOv93otxVb1yRbdBJVq0Eu9Uk9_prnjVh6le1D_slUW8v6kq_lKSU1buD6KpjADkn6XXxfGapTsPq92KIUpMcxaLd1C_7XT7cQY7-RF-6Tvmatx2BSiADmw06wPJSgNb2Uj9ku962e0DsfKVpkBBghSRLY5UbE3wfqPy9Fgsv6qIrvDQYdUOq3D4p7-SkB55RC1SOR9ydQN9bF5_0UVHc8_M1WARMChScRD5UbjIQ3YW7hLfpTVcxDupaPTnTeEzS4Z3QvQs_Lb_Hw7_9R6_VfP5Oo-spQuAujjO7c8fU93vp8h1nPcGRfms9Q3R_7Ojt6OGppfwCyRfohzwElWlHcSDJ92GAyxkySy66ql_pet0DJx9rmBF0T3cm73_7dCKxTcOXCfIB7iud_IgtTKlny7j17lpIOfAQendlEQ8LuZtF557wsNXhWZz13OSBNeVPf1Fiaw4o7k3m4S1iAthimQ5pimX00mhUDvvKNm-o8Xs2miT_A6-AVPOj0cYrjp31ZM7OUk9bZFW4cIEr8acSCQmQfFKzPSWmMfWQsAul9pSX44BYIS49bKXROFNZC4mM66NsYskpyis06e5NvOGfkTSu69MMZa865aQbVoKJHOcLOFMj-Mh03L12P03sXqQMIjpHmHk4P56yFpqWiAkx4M8tnpi5rGoMqsJvoqb1kX8Ekn5hBINKkWDE1Xy7_Dm3Ww7vucFmDIyws9YfK-CQxRpzdDeLcqNpklxl1tRp8jo8V2ej1Zq3i1gzpiL6ny0UdvDAIlVUaEFH4H-3yYGtFMeje3AFOtqRpPN0LhMwZQEhcseFV-wyZE8HzJzURe4E7cz2lUrjCVV9APacPwdy0)

[Ver diagrama UML completo](https://www.plantuml.com/plantuml/svg/xHfTRzisy3s_Ga-RYgK6jkSig6ChdJj_6BQH02lwG4kqmvOIFObAYtRxxwDqyPavdUn4w8e2Mu3QjEw3zttKiQyAAt9Rflhin-1NznSCPjFLOZOUHujb_U1i9-8FOYk3s6GsDrhBl0Y-d0NkBzQYA8A9-2m720DG_VUo1ir59dMmgpuzo4PbGjVmwroaCjXKdnvy8BKELAPY9JHz74kTY6l7m8SihR0oa4wRnFHZMuhcRdszDRcoT-b25gMsm_uioFSh8lJIw7kdIufB3pvMXRse1BbqMXN6hH9HtAX2MU4MHn15gZBZmLLcQy0eI-GdekJRTs_VWQB5d9XkROoM8gjaTw8fCtSi-jN76IBzH9mI4aE_y33lZKfWfu5MyGVfb0WhoJPAY_DQnjZe1a4XxG3Ty0py4RZq2HJMRBTQtWhz56IjqfqIUTW6G1krOX0tDl5SCDhNkKf2JhUeZhiGWZ0hvRs19nuknEvMshq961ZQb80nZogSUi5A0H2IT0mYYk1bsIaNayppiK5h9rdaPHyZ845ePYoG_0rnBBCo1TPUILck-eiL_egcGrpVziVZ1tB7mAjLFLAVNhwy3EwL_0W0IAwdeaCEdK9FJI9rqQiiTWhL1zUwdY98XEarwzWfggAkORr3PNkY2ZKox7AsxmzkkyTzmOa47ggoK-OR4Kkkr7wXyR0jHPw8F2IuK9Fhe010wcSH55L2SXd8h5P8v2kJc29ihmyO8hxuFVTlbEwqtzOO5QwLbe_QM_rWupxyoilJkMZFLRODyjAA5z-P9JmBz5gqxlN-Qeszq-H-I2462Ql9R1YDoOa3I8vvnS5ebtymT6RITIw7KgFjA6RTJBJ9jigMYKE4_inYMINJdJdYvR1rrtD-ctEFh-yZu5CLqsc3bU_CcjnXZGbZ5cbLRnCo-rW32mo7WwcX9_dstRTXyYWskampGGumVdK_8vsEDdHWm0X-xN0uXGP0qMYXf1CTQzLKHhhBdIZk1YQHFWyyUia_Iw6BSBP-BsCRcFNxPkT2_fu91xF9A9h-CSC7rwCfhkVzHJGyeFOde-a1yQO_6CsQBiBwp6Q2T_wy9yTJ5Yc61F3qzVY77BVp9hbdBX_eV4BELYpE8OQXE65vMCx6Dz4Y64rNqUAwFuY27uBHP3wE9j5qrL-DPbDIEg3ZqRxad-s_y7h22SlUXNWAJuLBaK_da0_rsrArUlWjiwJwzWHkzDhWejVppmGy1jToYJOz-AATobFgwdhvJBq-F-WHu-7Su1Cuw-rLu_OR7lQ3bp29mN3ohGWZ9eAEHUh4eqt25rzRAUPzAjaKJJvifG9cLTvBg-uDnqmMDqAhn3oHup4ZBsHSveNRvzytDMxrJGo-BejOv93otxVb1yRbdBJVq0Eu9Uk9_prnjVh6le1D_slUW8v6kq_lKSU1buD6KpjADkn6XXxfGapTsPq92KIUpMcxaLd1C_7XT7cQY7-RF-6Tvmatx2BSiADmw06wPJSgNb2Uj9ku962e0DsfKVpkBBghSRLY5UbE3wfqPy9Fgsv6qIrvDQYdUOq3D4p7-SkB55RC1SOR9ydQN9bF5_0UVHc8_M1WARMChScRD5UbjIQ3YW7hLfpTVcxDupaPTnTeEzS4Z3QvQs_Lb_Hw7_9R6_VfP5Oo-spQuAujjO7c8fU93vp8h1nPcGRfms9Q3R_7Ojt6OGppfwCyRfohzwElWlHcSDJ92GAyxkySy66ql_pet0DJx9rmBF0T3cm73_7dCKxTcOXCfIB7iud_IgtTKlny7j17lpIOfAQendlEQ8LuZtF557wsNXhWZz13OSBNeVPf1Fiaw4o7k3m4S1iAthimQ5pimX00mhUDvvKNm-o8Xs2miT_A6-AVPOj0cYrjp31ZM7OUk9bZFW4cIEr8acSCQmQfFKzPSWmMfWQsAul9pSX44BYIS49bKXROFNZC4mM66NsYskpyis06e5NvOGfkTSu69MMZa865aQbVoKJHOcLOFMj-Mh03L12P03sXqQMIjpHmHk4P56yFpqWiAkx4M8tnpi5rGoMqsJvoqb1kX8Ekn5hBINKkWDE1Xy7_Dm3Ww7vucFmDIyws9YfK-CQxRpzdDeLcqNpklxl1tRp8jo8V2ej1Zq3i1gzpiL6ny0UdvDAIlVUaEFH4H-3yYGtFMeje3AFOtqRpPN0LhMwZQEhcseFV-wyZE8HzJzURe4E7cz2lUrjCVV9APacPwdy0)

---

## Capturas del Laberinto

* Captura del BFS
![Captura_BFS](./resources/captura_bfs.png)

* Captura del DFS
![Captura_DFS](./resources/captura_dfs.png)

---

## Codigo ejemplo (BFS)

- A continuacion, la explicacion del metodo BFS:

```java
/**
 * Implementación del algoritmo de Búsqueda en Anchura (BFS) para resolver laberintos.
 */
public class MazeSolverBFS implements MazeSolver {

    /**
     * Busca el camino más corto desde celdaInicio hasta celdaFin usando BFS.
     * 
     * @param matriz      Matriz de celdas del laberinto
     * @param celdaInicio Celda de inicio
     * @param celdaFin    Celda de destino
     * @return SolveResult con el recorrido y el camino encontrado
     */
    @Override
    public SolveResult getPath(Cell[][] matriz, Cell celdaInicio, Cell celdaFin) {

        // Obtiene las dimensiones de la matriz del laberinto
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Matriz para marcar las celdas visitadas
        boolean[][] visitado = new boolean[filas][columnas];
        // Mapa para reconstruir el camino desde el final hasta el inicio
        HashMap<Cell, Cell> mapaPadres = new HashMap<>();
        // Cola para la búsqueda BFS
        LinkedList<Cell> cola = new LinkedList<>();
        // Lista para registrar el recorrido de las celdas visitadas en orden
        ArrayList<Cell> recorrido = new ArrayList<>();

        // Se obtiene la referencia real de las celdas de inicio y fin en la matriz
        Cell inicio = matriz[celdaInicio.getFila()][celdaInicio.getColumna()];
        Cell fin = matriz[celdaFin.getFila()][celdaFin.getColumna()];

        // Se inicia la búsqueda añadiendo la celda de inicio a la cola
        cola.add(inicio);
        visitado[inicio.getFila()][inicio.getColumna()] = true;

        // Posibles movimientos: abajo, arriba, derecha, izquierda
        int[][] direcciones = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        // Mientras la cola no esté vacía, sigue explorando el laberinto
        while (!cola.isEmpty()) {
            // Saca la siguiente celda a explorar
            Cell actual = cola.poll();
            // Agrega la celda al recorrido (todas las que fueron visitadas)
            recorrido.add(actual);

            // Si se llegó a la celda final, termina la búsqueda
            if (actual.equals(fin)) {
                break;
            }

            // Para cada dirección posible
            for (int[] dir : direcciones) {
                int nuevaFila = actual.getFila() + dir[0];
                int nuevaColumna = actual.getColumna() + dir[1];

                // Verifica que la nueva posición esté dentro de los límites de la matriz
                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                    Cell vecino = matriz[nuevaFila][nuevaColumna];
                    // Si el vecino no ha sido visitado y no es una pared
                    if (!visitado[nuevaFila][nuevaColumna] && vecino.getEstado() != CellState.PARED) {
                        // Marca como visitado, guarda el padre para reconstruir el camino y lo agrega a la cola
                        visitado[nuevaFila][nuevaColumna] = true;
                        mapaPadres.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        // Reconstrucción del camino desde el final hasta el inicio usando el mapa de padres
        List<Cell> camino = new ArrayList<>();
        Cell actual = fin;
        // Mientras haya padre para el nodo actual, se va agregando al camino
        while (mapaPadres.containsKey(actual)) {
            camino.add(actual);
            actual = mapaPadres.get(actual);
        }
        // Si se llegó correctamente al inicio, se agrega y se invierte la lista para mostrar el camino desde el inicio al fin
        if (actual.equals(inicio)) {
            camino.add(inicio);
            Collections.reverse(camino);
        } else {
            // Si no se pudo llegar al inicio, el camino está vacío (no hay solución)
            camino.clear(); 
        }
        // Devuelve el resultado con el recorrido y el camino hallado (si existe)
        return new SolveResult(recorrido, camino);
    }

}
```

---

## Conclusiones

* Nicolas Cedillo: El algoritmo BFS (Búsqueda en Anchura) es especialmente recomendable cuando se necesita encontrar el camino más corto en un laberinto o grafo. Esto lo convierte en la opción ideal si el objetivo es optimizar la cantidad de pasos desde un punto de inicio hasta un destino, ya que BFS explora todas las rutas posibles por niveles. Además, es útil en situaciones donde se requiere explorar todas las posiciones alcanzables a cierta distancia del origen. BFS es el método preferido cuando el grafo es pequeño o moderado en tamaño, ya que su consumo de memoria puede crecer considerablemente en escenarios muy grandes. En resumen, BFS es la mejor alternativa cuando la prioridad es encontrar caminos mínimos de forma garantizada en grafos pequeños para ahorrar memoria.

* Mateo Miller: El algoritmo DFS (Búsqueda en Profundidad) puede ser la mejor opción en laberintos o grafos cuando el objetivo no es necesariamente encontrar el camino más corto, sino llegar rápidamente a una solución viable, especialmente en estructuras grandes o profundas donde existen muchos caminos potenciales y pocos conducen a la meta. DFS consume menos memoria que BFS porque solo necesita almacenar el camino actual, lo que lo hace adecuado para laberintos con muchas ramificaciones o en los que los caminos largos son frecuentes pero las soluciones se encuentran en lo profundo de la estructura. En resumen, DFS es preferible cuando se prioriza el bajo consumo de memoria, la exploración exhaustiva o cuando se busca cualquier solución rápidamente en estructuras muy ramificadas o profundas.

---

## Recomendaciones y Aplicaciones futuras

* En futuras versiones se podría incorporar memoización o tabulación para reducir la complejidad temporal en algunos algoritmos recursivos.

* Incorporar más algoritmos de resolución de laberintos, y permitir que los usuarios comparen sus resultados en términos de eficiencia y recursos utilizados. Complementariamente, se podrían implementar métricas adicionales, como el consumo de memoria, la cantidad de nodos expandidos y el análisis de complejidad temporal/espacial. Esto enriquecería el proyecto para propósitos educativos y de investigación.

---

## Contribuciones

* Nicolas Cedillo - Estudiante
* Mateo Miller - Estudiante
