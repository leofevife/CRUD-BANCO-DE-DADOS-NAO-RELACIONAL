package Model;

public class Funcionario extends Usuario {
	
	private float salario;
	private float custoHH;

	public Funcionario(String nome, String cpf, String telefone, String senha, String tipo,float salario) {
		super(nome,cpf,telefone,senha,tipo);
		
		this.salario 			= salario;
		this.setCustoHH(calc_custoHH(this.salario));
		
	}
	
	public Funcionario() {
		
	}

	private float calc_custoHH(float salario) {
		float cont= salario/220;
		return cont;
	}

	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getCustoHH() {
		return calc_custoHH(this.salario);
		 
	}
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", senha=" + senha + ", tipo="
				+ tipo + ", getSalario()=" + getSalario() + ", getCustoHH()=" + getCustoHH() + "]";
	}

	public void setCustoHH(float custoHH) {
		this.custoHH = custoHH;
	}	

	
	
}
