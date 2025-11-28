package Model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	
	private String 	moto_modelo;
	private int 	moto_Ano;
	private String 	moto_Placa;
	
	public Cliente(String nome, String cpf, String telefone, String senha, String tipo, String moto_modelo,
			int moto_Ano, String moto_Placa) {
		
		super(nome, cpf, telefone, senha, tipo);
		this.moto_modelo 		= moto_modelo;
		this.moto_Ano 			= moto_Ano;
		this.moto_Placa 		= moto_Placa;
	}
	public Cliente() {
		
		super();
		
		this.moto_modelo 		= null;
		this.moto_Ano 			= 0;
		this.moto_Placa 		= null;
	}
	

	public String getMoto_modelo() {
		return moto_modelo;
	}
	public void setMoto_modelo(String moto_modelo) {
		this.moto_modelo = moto_modelo;
	}
	public int getMoto_Ano() {
		return moto_Ano;
	}
	public void setMoto_Ano(int moto_Ano) {
		this.moto_Ano = moto_Ano;
	}
	public String getMoto_Placa() {
		return moto_Placa;
	}
	public void setMoto_Placa(String moto_Placa) {
		this.moto_Placa = moto_Placa;
	}
	@Override
	public String toString() {
		return "Cliente [moto_modelo=" + moto_modelo + ", moto_Ano=" + moto_Ano + ", moto_Placa=" + moto_Placa
				+ ", getNome()=" + getNome() + ", getCpf()=" + getCpf() + ", getTelefone()=" + getTelefone()
				+ ", getTipo()=" + getTipo() + "]";
	}
	public void toString_Cliente(ArrayList<Usuario> list_usuarios) {
		
		  System.out.println("Lista de clientes:");
		  System.out.println("___________________________________________");
		    for (int i = 0; i < list_usuarios.size(); i++) {
		    	
		    	if("CLIENTE".equals(list_usuarios.get(i).getTipo()))
		        System.out.println("ID: " + i + " | NOME: " + list_usuarios.get(i).getNome()+" | CPFOR:"+list_usuarios.get(i).getCpf());
		    }
		    System.out.println("___________________________________________");
	}
	
	
	

}
