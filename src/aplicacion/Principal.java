package aplicacion;

import mates.Grafo;
import java.util.List;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        // Prueba
        Grafo<Integer> g = new Grafo<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        List<Integer> shortestPath = g.shortestPath(1, 4);
        System.out.println("Shortest path from 1 to 4: " + shortestPath);
    }
}

