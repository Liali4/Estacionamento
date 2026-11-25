import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
class Estacionamento {

    private List<Vaga> vagas;
    private List<RegistroVeiculos> historico;
    private List<Veiculo> veiculos;

    private DateTimeFormatter formato =
        DateTimeFormatter.ofPattern("\ndd/MM/yyyy HH:mm:ss");

    public Estacionamento(int quantidadeVagas) {

        vagas = new ArrayList<>();
        historico = new ArrayList<>();
        veiculos = new ArrayList<>();

        for (int i = 1; i <= quantidadeVagas; i++) {
            vagas.add(new Vaga(i));
        }
    }

    // --------------------------------------
    // REGISTRAR ENTRADA
    // --------------------------------------

    public void entrada(Veiculo novoVeiculo) {

        String placa = novoVeiculo.getPlaca();

        // Verifica se a placa já está estacionada.

        for (RegistroVeiculos registro : historico) {

            if (registro.estaAtivo() &&
                registro.getVeiculo().getPlaca().equals(placa)) {

                System.out.println(
                    "\nErro: este veiculo ja esta estacionado!"
                );

                return;
            }
        }

        // Procura o cadastro anterior pela placa.
        // Se já existir, reutiliza o mesmo veículo.

        Veiculo veiculoExistente = null;

        for (Veiculo v : veiculos) {

            if (v.getPlaca().equals(placa)) {
                veiculoExistente = v;
                break;
            }
        }

        if (veiculoExistente != null) {
            novoVeiculo = veiculoExistente;
        }

        // Procura uma vaga disponível.

        for (Vaga vaga : vagas) {

            if (vaga.disponivel()) {

                vaga.ocupar(novoVeiculo);

                if (veiculoExistente == null) {
                    veiculos.add(novoVeiculo);
                }

                RegistroVeiculos registro =
                    new RegistroVeiculos(novoVeiculo, vaga);

                historico.add(registro);

                System.out.println("\n\nVeiculo registrado com sucesso!");

                System.out.println(
                    "\nVaga ocupada: " + vaga.getNumero()
                );

                System.out.println(
                    "\nHorario de entrada: " +
                    registro.getEntrada().format(formato)
                );

                return;
            }
        }

        System.out.println("\n\nEstacionamento lotado!");
        System.out.println("\n\nNao existem vagas disponiveis.");
    }

    // --------------------------------------
    // REGISTRAR SAIDA
    // --------------------------------------

    public void saida(String placa) {

        placa = placa.trim().toUpperCase();

        for (RegistroVeiculos registro : historico) {

            if (registro.estaAtivo() &&
                registro.getVeiculo().getPlaca().equals(placa)) {

                registro.finalizarRegistro();

                registro.getVaga().liberar();

                System.out.println("\n\nSaida registrada com sucesso!");

                System.out.println(
                    "\nVeiculo: " +
                    registro.getVeiculo().getModelo()
                );

                System.out.println(
                    "\nHorario de entrada: " +
                    registro.getEntrada().format(formato)
                );

                System.out.println(
                    "\nHorario de saida: " +
                    registro.getSaida().format(formato)
                );

                System.out.println(
                    "\nTempo de permanencia: " +
                    registro.getPermanencia() + "\n minutos"
                );

                System.out.println(
                    "\nHoras cobradas: " +
                    registro.getHorasCobradas()
                );

                System.out.printf(
                    Locale.forLanguageTag("\npt-BR"),
                    "\nValor: R$ %.2f%n",
                    registro.getValor()
                );

                System.out.println(
                    "\nVaga " + registro.getVaga().getNumero() +
                    " \nliberada com sucesso!"
                );

                return;
            }
        }

        System.out.println("\n\nErro: veiculo nao encontrado no estacionamento!" );
    }

    // --------------------------------------
    // CONSULTAR VAGAS
    // --------------------------------------

    public void vagas() {

        System.out.println("\n\n\n--- SITUACAO DAS VAGAS ---");

        for (Vaga vaga : vagas) {

            if (vaga.disponivel()) {

                System.out.println(
                    "\nVaga " + vaga.getNumero() + "\n - Disponivel"
                );

            } else {

                System.out.println(
                    "\nVaga " + vaga.getNumero() +
                    "\n - Ocupada (" +
                    vaga.getVeiculo().getPlaca() + ")"
                );
            }
        }
    }

    // --------------------------------------
    // LISTAR VEICULOS ESTACIONADOS
    // --------------------------------------

    public void veiculos() {

        System.out.println("\n\n--- VEICULOS ESTACIONADOS ---");

        boolean encontrou = false;

        for (Vaga vaga : vagas) {

            if (!vaga.disponivel()) {

                Veiculo v = vaga.getVeiculo();

                System.out.println("-----------------------------------");
                System.out.println("\nVeiculo");
                System.out.println("\nPlaca: " + v.getPlaca());
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Tipo: " + v.getTipo());
                System.out.println("Vaga: " + vaga.getNumero());
                System.out.println("-----------------------------------\n");

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("\n\nNao existem veiculos estacionados.");
        }
    }

    // --------------------------------------
    // CONSULTAR VEICULO PELA PLACA
    // --------------------------------------

    public void consultarVeiculo(String placa) {

        placa = placa.trim().toUpperCase();

        Veiculo encontrado = null;

        for (Veiculo v : veiculos) {

            if (v.getPlaca().equals(placa)) {
                encontrado = v;
                break;
            }
        }

        if (encontrado == null) {

            System.out.println("\nVeiculo nao cadastrado!");
            return;
        }

        System.out.println("\n--- DADOS DO VEICULO ---");

        System.out.println("\nPlaca: " + encontrado.getPlaca());
        System.out.println("\nMarca: " + encontrado.getMarca());
        System.out.println("\nModelo: " + encontrado.getModelo());
        System.out.println("\nTipo: " + encontrado.getTipo());

        for (Vaga vaga : vagas) {

            if (!vaga.disponivel() &&
                vaga.getVeiculo().getPlaca().equals(placa)) {

                System.out.println("\nSituacao: Estacionado");
                System.out.println("\nVaga: " + vaga.getNumero());

                return;
            }
        }

        System.out.println("\nSituacao: Fora do estacionamento");
    }

    // --------------------------------------
    // CONSULTAR HISTORICO
    // --------------------------------------

    public void consultarHistorico() {

        System.out.println("\n--- HISTORICO DE ENTRADAS E SAIDAS ---");

        if (historico.isEmpty()) {

            System.out.println("\nNenhum registro encontrado.");
            return;
        }

        for (RegistroVeiculos registro : historico) {

            System.out.println("\nPlaca: " +
                registro.getVeiculo().getPlaca());

            System.out.println("\nTipo: " +
                registro.getVeiculo().getTipo());

            System.out.println("\nVaga: " +
                registro.getVaga().getNumero());

            System.out.println("\nEntrada: " +
                registro.getEntrada().format(formato));

            if (registro.estaAtivo()) {

                System.out.println("\nSaida: Veiculo ainda estacionado");

            } else {

                System.out.println("\nSaida: " +
                    registro.getSaida().format(formato));

                System.out.println("\nPermanencia: " +
                    registro.getPermanencia() + "\n minutos");

                System.out.printf(
                    Locale.forLanguageTag("pt-BR"),
                    "\nValor pago: R$ %.2f%n",
                    registro.getValor()
                );
            }

            System.out.println("------------------------------");
        }
    }
}