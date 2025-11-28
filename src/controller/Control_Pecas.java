package controller;

import java.util.ArrayList;

import Model.Pecas;
import Model.PecasDAO;

public class Control_Pecas {
	
	private static PecasDAO pecaDAO =new PecasDAO();
	
	public void lancamentoPecas(String id_os, String id_pecas, int quant_pecas ) {	
			pecaDAO.insertPecasUsadas(id_os, id_pecas, quant_pecas);
	}
	public void cadastrarPecas(ArrayList<Pecas> peca) {
		 pecaDAO.insertPecas(peca);
	}
	public void removerPecas(String id_num) {
				pecaDAO.proc_RemovePecas(id_num);
	}
	public void removePecasU(int numOS, int idPeca,int quant) {
		pecaDAO.removePecaUsada(numOS, idPeca, quant);
	}
	public void cont_pecas() {
		pecaDAO.count_pecas();
	}
	public void atualizarpecas(String idpecas, String coluna, String novoValor) {
		pecaDAO.atualizarPeca(idpecas, coluna, novoValor);
	}
	
}
