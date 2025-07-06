package Main;

import Modelo.Apartamento;
import Modelo.Casa;
import Modelo.Financiamento;
import Modelo.Terreno;
import Util.AumentoMaiorDoQueJurosException;
import Util.InterfaceUsuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> listaDeFinanciamentos = new ArrayList<>();

        System.out.print("Quantos financiamentos você deseja cadastrar? ");
        int numeroDeFinanciamentos = new java.util.Scanner(System.in).nextInt();

        // Entrada de dados para cada financiamento
        for (int i = 1; i <= numeroDeFinanciamentos; i++) {
            System.out.println("\n--- Cadastro do Financiamento " + i + " ---");

            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazo = interfaceUsuario.pedirPrazoFinanciamento();
            double taxa = interfaceUsuario.pedirTaxaJurosAnual();

            // Pergunta o tipo de imóvel
            String tipoImovel = interfaceUsuario.pedirTipoFinanciamento();
            Financiamento financiamento = null;

            switch (tipoImovel) {
                case "casa":

                    Casa casa = new Casa(valorImovel, prazo, taxa, 0, 0);
                    casa.pedirDadosCasa();
                    financiamento = casa;
                    break;

                case "apartamento":

                    Apartamento apto = new Apartamento(valorImovel, prazo, taxa, 0, 0);

                    apto.pedirDadosApartamento();
                    financiamento = apto;
                    break;
                case "terreno":

                    Terreno terreno = new Terreno(valorImovel, prazo, taxa, "");
                    terreno.definirTipoDeZona();
                    financiamento = terreno;
                    break;
            }

            if (financiamento != null) {
                listaDeFinanciamentos.add(financiamento);
            }
        }

        // resultados
        double totalValorImoveis = 0;
        double totalFinanciamentos = 0;
        int contador = 1;

        System.out.println("\n\n------ Lista de Todos os Financiamentos ------");
        for (Financiamento f : listaDeFinanciamentos) {
            try {
                f.dadosFinanciamento();
                totalValorImoveis += f.getValorImovel();
                totalFinanciamentos += f.calcularTotalPagamento();
            } catch (AumentoMaiorDoQueJurosException e) {
                System.err.println("Erro ao processar financiamento: " + e.getMessage());
            }
        }

        System.out.println("---------------------------------------");
        System.out.printf("Valor total de todos os imóveis: R$ %.2f%n", totalValorImoveis);
        System.out.printf("Valor total de todos os financiamentos a pagar: R$ %.2f%n", totalFinanciamentos);
        System.out.println("---------------------------------------");

        //salvar e ler
        salvarFinanciamentosEmTexto(listaDeFinanciamentos, "financiamentos.txt");
        lerFinanciamentosDeTexto("financiamentos.txt");

        salvarFinanciamentosSerializados(listaDeFinanciamentos, "financiamentos.ser");
        lerFinanciamentosSerializados("financiamentos.ser");

        interfaceUsuario.fecharScanner();
    }

    public static void salvarFinanciamentosEmTexto(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Financiamento f : financiamentos) {
                StringBuilder sb = new StringBuilder();
                sb.append(f.getValorImovel()).append(",");
                sb.append(f.getPrazoFinanciamento()).append(",");
                sb.append(f.getTaxaJurosAnual()).append(",");

                if (f instanceof Casa) {
                    Casa casa = (Casa) f;
                    sb.append("Casa,").append(casa.getAreaConstruida()).append(",").append(casa.getAreaTerreno());
                } else if (f instanceof Apartamento) {
                    Apartamento apto = (Apartamento) f;
                    sb.append("Apartamento,").append(apto.getVagasGaragem()).append(",").append(apto.getAndares());
                } else if (f instanceof Terreno) {
                    Terreno terreno = (Terreno) f;
                    sb.append("Terreno,").append(terreno.getTipoDeZona());
                }
                writer.println(sb.toString());
            }
            System.out.println("Dados dos financiamentos salvos em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar financiamentos em texto: " + e.getMessage());
        }
    }

    public static void lerFinanciamentosDeTexto(String nomeArquivo) {
        System.out.println("\nLendo dados de financiamentos de " + nomeArquivo + ":");
        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler financiamentos de texto: " + e.getMessage());
        }
    }

    public static void salvarFinanciamentosSerializados(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(financiamentos);
            System.out.println("Dados dos financiamentos serializados e salvos em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao serializar financiamentos: " + e.getMessage());
        }
    }

    public static void lerFinanciamentosSerializados(String nomeArquivo) {
        System.out.println("\nLendo dados de financiamentos serializados de " + nomeArquivo + ":");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            ArrayList<Financiamento> financiamentosLidos = (ArrayList<Financiamento>) ois.readObject();
            System.out.println("Financiamentos lidos (serializados): ");
            for (Financiamento f : financiamentosLidos) {
                System.out.println(f.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao deserializar financiamentos: " + e.getMessage());
        }
    }
}
