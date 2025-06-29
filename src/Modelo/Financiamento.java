package Modelo;

public class Financiamento {

    // atributos
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // construtores
    public Financiamento(){
    }

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    //getters e setters
    public double getValorImovel() {
        return valorImovel;
    }
    public Financiamento setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
        return this;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }
    public Financiamento setPrazoFinanciamento(int prazoFinanciamento) {
        this.prazoFinanciamento = prazoFinanciamento;
        return this;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }
    public Financiamento setTaxaJurosAnual(double taxaJurosAnual) {
        this.taxaJurosAnual = taxaJurosAnual;
        return this;
    }

    //metodos
    public double calcularPagamentoMensal() {
        return (this.valorImovel / (this.prazoFinanciamento * 12)) * (1 +(this.taxaJurosAnual / 12));
    }

    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }

    public void dadosFinanciamento(){
        System.out.println("Valor do Imovel: " + valorImovel);
        System.out.println("Prazo do financiamento: " + prazoFinanciamento);
        System.out.println("Taxa de juros anual: " + taxaJurosAnual);
        System.out.println("Valor do pagamento mensal: " + calcularPagamentoMensal());
        System.out.println("Valor total a pagar: " + calcularTotalPagamento());
    }

    //toString
    @Override
    public String toString() {
        return "Financiamento{" +
                "valorImovel=" + valorImovel +
                ", prazoFinanciamento=" + prazoFinanciamento +
                ", taxaJurosAnual=" + taxaJurosAnual +
                '}';
    }
}