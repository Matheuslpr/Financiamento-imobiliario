package Modelo;

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
    public double calcularPagamentoMensal() {
        double taxaMensal = this.taxaJurosAnual / 12;

        int meses = this.prazoFinanciamento * 12;
        double fator = Math.pow(1 + taxaMensal, meses);
        return this.valorImovel * (taxaMensal * fator) / (fator - 1);
    }

    public void pedirDadosApartamento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Por favor, insira a quantidade de vagas de garagem: ");
        this.setVagasGaragem(scanner.nextInt());

        System.out.print("Por favor, insira o número de andares: ");
        this.setAndares(scanner.nextInt());
    }
    @Override
    public void dadosFinanciamento(){
        System.out.println("---------------------------------------");
        System.out.println("Valor do Apartamento: " + valorImovel);
        System.out.println("Numero de vagas da garagem: " + vagasGaragem );
        System.out.println("Numero de andares: " + andares );
        System.out.println("Prazo do financiamento: " + prazoFinanciamento);
        System.out.println("Taxa de juros anual: " + taxaJurosAnual);
        System.out.println("Valor do pagamento mensal: " + calcularPagamentoMensal());
        System.out.println("Valor total a pagar: " + calcularTotalPagamento());
        System.out.println("---------------------------------------");
    }


}

