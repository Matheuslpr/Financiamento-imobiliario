package Modelo;

import Util.AumentoMaiorDoQueJurosException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Apartamento extends Financiamento{
    Scanner scanner = new Scanner(System.in);

    //Atributos
    private int vagasGaragem;
    private int andares;

    //Construtores
    public Apartamento() {
    }
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int andares) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.andares = andares;
    }

    //Getters and setters
    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public Apartamento setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
        return this;
    }
    public int getAndares() {
        return andares;
    }

    public Apartamento setAndares(int andares) {
        this.andares = andares;
        return this;
    }

    //Metodos

    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        double taxaMensal = this.taxaJurosAnual / 12;

        int meses = this.prazoFinanciamento * 12;
        double fator = Math.pow(1 + taxaMensal, meses);
        return this.valorImovel * (taxaMensal * fator) / (fator - 1);
    }

    public void pedirDadosApartamento() {
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Por favor, insira a quantidade de vagas de garagem: ");
                int vagas = scanner.nextInt();
                if (vagas >= 0) {
                    this.setVagasGaragem(vagas);
                    entradaValida = true;
                } else {
                    System.out.println("Número de vagas inválido. Deve ser um valor que não seja negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro para as vagas de garagem.");
                scanner.next();
            }
        } while (!entradaValida);

        entradaValida = false;
        do {
            try {
                System.out.print("Por favor, insira o número de andares: ");
                int andares = scanner.nextInt();
                if (andares >= 0) {
                    this.setAndares(andares);
                    entradaValida = true;
                } else {
                    System.out.println("Número de andares inválido. Deve ser um valor que não seja negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro para o número de andares.");
                scanner.next();
            }
        } while (!entradaValida);
    }

    @Override
    public void dadosFinanciamento(){
        System.out.println("---------------------------------------");
        System.out.println("Valor do Apartamento: " + valorImovel);
        System.out.println("Numero de vagas da garagem: " + vagasGaragem );
        System.out.println("Numero de andares: " + andares );
        System.out.println("Prazo do financiamento: " + prazoFinanciamento);
        System.out.println("Taxa de juros anual: " + taxaJurosAnual);
        try {
            System.out.println("Valor do pagamento mensal: " + calcularPagamentoMensal());
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Valor total a pagar: " + calcularTotalPagamento());
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------");
    }


}

