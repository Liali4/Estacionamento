abstract class Veiculo implements Calculavel{
    protected String placa;
    protected String modelo;
    protected String marca;

    
    public Veiculo(String placa, String marca, String modelo){ //metodo construtor
        this.placa=placa;
        this.modelo=modelo;
        this.marca=marca;

    }

    //metodos get e set

    //placa
    public void setPlaca(String placa){
        this.placa=placa;
    
    }
    public String getPlaca(){
        return this.placa;
    }
    
    //modelo
    public void setModelo(String modelo){
        this.modelo=modelo;
    }
    public String getModelo(){
        return this.modelo;
    }

    //marca 
    public void setMarca(String marca){
        this.marca=marca;
    }
    public String getMarca(){
        return this.marca;
    }

    public abstract String getTipo();
}