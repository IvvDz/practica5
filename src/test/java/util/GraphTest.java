package util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Pruebas unitarias para la clase Graph.
 */
public class GraphTest {

    @Test
    public void caminoMasCortoEncuentraUnCamino() {
        System.out.println("\nPrueba caminoMasCortoEncuentraUnCamino");
        System.out.println("---------------------------------------");
        // Construimos el grafo
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        // Construimos el camino esperado
        List<Integer> caminoEsperado = new ArrayList<>();
        caminoEsperado.add(1);
        caminoEsperado.add(5);
        caminoEsperado.add(4);

        // Verificamos si el camino devuelto es igual al esperado.
        assertEquals(caminoEsperado, g.caminoMasCorto(1, 4));
    }
}
