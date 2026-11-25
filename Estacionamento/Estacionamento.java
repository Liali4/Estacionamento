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
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
                    "Erro: este veiculo ja esta estacionado!"
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

                System.out.println("\nVeiculo registrado com sucesso!");

                System.out.println(
                    "Vaga ocupada: " + vaga.getNumero()
                );

                System.out.println(
                    "Horario de entrada: " +
                    registro.getEntrada().format(formato)
                );

                return;
            }
        }

        System.out.println("\nEstacionamento lotado!");
        System.out.println("Nao existem vagas disponiveis.");
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

                System.out.println("\nSaida registrada com sucesso!");

                System.out.println(
                    "Veiculo: " +
                    registro.getVeiculo().getModelo()
                );

                System.out.println(
                    "Horario de entrada: " +
                    registro.getEntrada().format(formato)
                );

                System.out.println(
                    "Horario de saida: " +
                    registro.getSaida().format(formato)
                );

                System.out.println(
                    "Tempo de permanencia: " +
                    registro.getPermanencia() + " minutos"
                );

                System.out.println(
                    "Horas cobradas: " +
                    registro.getHorasCobradas()
                );

                System.out.printf(
                    Locale.forLanguageTag("pt-BR"),
                    "Valor: R$ %.2f%n",
                    registro.getValor()
                );

                System.out.println(
                    "Vaga " + registro.getVaga().getNumero() +
                    " liberada com sucesso!"
                );

                return;
            }
        }

        System.out.println(
            "Erro: veiculo nao encontrado no estacionamento!"
        );
    }

    // --------------------------------------
    // CONSULTAR VAGAS
    // --------------------------------------

    public void vagas() {

        System.out.println("\n--- SITUACAO DAS VAGAS ---");

        for (Vaga vaga : vagas) {

            if (vaga.disponivel()) {

                System.out.println(
                    "Vaga " + vaga.getNumero() + " - Disponivel"
                );

            } else {

                System.out.println(
                    "Vaga " + vaga.getNumero() +
                    " - Ocupada (" +
                    vaga.getVeiculo().getPlaca() + ")"
                );
            }
        }
    }

    // --------------------------------------
    // LISTAR VEICULOS ESTACIONADOS
    // --------------------------------------

    public void veiculos() {

        System.out.println("\n--- VEICULOS ESTACIONADOS ---");

        boolean encontrou = false;

        for (Vaga vaga : vagas) {

            if (!vaga.disponivel()) {

                Veiculo v = vaga.getVeiculo();

                System.out.println("\nPlaca: " + v.getPlaca());
                System.out.println("Marca: " + v.getMarca());
                System.out.println("Modelo: " + v.getModelo());
                System.out.println("Tipo: " + v.getTipo());
                System.out.println("Vaga: " + vaga.getNumero());

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nao existem veiculos estacionados.");
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

            System.out.println("Veiculo nao cadastrado!");
            return;
        }

        System.out.println("\n--- DADOS DO VEICULO ---");

        System.out.println("Placa: " + encontrado.getPlaca());
        System.out.println("Marca: " + encontrado.getMarca());
        System.out.println("Modelo: " + encontrado.getModelo());
        System.out.println("Tipo: " + encontrado.getTipo());

        for (Vaga vaga : vagas) {

            if (!vaga.disponivel() &&
                vaga.getVeiculo().getPlaca().equals(placa)) {

                System.out.println("Situacao: Estacionado");
                System.out.println("Vaga: " + vaga.getNumero());

                return;
            }
        }

        System.out.println("Situacao: Fora do estacionamento");
    }

    // --------------------------------------
    // CONSULTAR HISTORICO
    // --------------------------------------

    public void consultarHistorico() {

        System.out.println("\n--- HISTORICO DE ENTRADAS E SAIDAS ---");

        if (historico.isEmpty()) {

            System.out.println("Nenhum registro encontrado.");
            return;
        }

        for (RegistroVeiculos registro : historico) {

            System.out.println("\nPlaca: " +
                registro.getVeiculo().getPlaca());

            System.out.println("Tipo: " +
                registro.getVeiculo().getTipo());

            System.out.println("Vaga: " +
                registro.getVaga().getNumero());

            System.out.println("Entrada: " +
                registro.getEntrada().format(formato));

            if (registro.estaAtivo()) {

                System.out.println("Saida: Veiculo ainda estacionado");

            } else {

                System.out.println("Saida: " +
                    registro.getSaida().format(formato));

                System.out.println("Permanencia: " +
                    registro.getPermanencia() + " minutos");

                System.out.printf(
                    Locale.forLanguageTag("pt-BR"),
                    "Valor pago: R$ %.2f%n",
                    registro.getValor()
                );
            }

            System.out.println("------------------------------");
        }
    }
}