import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Main {

public static void main(String[] args) {

   Scanner entrada = new Scanner(System.in);

     Estacionamento estacionamento = new Estacionamento(10);

        int opcao = -1;

        do {

            System.out.println("\n\n====================================");
            System.out.println("    SISTEMA DE ESTACIONAMENTO");
            System.out.println("====================================");

            System.out.println("1 - Registrar entrada de veiculo");
            System.out.println("2 - Registrar saida de veiculo");
            System.out.println("3 - Consultar vagas disponiveis");
            System.out.println("4 - Listar veiculos estacionados");
            System.out.println("5 - Consultar veiculo pela placa");
            System.out.println("6 - Consultar historico");
            System.out.println("0 - Sair");

            System.out.print("\nEscolha uma opcao: ");

            try {

                opcao = Integer.parseInt(entrada.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("\nDigite uma opcao numerica valida!");
                continue;
            }

            switch (opcao) {

                // ----------------------------------
                // ENTRADA
                // ----------------------------------

                case 1:

                    System.out.println("\n\n--- ENTRADA DE VEICULO ---");

                    System.out.print("\nInforme a placa: ");
                    String placa = entrada.nextLine().trim();

                    if (placa.isEmpty()) {
                        System.out.println("\nA placa nao pode ficar vazia!");
                        break;
                    }

                    System.out.print("\nInforme a marca: ");
                    String marca = entrada.nextLine().trim();

                    System.out.print("\nInforme o modelo: ");
                    String modelo = entrada.nextLine().trim();

                    if (marca.isEmpty() || modelo.isEmpty()) {
                        System.out.println("\nPreencha marca e modelo!");
                        break;
                    }

                    System.out.println("\nTipo de veiculo:");
                    System.out.println("\n1 - Carro");
                    System.out.println("\n2 - Moto");
                    System.out.println("\n3 - Caminhonete");

                    System.out.print("\nEscolha: ");

                    int tipo;

                    try {

                        tipo = Integer.parseInt(entrada.nextLine());

                    } catch (NumberFormatException e) {

                        System.out.println("\nTipo invalido!");
                        break;
                    }

                    Veiculo veiculo = null;

                    switch (tipo) {

                        case 1:
                            veiculo = new Carro(placa, marca, modelo);
                            break;

                        case 2:
                            veiculo = new Moto(placa, marca, modelo);
                            break;

                        case 3:
                            veiculo = new Caminhonete(placa, marca, modelo);
                            break;

                        default:
                            System.out.println("\nTipo de veiculo invalido!");
                    }

                    if (veiculo != null) {
                        estacionamento.entrada(veiculo);
                    }

                    break;

                // ----------------------------------
                // SAIDA
                // ----------------------------------

                case 2:

                    System.out.println("\n\n--- SAIDA DE VEICULO ---");

                    System.out.print("\nInforme a placa: ");

                    String placaSaida = entrada.nextLine();

                    estacionamento.saida(placaSaida);

                    break;

                // ----------------------------------
                // CONSULTAR VAGAS
                // ----------------------------------

                case 3:

                    estacionamento.vagas();

                    break;

                // ----------------------------------
                // LISTAR VEICULOS
                // ----------------------------------

                case 4:

                    estacionamento.veiculos();

                    break;

                // ----------------------------------
                // CONSULTAR VEICULO
                // ----------------------------------

                case 5:

                    System.out.print("\n\nInforme a placa: ");

                    String placaConsulta = entrada.nextLine();

                    estacionamento.consultarVeiculo(placaConsulta);

                    break;

                // ----------------------------------
                // HISTORICO
                // ----------------------------------

                case 6:

                    estacionamento.consultarHistorico();

                    break;

                // ----------------------------------
                // ENCERRAR
                // ----------------------------------

                case 0:

                    System.out.println("\n\nEncerrando o sistema...");

                    break;

                default:

                    System.out.println("\nOpcao invalida!");
            }

        } while (opcao != 0);

        entrada.close();
    }
}