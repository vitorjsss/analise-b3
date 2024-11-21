package com.projeto.b3.utils;

import com.projeto.b3.modelos.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Funcoes {

    public static String[] separarDados(String linha) {
        String valores[] = linha.split(",");
        return valores;
    }

    public static Registro[] construirVetorRegistro(String caminho) {
        Date dataLinha = null;
        String tickerLinha;
        float openLinha;
        float closeLinha;
        float highLinha;
        float lowLinha;
        float volumeLinha;
        String registroLinha;

        String linha = null;
        int i = 0;

        try {
            // Conta o número de linhas no arquivo, ignorando a primeira (cabeçalho)
            long numLinhas = Files.lines(Paths.get(caminho)).skip(1).count(); // Pula a primeira linha (cabeçalho)

            Registro[] vetorRegistro = new Registro[(int) numLinhas];

            try (BufferedReader arq2 = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)))) {
                arq2.readLine(); // Desconsidera a primeira linha novamente (cabeçalho)

                while ((linha = arq2.readLine()) != null) {
                    String[] arrayDados = separarDados(linha);

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        dataLinha = formatter.parse(arrayDados[0]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    tickerLinha = arrayDados[1];
                    openLinha = Float.parseFloat(arrayDados[2]);
                    closeLinha = Float.parseFloat(arrayDados[3]);
                    highLinha = Float.parseFloat(arrayDados[4]);
                    lowLinha = Float.parseFloat(arrayDados[5]);
                    volumeLinha = Float.parseFloat(arrayDados[6]);
                    registroLinha = linha;

                    vetorRegistro[i] = new Registro(dataLinha, tickerLinha, openLinha, closeLinha, highLinha, lowLinha,
                            volumeLinha, registroLinha);
                    i++;
                }
            }
            return vetorRegistro;
        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado!");
            return new Registro[0];
        }
    }

    public static Registro[] construirVetorMenor(String caminho) {
        Date dataLinha = null;
        String tickerLinha;
        float openLinha;
        float closeLinha;
        float highLinha;
        float lowLinha;
        float volumeLinha;
        String registroLinha;

        String linha = null;
        int i = 0;

        try (BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)))) {
            arq.readLine(); // Desconsidera a primeira linha

            // Conta o número de linhas restantes no arquivo, ignorando a primeira
            long numLinhas = arq.lines().count();
            arq.close(); // Fecha o arquivo após contar as linhas

            // Redimensiona o vetor com base no número de registros
            Registro[] vetorRegistro = new Registro[(int) numLinhas];

            try (BufferedReader arq2 = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)))) {
                arq2.readLine(); // Desconsidera novamente a primeira linha

                while ((linha = arq2.readLine()) != null) {
                    String[] arrayDados = separarDados(linha);

                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        dataLinha = formatter.parse(arrayDados[0]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    tickerLinha = arrayDados[1];
                    openLinha = Float.parseFloat(arrayDados[2]);
                    closeLinha = Float.parseFloat(arrayDados[3]);
                    highLinha = Float.parseFloat(arrayDados[4]);
                    lowLinha = Float.parseFloat(arrayDados[5]);
                    volumeLinha = Float.parseFloat(arrayDados[6]);
                    registroLinha = linha;

                    vetorRegistro[i] = new Registro(dataLinha, tickerLinha, openLinha, closeLinha, highLinha, lowLinha,
                            volumeLinha, registroLinha);
                    i++;
                }
            }
            return vetorRegistro;
        } catch (IOException ex) {
            System.out.println("Arquivo não encontrado!");
            return new Registro[0];
        }
    }

    public Registro[] inverterVetor(Registro[] vetor) {
        int j = 0;
        Registro[] A = new Registro[vetor.length];

        for (int i = vetor.length - 1; i >= 0; i--) {
            A[j] = vetor[i];
            j++;
        }
        return A;
    }

    public static void criarArquivo(Registro[] banco, String nomeArquivo) {
        int i = 0;
        try (FileWriter novoArquivo = new FileWriter(nomeArquivo);
                PrintWriter escrever = new PrintWriter(novoArquivo)) {
            escrever.println("datetime,ticker,open,close,high,low,volume");

            while (i < banco.length) {
                escrever.println(banco[i].toString());
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void troca(Registro vetor[], int i, int j) {
        Registro aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}
