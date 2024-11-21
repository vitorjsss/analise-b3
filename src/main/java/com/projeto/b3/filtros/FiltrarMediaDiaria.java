package com.projeto.b3.filtros;

import com.projeto.b3.utils.*;
import com.projeto.b3.modelos.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FiltrarMediaDiaria extends Funcoes {

    public void filtrarMediaDiaria(String caminho) {
        String colunas;
        int i = 0;

        try {
            FileWriter newFile = new FileWriter("b3stocks_T1_Filtrado.csv"); // Mantendo o caminho original de onde
                                                                             // salvar o arquivo
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)));
            PrintWriter writer = new PrintWriter(newFile);

            colunas = arq.readLine();
            writer.println(colunas); // Escreve a primeira linha

            Registro[] vetorRegistro = construirVetorRegistro(caminho);
            vetorRegistro = OrdenarPorData(vetorRegistro);

            float mediaDiaria = 0;
            int indexPrimeiroDia = 0;
            int j = 0;

            while (i < vetorRegistro.length - 1) {
                if (vetorRegistro[i] != null && vetorRegistro[i + 1] != null &&
                        vetorRegistro[i].getData().equals(vetorRegistro[i + 1].getData())) {
                    mediaDiaria += vetorRegistro[i].getVolume();
                    j++;
                } else {
                    if (vetorRegistro[i] != null) {
                        mediaDiaria += vetorRegistro[i].getVolume();
                        j++;
                        if (j > 0) {
                            mediaDiaria = mediaDiaria / j;
                        }

                        for (int k = indexPrimeiroDia; k <= i; k++) {
                            if (vetorRegistro[k] != null && vetorRegistro[k].getVolume() > mediaDiaria) {
                                writer.println(vetorRegistro[k].toString());
                            }
                        }
                        indexPrimeiroDia = i + 1;
                        j = 0;
                        mediaDiaria = 0;
                    }
                }
                i++;
            }

            System.out.println("Arquivo \"b3stocks_T1_Filtrado.csv\" filtrado com sucesso.");

            writer.close();
            arq.close();
            newFile.close();
        } catch (IOException ex) {
            System.out.println("O arquivo não foi encontrado!");
        }
    }

    public Registro[] OrdenarPorData(Registro[] vetor) {
        int j;
        for (int k = 1; k < vetor.length; k++) {
            if (vetor[k] == null)
                continue; // Ignora elementos nulos
            Registro key = vetor[k];
            j = k - 1;

            while (j >= 0 && vetor[j] != null && key.getData().compareTo(vetor[j].getData()) < 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = key;
        }
        return vetor;
    }
}
