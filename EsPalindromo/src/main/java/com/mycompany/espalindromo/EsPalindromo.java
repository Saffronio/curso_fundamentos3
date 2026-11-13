package com.mycompany.espalindromo;

public class EsPalindromo {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args [0] : "ama";
        boolean esPalindromo = esPalindromo(cadena);
        System.out.println("Cadena: " + cadena);
        System.out.println("¿Es palindromo? "+ esPalindromo);
    }
    public static boolean esPalindromo(String cadena) {
        Deque<Character> pila = newArrayDeque<>();
        for(char c: cadena.toCharArray()) {
            pila.push(c);
        }
        char[] cadenaInvertida = new char[cadena.length()];
        while (!pila.isEmpty()) {
            cadenaInvertida[cadena.length() - pila.size()] = pila.pop();
        }
        String cadenaInvertidaStr = new String(cadenaInvertida);
        System.out.println("Cadena invertida: "+ cadenaInvertidaStr);
        return cadena.equals(cadenaInvertidaStr);
    }
}
