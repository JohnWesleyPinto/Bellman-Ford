import java.util.*;
import java.io.*;

public class BellmanFord {

    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int vertices, int start) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = dist[edge.source] + edge.weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                StdOut.println("O grafo contem um ciclo negativo!");
                return;
            }
        }

        StdOut.println("Distancias mais curtas a partir do vertice " + start + ":");
        for (int i = 0; i < vertices; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                StdOut.println("Vertice " + i + " não é alcancavel.");
            } else {
                StdOut.println("Distancia para o vertice " + i + ": " + dist[i]);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = args[0];
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        try {
            int vertices = scanner.nextInt();
            int arestas = scanner.nextInt();

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < arestas; i++) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.add(new Edge(source, destination, weight));
            }

            int start = scanner.nextInt();
            bellmanFord(edges, vertices, start);

        } catch (InputMismatchException e) {
            System.out.println("Erro ao ler os dados do arquivo. Verifique o formato.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
