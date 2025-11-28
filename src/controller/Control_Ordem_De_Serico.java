package controller;

import Model.Ordem_De_Servico;
import Model.Ordem_De_ServicoDAO;


public class Control_Ordem_De_Serico {
	
	private static Ordem_De_ServicoDAO osDAO=new Ordem_De_ServicoDAO();
	
	
	public void criaOS( Ordem_De_Servico os) {
		osDAO.insertOrdemServico(os);
	}
	public void finalizarOS() {	
	}

	public void removeOS(String id) {
		osDAO.removeOrdemServico(id);
	}
	public void cont_os() {
		osDAO.count_OS();
	}
	public void atualiazarso(String idOS, String coluna, Object novoValor) {
		osDAO.atualizarOrdemServico(idOS, coluna, novoValor);
	}
	
	
	
}
