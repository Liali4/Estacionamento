class Carro extends Veiculo{
    String tipo = "Carro";

     public Carro (String placa, String marca, String modelo) {
         super(placa, marca, modelo);
    }

    public double calcularValor(int horas) {
        return horas * 12.00;
}

public double calcularValor(int horas) {//calculo por hora
        return horas * 12.00;
    }
}