package com.projeto.b3.filtros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.projeto.b3.modelos.Registro;
import com.projeto.b3.utils.Funcoes;

public class FiltrarRegistro extends Funcoes {

    public void filtrar(String caminho) {

        String colunas;
        int i = 0;

        try {
            File resultadosDir = new File("resultados");
            if (!resultadosDir.exists()) {
                resultadosDir.mkdir();
            }

            FileWriter newFile = new FileWriter("resultados/b3stocks_F1.csv");
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)));

            PrintWriter writer = new PrintWriter(newFile);
            colunas = arq.readLine();
            writer.println(colunas); // Escreve a primeira linha

            Registro[] vetorRegistro = construirVetorRegistro(caminho); // Criação do vetor de objetos do tipo Registro
            OrdenarPorData(vetorRegistro);

            float maiorVolume = vetorRegistro[0].getVolume();
            int indexMaiorVolume = 0;

            while ((i < vetorRegistro.length - 1)) {

                if (vetorRegistro[i].getData().equals(vetorRegistro[i + 1].getData())
                        && ((i + 1) != vetorRegistro.length - 1)) {
                    if (vetorRegistro[i + 1].getVolume() > maiorVolume) {
                        maiorVolume = vetorRegistro[i + 1].getVolume();
                        indexMaiorVolume = i + 1;
                    }
                } else {
                    writer.println(vetorRegistro[indexMaiorVolume].toString());
                    maiorVolume = vetorRegistro[i + 1].getVolume();
                    indexMaiorVolume = i + 1;
                }

                i++;
            } // Fim do while

            System.out.println("Arquivo \"b3stocks_F1.csv\" criado com sucesso.");

            writer.close();
            arq.close();
            newFile.close();
        } // Fim do bloco try
        catch (IOException ex) {
            System.out.println("Arquivo não encontrado!");
        } // Fim do catch

    }// Fim do método filtrar()

    public Registro[] OrdenarPorData(Registro[] vetor) {
        int j;

        for (int k = 1; k < vetor.length; k++) {
            if (vetor[k] == null)
                continue; // Ignora entradas nulas

            Registro key = vetor[k];
            j = k - 1;

            while (j >= 0 && vetor[j] != null && key.getData().compareTo(vetor[j].getData()) < 0) {
                vetor[j + 1] = vetor[j];
                j = j - 1;
            }
            vetor[j + 1] = key;
        }
        return vetor;
    }
}
