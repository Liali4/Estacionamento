class Carro extends Veiculo{
    String tipo = "Carro";

     public Carro (String placa, String marca, String modelo) {
         super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 12.00;
}

    public void exibirInfo(int horas) {
        System.out.printf("Tipo: %s | Modelo: %s | Placa: %s | Valor (%dh): R$ %.2f%n",
                tipo, modelo, placa, horas, calcularValor(horas));
    }
    public String getTipo() {
        return "Carro";
    }
}
