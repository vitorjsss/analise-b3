package com.projeto.b3.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformarData extends Funcoes {

    public void transform(String caminho) {

        String column;
        String row = null;

        try {
            FileWriter newFile = new FileWriter("resultados/b3stocks_T1.csv");
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(caminho))); // Responsável
                                                                                                          // pela
                                                                                                          // leitura do
                                                                                                          // arquivo a
                                                                                                          // ser
                                                                                                          // transformado//

            PrintWriter writer = new PrintWriter(newFile);
            column = arq.readLine();
            writer.println(column);

            while ((row = arq.readLine()) != null) {

                String arrayDados[] = separarDados(row);

                writer.println(formatDate(arrayDados[0]) + "," + arrayDados[1] + "," + arrayDados[2] + ","
                        + arrayDados[3] + "," + arrayDados[4] + "," + arrayDados[5] + "," + arrayDados[6]);

            }
            System.out.println("Arquivo \"b3stocks_T1.csv\" criado com sucesso.");

            writer.close();
            arq.close();
            newFile.close();
        } // Fim do bloco try
        catch (IOException ex) {
            System.out.println("O arquivo nao foi encontrado!");
        } // Fim do catch
    }// Fim do método transform()

    private String formatDate(String data) { // Transforma de YYYY-MM-DD para o formato DD/MM/AAAA //

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Define o formato que irá aceitar//
        String dateToStr = null;

        try {
            Date novaData = formatter.parse(data); // Transforma de String para Date//
            formatter = new SimpleDateFormat("dd/MM/yyyy"); // Define o novo Formato desejado//
            dateToStr = formatter.format(novaData); // Transforma de Date para String//
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateToStr;
    }// Fim do método formatDate()

}
