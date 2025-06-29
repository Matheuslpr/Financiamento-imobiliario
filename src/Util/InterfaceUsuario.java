package Util;


import java.util.Scanner;

public class InterfaceUsuario  {
    Scanner scanner = new Scanner(System.in);

   //metodo
    public double pedirValorImovel() {
        double valor;
       do {
           System.out.println("Digite o valor do imóvel: ");
           valor = scanner.nextDouble();

            if (valor > 0) {
                System.out.println("O valor do imóvel é: " + valor);
            } else {
                System.out.println("Valor invalido.... Tente novamente ");
            }
       } while (valor < 0 );
        return valor;
    }
    public int pedirPrazoFinanciamento() {
        int prazo;
        do {
            System.out.print("Digite o prazo do financiamento em anos: ");
             prazo = scanner.nextInt();

            if (prazo > 0) {
                System.out.println("O prazo do financiamento é: " + prazo + " anos.");
            } else{
                System.out.println("Prazo invalido.... Tente novamente");
        }} while (prazo < 0 );

        return prazo;
    }

    public double pedirTaxaJurosAnual() {
        double taxa;
        do {
            System.out.print("Digite a taxa de juros anual (0-20%): ");
            taxa = scanner.nextDouble();

            if (taxa >= 0 && taxa <= 20.0) {
                System.out.println("Taxa de juros válida: " + taxa + "%");
            } else if (taxa > 20.0) {
                System.out.println("Taxa de juros muito alta! Máximo permitido: 20%.");
            } else {
                System.out.println("Valor inválido! tente novamente");
            }
        } while (taxa < 0 || taxa > 20.0);
        return taxa;
    }
}