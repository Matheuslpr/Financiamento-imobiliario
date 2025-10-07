package Modelo;

import Util.AumentoMaiorDoQueJurosException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Casa extends Financiamento implements Serializable {
    transient Scanner scanner = new Scanner(System.in);

    //Atributos
    private double areaConstruida;
    private double areaTerreno;

    //Construtores
    public Casa() {
    }
    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double areaTerreno) {
      super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }

    //Getters and Setters
    public double getAreaConstruida() {
        return areaConstruida;
    }

    public Casa setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
        return this;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    public Casa setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
        return this;
    }

    //Metodos
    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double pagamentoMensal = super.calcularPagamentoMensal();
        double jurosMensal = pagamentoMensal * (this.taxaJurosAnual/12/100);

        if (80 > (jurosMensal / 2)) {

            double novoAcrescimo = jurosMensal / 2;
            System.out.println("Aviso: O acréscimo de juros foi ajustado para " + novoAcrescimo +
                    " (50% do juro mensal calculado), pois o valor original excedia o limite permitido.");
            return pagamentoMensal + novoAcrescimo;
        }
        return pagamentoMensal + 80;
    }

    public void pedirDadosCasa() {
        Scanner scanner = new Scanner(System.in);
        boolean entradaValida = false;

        do {
            try {
                System.out.print("Por favor, insira a área construída (em m²): ");
                double areaConstruidaInput = scanner.nextDouble();
                if (areaConstruidaInput > 0) {
                    this.setAreaConstruida(areaConstruidaInput);
                    entradaValida = true;
                } else {
                    System.out.println("Área construída inválida. Por favor, Digite um valor maior que zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número para a área construída.");
                scanner.next();
            }
        } while (!entradaValida);

        entradaValida = false;
        do {
            try {
                System.out.print("Por favor, insira a área total do terreno (em m²): ");
                double areaTerrenoInput = scanner.nextDouble();
                if (areaTerrenoInput >= this.getAreaConstruida()) {
                    this.setAreaTerreno(areaTerrenoInput);
                    entradaValida = true;
                } else {
                    System.out.println("A área do terreno não pode ser menor que a área construída. Área construída informada: " + this.getAreaConstruida() + " m².");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número para a área do terreno.");
                scanner.next();
            }
        } while (!entradaValida);
    }
    @Override
    public void dadosFinanciamento() throws AumentoMaiorDoQueJurosException {
        System.out.println("---------------------------------------");
        System.out.println("Valor do Imovel: " + valorImovel);
        System.out.println("Área do imovel construida: " + areaConstruida + " m².");
        System.out.println("Tamanho do Terreno: " + areaTerreno + " m².");
        System.out.println("Prazo do financiamento: " + prazoFinanciamento);
        System.out.println("Taxa de juros anual: " + taxaJurosAnual);
        try {
            System.out.println("Valor do pagamento mensal: " + calcularPagamentoMensal());
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Valor total a pagar: " + calcularTotalPagamento());
        System.out.println("---------------------------------------");
    }

    @Override
    public String toString() {
        return "Casa{" +
                "valorImovel=" + valorImovel +
                ", prazoFinanciamento=" + prazoFinanciamento +
                ", taxaJurosAnual=" + taxaJurosAnual +
                ", areaConstruida=" + areaConstruida +
                ", areaTerreno=" + areaTerreno +
                "}";
    }

}
