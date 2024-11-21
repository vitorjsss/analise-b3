package com.projeto.b3.estruturasDados;

public class Stack {
    private Node top;

    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public Stack() {
        top = null;
    }

    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public Object pop() {
        if (top == null)
            return null;

        Object data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
