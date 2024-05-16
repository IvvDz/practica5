package pr2;

import java.util.ArrayList;
import java.util.List;

import util.Graph;


public class App {
    public static void main(String[] args) {
        System.out.println("\nTest shortestPathFindsAPath");
        System.out.println("----------------------------");
        // Creamos el grafo
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        // Construimos el camino esperado
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(4);

        // Obtenemos el camino más corto
        List<Integer> shortestPath = g.shortestPath(1, 4);

        // Verificamos si el camino devuelto es igual al esperado.
        if (expectedPath.equals(shortestPath)) {
            System.out.println("El camino más corto encontrado es correcto: " + shortestPath);
        } else {
            System.out.println("El camino más corto encontrado es incorrecto. Esperado: " + expectedPath
                    + ", Encontrado: " + shortestPath);
        }
    }
}
