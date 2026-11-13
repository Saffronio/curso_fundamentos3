package com.mycompany.grafos;
class GrafoArrays {
    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;

    public GrafoArrays(String origen) {
        creaTablaVertices(origen);
        matrizAdyacencia = new int[numVertices][numVertices];

        // Agregar las aristas después de crear los vértices
        populateMatrizAdyacencia(origen);
    }

    private void creaTablaVertices(String origen) {
        String[] lineas = origen.split("\\R");
        vertices = new String[lineas.length * 2];
        numVertices = 0;

        for (String linea : lineas) {
            String arista = linea.trim();

            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }

            int etiqueta = arista.indexOf('[');
            if (etiqueta >= 0) {
                arista = arista.substring(0, etiqueta);
            }

            String[] extremos = arista.split("--", 2);

            if (extremos.length == 2) {
                agregarVertice(extremos[0].trim());
                agregarVertice(extremos[1].trim());
            }
        }
    }

    private void agregarVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return;
            }
        }

        vertices[numVertices] = vertice;
        numVertices++;
    }

    private int obtenerIndiceVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarArista(String vertice1, String vertice2, int peso) {
        int indice1 = obtenerIndiceVertice(vertice1);
        int indice2 = obtenerIndiceVertice(vertice2);

        if (indice1 >= 0 && indice2 >= 0) {
            matrizAdyacencia[indice1][indice2] = peso;
            matrizAdyacencia[indice2][indice1] = peso;
        }
    }

    // 1. Procesar el archivo y llenar la matriz de adyacencia
    private void populateMatrizAdyacencia(String origen) {
    String[] lineas = origen.split("\\R");

    for (String linea : lineas) {

        linea = linea.trim();

        // Ignorar encabezados y líneas sin aristas
        if (!linea.contains("--")) {
            continue;
        }

        // Separar la conexión de sus atributos
        int inicioEtiqueta = linea.indexOf('[');

        if (inicioEtiqueta < 0) {
            continue;
        }

        String conexion = linea.substring(0, inicioEtiqueta).trim();
        String atributos = linea.substring(inicioEtiqueta);

        // Separar los dos vértices
        String[] extremos = conexion.split("--", 2);

        if (extremos.length == 2) {

            String vertice1 = extremos[0].trim();
            String vertice2 = extremos[1].trim();

            // Extraer el peso de label="..."
            int inicioPeso = atributos.indexOf("label=\"");

            if (inicioPeso >= 0) {

                inicioPeso += 7;

                int finPeso = atributos.indexOf("\"", inicioPeso);

                if (finPeso > inicioPeso) {

                    String pesoTexto = atributos.substring(
                        inicioPeso, finPeso
                    );

                    int peso = Integer.parseInt(pesoTexto);

                    // Agregar la arista a la matriz
                    agregarArista(vertice1, vertice2, peso);
                }
            }
        }
    }
}

    // 2. Mostrar los vértices
    public void display() {
        System.out.println("Vértices del grafo:");
        displayVertices();

        System.out.println("\nMatriz de adyacencia:");
        displayMatrizAdyacencia();
    }

    private void displayVertices() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }
    }

    // 3. Mostrar la matriz de adyacencia
    private void displayMatrizAdyacencia() {

        // Encabezado de columnas
        System.out.print("     ");

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%8s", vertices[i]);
        }

        System.out.println();

        // Filas de la matriz
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%5s", vertices[i]);

            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%8d", matrizAdyacencia[i][j]);
            }

            System.out.println();
        }
    }
}

public class Grafos {

    public static void main(String[] args) {

        String texto = cargaArchivo(
            "C:\\Users\\eldan\\OneDrive\\Documentos\\Fundamentos3\\grafos\\grafo_salon.txt"
        );

        System.out.println("Contenido del archivo:\n" + texto);

        GrafoArrays grafo = new GrafoArrays(texto);

        grafo.display();
    }

    public static String cargaArchivo(String ruta) {
        String contenido = "";

        try {
            java.io.File archivo = new java.io.File(ruta);
            java.util.Scanner scanner = new java.util.Scanner(archivo);

            while (scanner.hasNextLine()) {
                contenido += scanner.nextLine() + "\n";
            }

            scanner.close();

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        }

        return contenido;
    }
}
