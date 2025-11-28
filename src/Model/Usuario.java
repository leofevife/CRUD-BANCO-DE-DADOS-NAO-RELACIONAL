package Model;


public  class Usuario{
	
	protected int id;
	protected  String 	nome;
	protected String 	cpf;
	protected String 	telefone;
	protected  String 	senha;
	protected String 	tipo;

	
	public Usuario(String nome, String cpf, String numero, String senha, String tipo) {
		super();
		
		this.nome 			= nome;
		this.cpf 			= cpf;
		this.telefone 		= numero;
		this.senha			=senha;
		this.tipo			=tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario() {
		super();
		this.nome 			= null;
		this.cpf 			= null;
		this.telefone 		= null;
		this.senha			=null;
		this.tipo			=null;
	
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", senha=" + senha + ", tipo="
				+ tipo + "]";
	}



	
	
	
	
	
	
}
