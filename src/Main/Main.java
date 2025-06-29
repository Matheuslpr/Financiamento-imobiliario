package Main;

import Modelo.Financiamento;
import Util.InterfaceUsuario;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> listaDeFinanciamentos = new ArrayList<>();
        double totalValorImoveis = 0;
        double totalFinanciamentos = 0;

        // Entrada de dados
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n--- Cadastro do Financiamento " + i + " ---");
            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazo = interfaceUsuario.pedirPrazoFinanciamento();
            double taxa = interfaceUsuario.pedirTaxaJurosAnual();

            Financiamento f = new Financiamento(valorImovel, prazo, taxa);
            listaDeFinanciamentos.add(f);

            }

        // Exibição das listas
        System.out.println("\n------ Lista de Financiamentos ------");
        int contador = 1;
        for (Financiamento f : listaDeFinanciamentos) {
            System.out.println("\n--- Financiamento " + contador + " ---");
            f.dadosFinanciamento();
            contador++;
        }

        for (Financiamento f : listaDeFinanciamentos) {
            totalValorImoveis += f.getValorImovel();
            totalFinanciamentos += f.calcularTotalPagamento();
        }

        System.out.println("\n---------------------------------------");
        System.out.printf("Valor total dos imóveis: R$ %.2f%n ", totalValorImoveis);
        System.out.printf("Valor total a pagar em todos os financiamentos: R$ %.2f%n ", totalFinanciamentos);
    }
}