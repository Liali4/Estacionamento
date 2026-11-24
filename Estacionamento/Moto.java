class Moto extends Veiculo {
    String tipo = "Moto";

    public Moto(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 7.00;
    }

    public void exibirInfo(int horas) {
        System.out.printf("Tipo: %s | Modelo: %s | Placa: %s | Valor (%dh): R$ %.2f%n",
                tipo, modelo, placa, horas, calcularValor(horas));
    }
}
