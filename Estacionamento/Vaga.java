public class Vaga {
    private int numero;
    private Veiculo veiculo;

    public Vaga(int numero, Veiculo veiculo){ //metodo construtor vaga
        this.numero = numero;
        this.veiculo = null;
    }

    //get e set
    public void setNumero(int numero){
        this.numero=numero;
    }
    public int getNumero(){
        return this.numero;
    }

    public void setVeiculo(Veiculo veiculo){
        this.veiculo=veiculo;
    }
    public Veiculo getVeiculo(){
        return this.veiculo;
    }


    public boolean disponivel(){
        return veiculo == null;
    }

    public void ocupar(Veiculo veiculo){// ocupar vaga
        if (disponivel()){
            System.out.println("Vaga ocupada com sucesso!");
            this.veiculo=veiculo;
        }
        else{
            System.out.println("Erro, vaga ocupada");
        }
    }

    public void liberar(){// liberar vaga, verificar depois
        if (!disponivel()){
            System.out.println("Vaga liberada com sucesso!");
            this.veiculo=null;
        }
        else{
            System.out.println("Erro, vaga já está livre");
        }
    }
}
