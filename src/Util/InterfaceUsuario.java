package Util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario  {
    private Scanner scanner;

    //construtor
    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
    }

   //metodo
    public double pedirValorImovel() {
        double valor = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.println("Digite o valor do imóvel: ");
                valor = scanner.nextDouble();

                if (valor > 0) {
                    System.out.println("O valor do imóvel é: " + valor);
                    entradaValida = true;
                } else {
                    System.out.println("Valor invalido.... Tente novamente ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número para o valor do imóvel.");
                scanner.next();
            }
        } while (!entradaValida);
        return valor;
    }

    public int pedirPrazoFinanciamento() {
        int prazo = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite o prazo do financiamento em anos: ");
                prazo = scanner.nextInt();

                if (prazo > 0) {
                    System.out.println("O prazo do financiamento é: " + prazo + " anos.");
                    entradaValida = true;
                } else {
                    System.out.println("Prazo inválido.... Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro para o prazo do financiamento.");
                scanner.next();
            }
        } while (!entradaValida);

        return prazo;

    }
    public double pedirTaxaJurosAnual() {
        double taxa = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite a taxa de juros anual (0-20%): ");
                taxa = scanner.nextDouble();

                if (taxa >= 0 && taxa <= 20.0) {
                    System.out.println("Taxa de juros válida: " + taxa + "%");
                    entradaValida = true;
                } else if (taxa > 20.0) {
                    System.out.println("Taxa de juros muito alta! Máximo permitido: 20%.");
                } else {
                    System.out.println("Valor inválido.... Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número para a taxa de juros anual.");
                scanner.next();
            }
        } while (!entradaValida);
        return taxa;
    }

    public String pedirTipoFinanciamento() {
        scanner.nextLine();

        String tipo;
        while (true) {
            System.out.print("Qual o tipo de imóvel? (casa / apartamento / terreno): ");
            tipo = scanner.nextLine().toLowerCase().trim();

            if (tipo.equals("casa") || tipo.equals("apartamento") || tipo.equals("terreno")) {
                return tipo;
            }

            System.out.println("Tipo inválido. Por favor, escolha uma das opções.");
        }
    }

    public void fecharScanner() {
        scanner.close();
    }
}