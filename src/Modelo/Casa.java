package Modelo;

import java.util.Scanner;

public class Casa extends Financiamento{
    Scanner scanner = new Scanner(System.in);

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
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() + 80;
    }

    public void pedirDadosCasa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Por favor, insira a área construída (em m²): ");
        double areaConstruidaInput = scanner.nextDouble();
        this.setAreaConstruida(areaConstruidaInput);

        while (true) {
            System.out.print("Por favor, insira a área total do terreno (em m²): ");
            double areaTerrenoInput = scanner.nextDouble();
            if (areaTerrenoInput >= this.getAreaConstruida()) {
                this.setAreaTerreno(areaTerrenoInput);
                break;
            } else {
                System.out.println("Erro: A área do terreno não pode ser menor que a área construída.");
                System.out.println("Área construída informada: " + this.getAreaConstruida() + " m².");
            }
        }
    }
    @Override
    public void dadosFinanciamento(){
        System.out.println("---------------------------------------");
        System.out.println("Valor do Imovel: " + valorImovel);
        System.out.println("Área do imovel construida: " + areaConstruida + " m².");
        System.out.println("Tamanho do Terreno: " + areaTerreno + " m².");
        System.out.println("Prazo do financiamento: " + prazoFinanciamento);
        System.out.println("Taxa de juros anual: " + taxaJurosAnual);
        System.out.println("Valor do pagamento mensal: " + calcularPagamentoMensal());
        System.out.println("Valor total a pagar: " + calcularTotalPagamento());
        System.out.println("---------------------------------------");
    }

}
