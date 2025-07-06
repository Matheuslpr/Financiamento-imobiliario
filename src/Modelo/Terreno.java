package Modelo;

import Util.AumentoMaiorDoQueJurosException;

import java.util.Scanner;

public class Terreno extends Financiamento{
    Scanner scanner = new Scanner(System.in);

    //Atributos
    private String tipoDeZona;

    //Construtores
    public Terreno() {
    }
    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoDeZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoDeZona = tipoDeZona;
    }
    //Getters e setters

    public String getTipoDeZona() {
        return tipoDeZona;
    }

    public Terreno setTipoDeZona(String tipoDeZona) {
        this.tipoDeZona = tipoDeZona;
        return this;
    }


    //Metodos
    @Override
    public double calcularPagamentoMensal() throws AumentoMaiorDoQueJurosException {
        return super.calcularPagamentoMensal() * 1.02;
    }

    @Override
    public double calcularTotalPagamento() throws AumentoMaiorDoQueJurosException {

        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }

    public void definirTipoDeZona() {
        Scanner scanner = new Scanner(System.in);
        String resposta;
        while (true) {
            System.out.print("O terreno está em zona (residencial / comercial)? ");
            resposta = scanner.nextLine().toLowerCase().trim();

            if (resposta.equals("residencial") || resposta.equals("comercial")) {
                this.setTipoDeZona(resposta);
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite 'residencial' ou 'comercial'.");
            }
        }

    }
    @Override
    public void dadosFinanciamento(){
        System.out.println("---------------------------------------");
        System.out.println("Valor do Terreno: " + valorImovel);
        System.out.println("Tipo da Zona do Terreno: "+ tipoDeZona);
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
