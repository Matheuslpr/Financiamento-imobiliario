package Main;

import Modelo.Apartamento;
import Modelo.Casa;
import Modelo.Financiamento;
import Modelo.Terreno;
import Util.InterfaceUsuario;

import java.util.ArrayList;

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

        // Exibição dos resultados
        double totalValorImoveis = 0;
        double totalFinanciamentos = 0;
        int contador = 1;

        System.out.println("\n\n------ Lista de Todos os Financiamentos ------");
        for (Financiamento f : listaDeFinanciamentos) {
            f.dadosFinanciamento();
            totalValorImoveis += f.getValorImovel();
            totalFinanciamentos += f.calcularTotalPagamento();
        }

        System.out.println("---------------------------------------");
        System.out.printf("Valor total de todos os imóveis: R$ %.2f%n", totalValorImoveis);
        System.out.printf("Valor total de todos os financiamentos a pagar: R$ %.2f%n", totalFinanciamentos);
        System.out.println("---------------------------------------");


        interfaceUsuario.fecharScanner();
    }
}
