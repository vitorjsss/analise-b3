package com.projeto.b3.estruturasDados;

import com.projeto.b3.modelos.Registro;

public class Queue {
    private Nodo frente;
    private Nodo tras;
    private int tamanho;

    private class Nodo {
        Registro dado;
        Nodo proximo;

        Nodo(Registro dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    public Queue() {
        frente = null;
        tras = null;
        tamanho = 0;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public void enfileirar(Registro dado) {
        Nodo novoNodo = new Nodo(dado);
        if (tras == null) {
            frente = novoNodo;
            tras = novoNodo;
        } else {
            tras.proximo = novoNodo;
            tras = novoNodo;
        }
        tamanho++;
    }

    public Registro desenfileirar() {
        if (isEmpty()) {
            return null;
        }
        Registro dado = frente.dado;
        frente = frente.proximo;
        if (frente == null) {
            tras = null;
        }
        tamanho--;
        return dado;
    }

    public int tamanho() {
        return tamanho;
    }

    // Métodos de ordenação QuickSort
    public void ordenarPorTicker() {
        Registro[] vetor = converterParaArray();
        quickSortTicker(vetor, 0, vetor.length - 1);
        reconstruirFila(vetor);
    }

    public void ordenarPorVolume() {
        Registro[] vetor = converterParaArray();
        quickSortVolume(vetor, 0, vetor.length - 1);
        reconstruirFila(vetor);
    }

    public void ordenarPorVariacao() {
        Registro[] vetor = converterParaArray();
        quickSortVariacoes(vetor, 0, vetor.length - 1);
        reconstruirFila(vetor);
    }

    // Converte a fila para um vetor para facilitar a ordenação
    private Registro[] converterParaArray() {
        Registro[] vetor = new Registro[tamanho];
        Nodo atual = frente;
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = atual.dado;
            atual = atual.proximo;
        }
        return vetor;
    }

    // Reconstruir a fila com o vetor ordenado
    private void reconstruirFila(Registro[] vetor) {
        frente = null;
        tras = null;
        tamanho = 0;
        for (Registro registro : vetor) {
            enfileirar(registro);
        }
    }

    // QuickSort para Ticker
    private void quickSortTicker(Registro[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = partitionTicker(vetor, inicio, fim);
            quickSortTicker(vetor, inicio, pivo - 1);
            quickSortTicker(vetor, pivo + 1, fim);
        }
    }

    private int partitionTicker(Registro[] vetor, int inicio, int fim) {
        Registro pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j].getTicker().compareTo(pivo.getTicker()) < 0) {
                i++;
                troca(vetor, i, j);
            }
        }
        troca(vetor, i + 1, fim);
        return i + 1;
    }

    // QuickSort para Volume
    private void quickSortVolume(Registro[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = partitionVolume(vetor, inicio, fim);
            quickSortVolume(vetor, inicio, pivo - 1);
            quickSortVolume(vetor, pivo + 1, fim);
        }
    }

    private int partitionVolume(Registro[] vetor, int inicio, int fim) {
        Registro pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j].getVolume() < pivo.getVolume()) {
                i++;
                troca(vetor, i, j);
            }
        }
        troca(vetor, i + 1, fim);
        return i + 1;
    }

    // QuickSort para Variação
    private void quickSortVariacoes(Registro[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = partitionVariacoes(vetor, inicio, fim);
            quickSortVariacoes(vetor, inicio, pivo - 1);
            quickSortVariacoes(vetor, pivo + 1, fim);
        }
    }

    private int partitionVariacoes(Registro[] vetor, int inicio, int fim) {
        Registro pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if ((vetor[j].getHigh() - vetor[j].getLow()) > (pivo.getHigh() - pivo.getLow())) {
                i++;
                troca(vetor, i, j);
            }
        }
        troca(vetor, i + 1, fim);
        return i + 1;
    }

    // Função auxiliar para trocar elementos no vetor
    private void troca(Registro[] vetor, int i, int j) {
        Registro temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}
