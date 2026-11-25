class Caminhonete extends Veiculo {


    public Caminhonete(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 18.00;
    }

    
  public String getTipo() {
        return "Caminhonete";
    }
    
}
