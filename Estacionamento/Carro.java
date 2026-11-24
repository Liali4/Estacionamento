class Carro extends Veiculo{
    

     public Carro (String placa, String marca, String modelo) {
         super(placa, marca, modelo);
    }

     public String getTipo() {
        return "Carro";
}

public double calcularValor(int horas) {//calculo por hora
        return horas * 12.00;
    }
}