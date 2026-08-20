package com.mycompany.pruebalinkedlist;

/**
 * @author Angel Daniel Valdez Robles
 * @date Aug 17, 2026
 */

class Student {
    String name;
    int score;
    Student next;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        this.next = null;
    }
}

class lnkdLst {
    Student head;
    public lnkdLst() {
        this.head = null;
    }

    public void add(String name, int score) {
        Student newStudent = new Student(name, score);
        if (head == null) {
            head = newStudent;
            return;
        }

        Student current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newStudent;
    }
}

public class PruebaLinkedList {
    public static void main(String[] args) {
        lnkdLst lista = new lnkdLst();
        lista.add("Linus", 90);
        lista.add("Ada", 95);
        lista.add("Paul", 78);
    }
}