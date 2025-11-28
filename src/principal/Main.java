package principal;

import java.util.ArrayList;

import view.Tela;

public class Main {

	
	public static void main(String[] args) {	
			
	Tela tela=new Tela();
	tela.splashScreen();
	
	
	
		while(true) {
			System.out.println();
			int opcao=tela.tela_menu();
			
			if(opcao==1) {
			relatorios(tela);
			}else if(opcao==2) {
				inserir(tela);
			}else if(opcao==3) {
				remover(tela);
			}else if(opcao==4) {
				atualizar(tela);
			}else if(opcao==5) {
				System.out.println("FINALIZANDO O PROGRAMA.........");
				break;
			}
		}
	}
	
	public static String estratoID(ArrayList<String> vet, int index) {
		int ind=index-1;
	    if (vet == null) {
	        return null;
	    }

	    if (ind >= 0 && ind < vet.size()) {
	        return vet.get(ind);   
	    } else {
	        return null;
	    }
	}

	
	private static void relatorios(Tela tela) {
		
		while(true) {
		System.out.println();
		int num = tela.tela_relatorio();
		
		switch (num) {
        case 1:
        	tela.relat_Usuario();
        	break;
        case 2:
        	tela.relat_OrdemServico();
        	break;
        case 3:
        	tela.relat_pecas();
        	break;
        case 4:
        	tela.relat_pecasUsadas();
        	break;
        case 5:
        	tela.relat_pecasTipo();
        	break;
        case 6:
        	return;
		}
	}
	}

	private static void inserir(Tela tela) {
		while(true) {
		System.out.println();
		int num = tela.tela_inserir();
		switch (num) {
        case 1:
        	tela.tela_cadastro();
        	
        	break;
        case 2:
        	tela.tela_criaOS();
        	break;
        case 3:
        	tela.tela_cadastrarPecas();
        	break;
        case 4:
        	tela.tela_lancamentoPecas();
        	break;
        case 5:
        	return;
		}
		}
		
	}

	private static void remover(Tela tela) {
		while(true) {
			System.out.println();
		int num = tela.tela_remover();
			
		switch (num) {
        case 1:
        	tela.tela_removeUsuario();
        	  
        	break;
        case 2:
        	tela.tela_removerPecas();
        	break;
        case 3:
        	tela.tela_removeOS();
        	break;
        case 4:
        	return;      
        	
			}
		
			
		}
	}

	private static void atualizar(Tela tela) {
		while(true) {
			System.out.println();
			int num = tela.tela_atualizar();
				
			switch (num) {
	        case 1:
	        	tela.tela_atualizarUsu();
	        	break;
	        case 2:
	        	tela.tela_atualizarPecas();
	        	break;
	        case 3:
	        	tela.tela_atualizarOS();
	          	break;
	        case 4:
	        	return;
	        
				}
			boolean bol=tela.tela_confir("DESEJA FAZER OUTRA ALTERAÇÃO ?");
			if(bol==false) {
				break;
			}
			}
		
	}

	
}
