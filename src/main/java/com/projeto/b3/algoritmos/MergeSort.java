package com.projeto.b3.algoritmos;

import com.projeto.b3.utils.*;
import com.projeto.b3.modelos.*;

public class MergeSort extends Funcoes {

    public void gerarMergeSort(Registro[] baseDeDados) {

        Registro[] vetor = baseDeDados.clone();

        System.out.println();
        System.out.println("________________________MERGE SORT________________________");
        System.out.println();
        System.out.println("ORDEM ALFABETICA:");
        System.out.println();
        System.out.println("MEDIO CASO:");
        criarArquivo(MergeSortTickerTempo(vetor), "b3stocks_ticker_mergeSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_mergeSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("MELHOR CASO:");
        criarArquivo(MergeSortTickerTempo(vetor), "b3stocks_ticker_mergeSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_mergeSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("PIOR CASO:");
        criarArquivo(MergeSortTickerTempo(inverterVetor(vetor)), "b3stocks_ticker_mergeSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_ticker_mergeSort_piorCaso.csv\" criado com sucesso.");

        System.out.println();
        System.out.println("VOLUME:");
        System.out.println();
        System.out.println("MEDIO CASO:");
        criarArquivo(MergeSortVolumeTempo(vetor), "b3stocks_volume_mergeSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_mergeSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("MELHOR CASO:");
        criarArquivo(MergeSortVolumeTempo(vetor), "b3stocks_volume_mergeSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_mergeSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("PIOR CASO:");
        criarArquivo(MergeSortVolumeTempo(inverterVetor(vetor)), "b3stocks_volume_mergeSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_volume_mergeSort_piorCaso.csv\" criado com sucesso.");

        System.out.println();
        System.out.println("VARIACOES DIARIAS:");
        System.out.println();
        System.out.println("MEDIO CASO:");
        criarArquivo(MergeSortVariacoesTempo(vetor), "b3stocks_fluctuations_mergeSort_medioCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_mergeSort_medioCaso.csv\" criado com sucesso.");

        System.out.println("MELHOR CASO:");
        criarArquivo(MergeSortVariacoesTempo(vetor), "b3stocks_fluctuations_mergeSort_melhorCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_mergeSort_melhorCaso.csv\" criado com sucesso.");

        System.out.println("PIOR CASO:");
        criarArquivo(MergeSortVariacoesTempo(inverterVetor(vetor)), "b3stocks_fluctuations_mergeSort_piorCaso.csv");
        System.out.println("Arquivo \"b3stocks_fluctuations_mergeSort_piorCaso.csv\" criado com sucesso.");
    }

    public Registro[] MergeSortTickerTempo(Registro[] vetor) {
        long inicio = System.currentTimeMillis();

        MergeSortTicker(vetor, 0, vetor.length - 1);
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public Registro[] MergeSortTicker(Registro[] vetor, int inicio, int fim) {

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            MergeSortTicker(vetor, inicio, meio);
            MergeSortTicker(vetor, meio + 1, fim);
            MergeTicker(vetor, inicio, meio, fim);
        }
        return vetor;
    }

    public void MergeTicker(Registro vetor[], int inicio, int meio, int fim) {
        int tamEsq = meio - inicio + 1;
        int tamDir = fim - meio;

        Registro[] esq = new Registro[tamEsq];
        Registro[] dir = new Registro[tamDir];

        for (int i = 0; i < tamEsq; i++) {
            esq[i] = vetor[inicio + i];
        }
        for (int i = 0; i < tamDir; i++) {
            dir[i] = vetor[meio + i + 1];
        }

        int indexEsq = 0;
        int indexDir = 0;
        int indexVetor = inicio;

        while (indexEsq < tamEsq && indexDir < tamDir) {
            if ((esq[indexEsq].getTicker().compareTo(dir[indexDir].getTicker()) < 0)) {
                vetor[indexVetor] = esq[indexEsq];
                indexVetor++;
                indexEsq++;
            } else {
                vetor[indexVetor] = dir[indexDir];
                indexVetor++;
                indexDir++;
            }
        }

        while (indexEsq < tamEsq) {
            vetor[indexVetor] = esq[indexEsq];
            indexVetor++;
            indexEsq++;
        }
        while (indexDir < tamDir) {
            vetor[indexVetor] = dir[indexDir];
            indexVetor++;
            indexDir++;
        }
    }

    public Registro[] MergeSortVariacoesTempo(Registro[] vetor) {
        long inicio = System.currentTimeMillis();

        MergeSortVariacoes(vetor, 0, vetor.length - 1);
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public Registro[] MergeSortVariacoes(Registro[] vetor, int inicio, int fim) {

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            MergeSortVariacoes(vetor, inicio, meio);
            MergeSortVariacoes(vetor, meio + 1, fim);
            MergeVariacoes(vetor, inicio, meio, fim);
        }
        return vetor;
    }

    public void MergeVariacoes(Registro vetor[], int inicio, int meio, int fim) {
        int tamEsq = meio - inicio + 1;
        int tamDir = fim - meio;

        Registro[] esq = new Registro[tamEsq];
        Registro[] dir = new Registro[tamDir];

        for (int i = 0; i < tamEsq; i++) {
            esq[i] = vetor[inicio + i];
        }
        for (int i = 0; i < tamDir; i++) {
            dir[i] = vetor[meio + i + 1];
        }

        int indexEsq = 0;
        int indexDir = 0;
        int indexVetor = inicio;

        while (indexEsq < tamEsq && indexDir < tamDir) {
            if ((esq[indexEsq].getHigh() - esq[indexEsq].getLow()) > (dir[indexDir].getHigh()
                    - dir[indexDir].getLow())) {
                vetor[indexVetor] = esq[indexEsq];
                indexVetor++;
                indexEsq++;
            } else {
                vetor[indexVetor] = dir[indexDir];
                indexVetor++;
                indexDir++;
            }
        }

        while (indexEsq < tamEsq) {
            vetor[indexVetor] = esq[indexEsq];
            indexVetor++;
            indexEsq++;
        }
        while (indexDir < tamDir) {
            vetor[indexVetor] = dir[indexDir];
            indexVetor++;
            indexDir++;
        }
    }

    public Registro[] MergeSortVolumeTempo(Registro[] vetor) {
        long inicio = System.currentTimeMillis();

        MergeSortVolume(vetor, 0, vetor.length - 1);
        System.out.println("Ordenação realizada em " + (System.currentTimeMillis() - inicio) + " milissegundos");

        return vetor;
    }

    public Registro[] MergeSortVolume(Registro[] vetor, int inicio, int fim) {

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            MergeSortVolume(vetor, inicio, meio);
            MergeSortVolume(vetor, meio + 1, fim);
            MergeVolume(vetor, inicio, meio, fim);
        }
        return vetor;
    }

    public void MergeVolume(Registro vetor[], int inicio, int meio, int fim) {
        int tamEsq = meio - inicio + 1;
        int tamDir = fim - meio;

        Registro[] esq = new Registro[tamEsq];
        Registro[] dir = new Registro[tamDir];

        for (int i = 0; i < tamEsq; i++) {
            esq[i] = vetor[inicio + i];
        }
        for (int i = 0; i < tamDir; i++) {
            dir[i] = vetor[meio + i + 1];
        }

        int indexEsq = 0;
        int indexDir = 0;
        int indexVetor = inicio;

        while (indexEsq < tamEsq && indexDir < tamDir) {
            if ((esq[indexEsq].getVolume()) < (dir[indexDir].getVolume())) {
                vetor[indexVetor] = esq[indexEsq];
                indexVetor++;
                indexEsq++;
            } else {
                vetor[indexVetor] = dir[indexDir];
                indexVetor++;
                indexDir++;
            }
        }

        while (indexEsq < tamEsq) {
            vetor[indexVetor] = esq[indexEsq];
            indexVetor++;
            indexEsq++;
        }
        while (indexDir < tamDir) {
            vetor[indexVetor] = dir[indexDir];
            indexVetor++;
            indexDir++;
        }
    }

}
