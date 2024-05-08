package mates;

import java.util.*;

public class Grafo<V> {
    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /******************************************************************
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso
     * contrario.
     ******************************************************************/
    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
        return false;
    }

    /******************************************************************
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En
     * caso de que no exista alguno de los vértices, lo añade
     * también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     ******************************************************************/
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    /******************************************************************
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     * @throws Exception si el vértice no existe en el grafo.
     ******************************************************************/
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe en el grafo.");
        }
        return adjacencyList.get(v);
    }

    /******************************************************************
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     ******************************************************************/
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /******************************************************************
     * Método `toString()` reescrito para la clase `Grafo.java`.
     *
     * @return una cadena de caracteres con la lista de
     * adyacencia.
     ******************************************************************/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V vertex : adjacencyList.keySet()) {
            sb.append(vertex.toString()).append(": ");
            for (V adjacent : adjacencyList.get(vertex)) {
                sb.append(adjacent.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre
     * `v1` y `v2`. En caso contrario, devuelve `null`.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto
     * entre `v1` y `v2`
     */
    public List<V> shortestPath(V v1, V v2) {
        if (!containsVertex(v1) || !containsVertex(v2))
            return null;

        Map<V, V> parentMap = new HashMap<>();
        Queue<V> queue = new LinkedList<>();
        queue.add(v1);
        parentMap.put(v1, null);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            if (current.equals(v2)) {
                return constructPath(parentMap, v1, v2);
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return null; // No se encontró un camino entre v1 y v2
    }

    private List<V> constructPath(Map<V, V> parentMap, V v1, V v2) {
        LinkedList<V> path = new LinkedList<>();
        V current = v2;
        while (current != null) {
            path.addFirst(current);
            current = parentMap.get(current);
        }
        return path;
    }
}

