package controller;

import Model.Usuario;
import Model.UsuarioDAO;
import Model.Funcionario;
import Model.Cliente;



public class Control_Usuario {
	
	
	private static UsuarioDAO usuDAO=new UsuarioDAO();
		
	public void cadastro(int tipo_usuario, Usuario usu) {
		
	 if(tipo_usuario==1) {
		 	usuDAO.insertFuncionario((Funcionario) usu);
		 }else {
			 usuDAO.insertCliente((Cliente) usu);
		 }
	 
	}
	

	public Usuario return_usuario(String id) {
		return usuDAO.return_usuario(id);
	}

	public boolean removeUsuarioC(String id_num) {
		return usuDAO.removeUsuarioC(id_num);
		
	}
	public void cont_usu() {
		usuDAO.count_usu();
	}
	
	public void atuli_usu(String idUsuario, String coluna, String novoValor) {
		usuDAO.updat_usu(idUsuario, coluna, novoValor);
	}
	
	
	
	
}
