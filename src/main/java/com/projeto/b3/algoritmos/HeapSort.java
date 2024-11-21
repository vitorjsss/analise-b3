package com.projeto.b3.algoritmos;

import com.projeto.b3.utils.*;
import com.projeto.b3.modelos.*;
import java.io.File;

public class HeapSort extends Funcoes {

    public void gerarHeapSort(Registro[] baseDeDados) {

        Registro[] vetor = baseDeDados.clone();

        // Cria a pasta "resultados" caso não exista
        File dir = new File("resultados");
        if (!dir.exists()) {
            dir.mkdirs(); // Cria a pasta "resultados" se não existir
        }

        System.out.println();
        System.out.println("________________________HEAP SORT________________________");
        System.out.println();
        System.out.println("ORDEM ALFABETICA:");
        System.out.println("Medio Caso:");
        criarArquivo(HeapSortTicker(vetor), "b3stocks_ticker_heapSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_heapSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("Melhor Caso:");
        criarArquivo(HeapSortTicker(vetor), "b3stocks_ticker_heapSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_heapSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("Pior Caso:");
        criarArquivo(HeapSortTicker(inverterVetor(vetor)), "b3stocks_ticker_heapSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_heapSort_piorCaso.csv\" criado com sucesso.");

        System.out.println();
        System.out.println("VOLUME:");
        System.out.println();
        System.out.println("Medio Caso:");
        criarArquivo(HeapSortVolume(vetor), "b3stocks_volume_heapSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_heapSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("Melhor Caso:");
        criarArquivo(HeapSortVolume(vetor), "b3stocks_volume_heapSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_heapSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("Pior Caso:");
        criarArquivo(HeapSortVolume(inverterVetor(vetor)), "b3stocks_volume_heapSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_heapSort_piorCaso.csv\" criado com sucesso.");

        System.out.println();
        System.out.println("VARIACOES DIARIAS:");
        System.out.println();
        System.out.println("Medio Caso:");
        criarArquivo(HeapSortVariacoes(vetor), "b3stocks_fluctuations_heapSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_heapSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("Melhor Caso:");
        criarArquivo(HeapSortVariacoes(vetor), "b3stocks_fluctuations_heapSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_heapSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("Pior Caso:");
        criarArquivo(HeapSortVariacoes(inverterVetor(vetor)), "b3stocks_fluctuations_heapSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_heapSort_piorCaso.csv\" criado com sucesso.");
    }

    public Registro[] HeapSortTicker(Registro[] vetor) {
        int i;
        long inicio = System.currentTimeMillis();

        buildMaxHeapTicker(vetor);

        for (i = vetor.length - 1; i > 0; i--) {
            troca(vetor, 0, i);
            maxHeapifyTicker(vetor, 0, i);
        }
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public void buildMaxHeapTicker(Registro vetor[]) {
        for (int i = (vetor.length / 2) - 1; i >= 0; i--) {
            maxHeapifyTicker(vetor, i, vetor.length);
        }
    }

    public void maxHeapifyTicker(Registro[] vetor, int i, int fim) {

        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        int maior;

        if (esq < fim && (vetor[esq].getTicker().compareTo(vetor[i].getTicker()) >= 0)) {
            maior = esq;
        } else {
            maior = i;
        }

        if (dir < fim && (vetor[dir].getTicker().compareTo(vetor[maior].getTicker()) >= 0)) {
            maior = dir;
        }

        if (maior != i) {
            troca(vetor, maior, i);
            maxHeapifyTicker(vetor, maior, fim);
        }
    }

    public Registro[] HeapSortVolume(Registro[] vetor) {
        int i;
        long inicio = System.currentTimeMillis();

        buildMaxHeapVolume(vetor);

        for (i = vetor.length - 1; i > 0; i--) {
            troca(vetor, 0, i);
            maxHeapifyVolume(vetor, 0, i);
        }
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public void buildMaxHeapVolume(Registro vetor[]) {
        for (int i = (vetor.length / 2) - 1; i >= 0; i--) {
            maxHeapifyVolume(vetor, i, vetor.length);
        }
    }

    public void maxHeapifyVolume(Registro[] vetor, int i, int fim) {

        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        int maior;

        if (esq < fim && ((vetor[esq].getVolume()) >= (vetor[i].getVolume()))) {
            maior = esq;
        } else {
            maior = i;
        }

        if (dir < fim && (vetor[dir].getVolume()) >= (vetor[maior].getVolume())) {
            maior = dir;
        }

        if (maior != i) {
            troca(vetor, maior, i);
            maxHeapifyVolume(vetor, maior, fim);
        }
    }

    public Registro[] HeapSortVariacoes(Registro[] vetor) {
        int i;
        long inicio = System.currentTimeMillis();

        buildMaxHeapVariacoes(vetor);

        for (i = vetor.length - 1; i > 0; i--) {
            troca(vetor, 0, i);
            maxHeapifyVariacoes(vetor, 0, i);
        }
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public void buildMaxHeapVariacoes(Registro vetor[]) {
        for (int i = (vetor.length / 2) - 1; i >= 0; i--) {
            maxHeapifyVariacoes(vetor, i, vetor.length);
        }
    }

    public void maxHeapifyVariacoes(Registro[] vetor, int i, int fim) {

        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        int maior;

        if (esq < fim && ((vetor[esq].getHigh() - vetor[esq].getLow()) <= (vetor[i].getHigh() - vetor[i].getLow()))) {
            maior = esq;
        } else {
            maior = i;
        }

        if (dir < fim
                && (vetor[dir].getHigh() - vetor[dir].getLow()) <= (vetor[maior].getHigh() - vetor[maior].getLow())) {
            maior = dir;
        }

        if (maior != i) {
            troca(vetor, maior, i);
            maxHeapifyVariacoes(vetor, maior, fim);
        }
    }
}
