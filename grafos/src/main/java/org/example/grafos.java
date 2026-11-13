package org.example;
public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("C:\\codigos intellij\\grafos\\grafo_salon2.txt");
        System.out.println("Contenido del archivo:\n" + texto);
        GrafoArrays grafo = new GrafoArrays(texto);
        grafo.display();
        grafo.DFS("Arcangel", "Sergio");
        grafo.BFS("Sergio", "Arcangel");
        String caminoDijkstra = grafo.caminoMasCorto("Arcangel", "Sergio");
        System.out.println(caminoDijkstra);
        GrafoListas grafolista = new GrafoListas(texto);
        grafolista.display();
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