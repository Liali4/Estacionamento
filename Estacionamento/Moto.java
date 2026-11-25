class Moto extends Veiculo {
    String tipo = "Moto";

    public Moto(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 7.00;
    }


    public String getTipo() {
        return "Moto";
    }
}
