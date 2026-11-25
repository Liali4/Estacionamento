class Caminhonete extends Veiculo {


    public Caminhonete(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 18.00;
    }

    public void exibirInfo(int horas) {
        System.out.printf("Tipo: %s | Modelo: %s | Placa: %s | Valor (%dh): R$ %.2f%n",
                tipo, modelo, placa, horas, calcularValor(horas));

  public String getTipo() {
        return "Caminhonete";
    }
    }
}
