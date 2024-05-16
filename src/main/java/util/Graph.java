package util;

import java.util.*;

/**
 * Clase que representa un grafo genérico.
 */
public class Graph<V> {

    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */
    public boolean addVertex(V v) {
        return adjacencyList.putIfAbsent(v, new HashSet<>()) == null;
    }

    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de que no
     * exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        Set<V> adyacentes = adjacencyList.get(v);
        if (adyacentes == null) {
            throw new Exception("Vértice no encontrado en el grafo");
        }
        return adyacentes;
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Método `toString()` reescrito para la clase `Grafo.java`.
     * 
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Set<V>> entrada : adjacencyList.entrySet()) {
            sb.append(entrada.getKey()).append(": ").append(entrada.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre `v1` y `v2`. En caso
     * contrario, devuelve `null`.
     * 
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto entre `v1` y
     *         `v2`
     **/
    public List<V> shortestPath(V v1, V v2) {
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null;
        }

        Queue<V> queue = new LinkedList<>();
        Map<V, V> parentMap = new HashMap<>();
        Set<V> visited = new HashSet<>();

        queue.add(v1);
        visited.add(v1);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            if (current.equals(v2)) {
                return reconstructPath(v1, v2, parentMap);
            }
            for (V neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return null; // No se encontró camino
    }

    private List<V> reconstructPath(V start, V end, Map<V, V> parentMap) {
        List<V> path = new ArrayList<>();
        V current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    public Object caminoMasCorto(int i, int j) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminoMasCorto'");
    }
}
