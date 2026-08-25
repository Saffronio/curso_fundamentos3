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
    public void print() {
        Student current = this.head;
        while(current !=null) {
            System.out.println(current.name + 
                    " - " + current.score);
            current = current.next;
        }
    }
    public Student retrieve(String searchedName) {
        Student current = this.head;
        while (current != null) {
            if (current.name.equals(searchedName)) 
            { return current; }
            current = current.next;
        }
        return null; //not found
    }
    public void remove (int n) {
        if(head == null) { return; } //empty list
        if (n < 0) { return; } //negative index
        int index = 0;
        Student current = head;
        while (current !=null && index < (n-1)) {
            current = current.next;
            index++;
        }
        while (current != null || current.next == null) {
            return; //position out of range
        }
        current.next = current.next.next;
    }
}

public class PruebaLinkedList {
    public static void main(String[] args) {
        lnkdLst lista = new lnkdLst();
        lista.add("Linus", 90);
        lista.add("Ada", 95);
        lista.add("Paul", 78);
        lista.print();
        Student found = lista.retrieve("Ada");
        System.out.println("Found:"+found.name+" score: "+found.score);
        Student notFound = lista.retrieve("Guido");
        if (notFound != null) {
            System.out.println("Found:"+found.name+" score:"+found.score);
        }
    }
}