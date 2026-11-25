

import java.time.LocalDateTime;
import java.time.Duration;


class RegistroVeiculos{
    private Veiculo veiculo;// ver depois
    private Vaga vaga;

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private long permanencia;
    private double valor;

     public RegistroVeiculos(Veiculo veiculo, Vaga vaga) {

        this.veiculo = veiculo;
        this.vaga = vaga;

        this.entrada = LocalDateTime.now();
        this.saida = null;

        this.permanencia = 0;
        this.valor = 0;
    }

    //metodo get e set
    public void setVeiculo(Veiculo veiculo){
        this.veiculo=veiculo;
    }
    public Veiculo getVeiculo(){
        return this.veiculo;
    }

    public void getVaga(Vaga vaga){
        this.vaga=vaga;
    }
    public Vaga getVaga(){
        return this.vaga;
    }

    public void setEntrada(LocalDateTime entrada){
        this.entrada=entrada;
    }
    public LocalDateTime getEntrada(){
        return this.entrada;
    }

    public void setSaida(LocalDateTime saida){
        this.saida=saida;
    }
    public LocalDateTime getSaida(){
        return this.saida;
    }

    public void setPermanencia(long permanencia){
        this.permanencia=permanencia;
    }
    public long getPermanencia(){
        return this.permanencia;
    }

    public void setValor(double valor){
        this.valor=valor;
    }
    public double getValor(){
        return this.valor;
    }
     public boolean estaAtivo() {
        return saida == null;
    }

    public long calcularPermanencia() { // calculo das horas na vaga

        LocalDateTime horarioFinal = saida;

        if (horarioFinal == null) {
            horarioFinal = LocalDateTime.now();
        }

        return Duration.between(entrada, horarioFinal).toMinutes();
    }

    public void finalizarRegistro() {

        if (estaAtivo()) {

            saida = LocalDateTime.now();

            permanencia = calcularPermanencia();

            // Arredonda qualquer fração para uma hora completa.
            // A cobrança mínima é de uma hora.

            int horas = (int) Math.max(
                1, (permanencia + 59) / 60
            );

            valor = veiculo.calcularValor(horas);
        }
    }

    public int getHorasCobradas() {

        return (int) Math.max(
            1, (permanencia + 59) / 60
        );
    }
}

