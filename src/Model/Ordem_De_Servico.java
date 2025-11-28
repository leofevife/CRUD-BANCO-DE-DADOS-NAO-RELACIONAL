package Model;

public class Ordem_De_Servico {

    private String id_Funcionario;
    private String id_Cliente;

    private String status;
    private float valorTotal;
    private float valorPecas;
    private float custoHH;
    private int cargaHoraria;
    private String observacao;


    public Ordem_De_Servico(String id_Funcionario, String id_Cliente, String observacao, float custoHH) {
        this.id_Funcionario = id_Funcionario;
        this.id_Cliente = id_Cliente;
        this.observacao = observacao;

        this.status = "ABERTO";

        this.custoHH = custoHH;
        this.cargaHoraria = 1;
        this.valorPecas = 0;
        this.valorTotal = custoHH + valorPecas;
    }

    public Ordem_De_Servico() { }

 

    public String getFuncionario() { return id_Funcionario; }
    public void setFuncionario(String id_Funcionario) { this.id_Funcionario = id_Funcionario; }

    public String getCliente() { return id_Cliente; }
    public void setCliente(String id_Cliente) { this.id_Cliente = id_Cliente; }

    public float getValorTotal() { return valorTotal; }
    public float getValorPecas() { return valorPecas; }
    public void setValorPecas(float valorPecas) {
        this.valorPecas = valorPecas;
        atualizarTotal();
    }

    public float getCustoHH() { return custoHH; }
    public void setCustoHH(float custoHH) {
        this.custoHH = custoHH;
        atualizarTotal();
    }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    private void atualizarTotal() {
        this.valorTotal = this.valorPecas + (this.custoHH * this.cargaHoraria);
    }

    @Override
    public String toString() {
        return "Status: " + status +
                " | Mecanico: " + id_Funcionario +
                " | Cliente: " + id_Cliente +
                " | HH: " + custoHH +
                " | Peças: " + valorPecas +
                " | Total: " + valorTotal +
                " | Obs: " + observacao;
    }
}
