package view;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import Model.Cliente;
import Model.Funcionario;
import Model.Ordem_De_Servico;
import Model.Pecas;
import Model.PecasEletricas;
import Model.PecasMecanica;
import Model.Usuario;
import controller.Control_Ordem_De_Serico;
import controller.Control_Pecas;	
import controller.Control_Usuario;
import principal.Main;
import reports.RelatoriosOs;
import reports.RelatoriosPecas;
import reports.RelatoriosUsu;

public class Tela {
			static Scanner scan=new Scanner(System.in);

	
			public void splashScreen() {
				Control_Usuario cont_usu = new Control_Usuario();
				Control_Pecas cont_pecas = new Control_Pecas();
				Control_Ordem_De_Serico cont_os = new Control_Ordem_De_Serico();
				System.out.println("--------- SISTEMA DE GERENCIAMENTO DE MANUTENÇÃO (SGM) ---------");
				  
			
			    System.out.println(">> TOTAL DE REGISTROS EXISTENTES:\n");
			    cont_usu.cont_usu();
			    cont_pecas.cont_pecas();
			    cont_os.cont_os();
			    System.out.println();
			    
			   
			    System.out.println("CRIADO POR:");
			    System.out.println(" - LARISSA MORAES DE JESUS");
			    System.out.println(" - BRUNO OLIVEIRA DUARTE");
			    System.out.println(" - LEO FERNANDE \n");
			
			    System.out.println("DISCIPLINA: BANCO DE DADOS 2025/2");
			    System.out.println("PROFESSOR: HAWARD ROATTI\n");
			}
				
	public int tela_menu() {
		System.out.println("--------- SISTEMA DE GERENCIAMENTO DE MANUTENÇÃO (SGM) ---------\n");
		System.out.println("----------------- MENU -----------------");
		
		System.out.println("1- RELATORIOS\n"
				+ "2- INSERIR REGISTROS\n"
				+ "3- REMOVER REGISTROS\n"
				+ "4- ATUALIZAR\n"
				+ "5- SAIR");
		return scan.nextInt();
	}
	public int tela_inserir() {
		System.out.println("----------------- INSERT -----------------");
		
		System.out.println("1- CADASTRO USUARIO(CLIENTE OU FUNCIONARIO)\n"
				+ "2- CADASTRO O.S\n"
				+ "3- CADASTRO DE PEÇAS\n"
				+ "4- ALOCAÇÃO DE INSUMOS\n"
				+ "5- SAIR");
		return scan.nextInt();	

	}
	public int tela_remover() {
		System.out.println("----------------- REMOVE -----------------");
		
		System.out.println("1- REMOVER USUARIO(CLIENTE OU FUNCIONARIO)\n"
				+ "2- REMOVER PEÇAS\n"
				+ "3- REMOVER O.S\n"				
				+ "4- SAIR");
		return scan.nextInt();
		
	}
	public int tela_relatorio() {
		System.out.println("----------------- REPORT -----------------");
		
		System.out.println("1- RELATORIO DE USUARIO\n"
				+ "2- RELATORIO DE O.S\n"
				+ "3- RELATORIO DE PEÇAS\n"
				+ "4- RELATORIO DE PEÇAS USADAS POR OS\n"
				+ "5- RELATORIO DE PEÇAS POR TIPO\n"
				+ "6- SAIR");
		return scan.nextInt();
	}	
	public void tela_cadastro() {
		Control_Usuario cont_usu=new Control_Usuario();
	
		System.out.println("----------------- CADASTRO DE USUARIO -----------------");
		
		System.out.print("NOME:");
		String nome		=scan.next().toUpperCase();
		System.out.print("CPF:"); 
		String cpf			=scan.next();
		System.out.print("TELEFONE:");
		String telefone	=scan.next().trim();
		System.out.print("SENHA:");
		String senha		=scan.next();
		System.out.println("1- FUNCIONARIO\n"
				+ "2- CLIENTE");
		int tipo_usuario	=scan.nextInt();
		
		 if(tipo_usuario==1) {
			 
			 String tipo="FUNCIONARIO";
			 System.out.print("Salario do Coloborador:");
			 float salario	=scan.nextFloat();
			 
			 Funcionario funcionario = new Funcionario(nome,cpf,telefone,senha,tipo,salario);
			 cont_usu.cadastro(tipo_usuario,funcionario);

			 return;
			 
			 
		 }else if (tipo_usuario==2) {
			 String tipo="CLIENTE";
			 
			 System.out.println("Modelo da moto");		
			 String 	moto_modelo=scan.next();
			 System.out.println("Modelo da Ano");
			 int 	moto_Ano=scan.nextInt();
			 System.out.println("Modelo da Placa");
			 String 	moto_Placa=scan.next();
			 System.out.println("Novo Cliente cadastrado com sucesso!");
			 Cliente cliente= new Cliente( nome,cpf,telefone,senha,tipo, moto_modelo, moto_Ano, moto_Placa);
			
			  cont_usu.cadastro(tipo_usuario,cliente);
				return;
		 }
		 
	}
	public void tela_cadastrarPecas() {
		
		Control_Pecas cont_pecas=new Control_Pecas();
		ArrayList<Pecas> lista = new ArrayList<Pecas> ();
				
		while(true) {
		
		System.out.println("------------- CADASTRO DE PEÇAS -------------");
				
		System.out.print("Nome:");
		 String 	nomePeca=scan.next().toUpperCase();
		 System.out.print("valor:");
		 float		valorPecas=scan.nextInt();
		 System.out.print("Unidade de medida:");
		 String 	unid=scan.next();
		 System.out.print("Quantidade de peças:");
		 int 	quat_Pecas=scan.nextInt();
	
		 
		 	 
		 float valor_unid=(valorPecas/quat_Pecas);
		 System.out.println("MODELO DE ELEMENTO DE MAQUINA :\n"
			 		+ "1- ELEMENTO MECANICO\n"
			 		+ "2- ELEMENTO ELETRICO\n");
		 int num=scan.nextInt();
		 
		 String tipo_elemento = null;
		 if(num==1) {
			 		tipo_elemento="MECANICO";
		 }else if(num==2) {
			 		tipo_elemento="ELETRICO";
		 }else {
			 System.out.println("Elemento não identificado");
		 }
			 
		 String carac_elemento = null;
		 if(num==1) {
			 
			 System.out.println("QUAL O MATERIAL DESTA PEÇA: ");
			 carac_elemento=scan.next();
			 PecasMecanica pecMec=new PecasMecanica(nomePeca, valor_unid,unid, quat_Pecas, valor_unid, tipo_elemento,carac_elemento);
			
			  lista.add(pecMec);
			  
		 }else if(num==2) {
			 
			 System.out.println("QUAL A VOLTAGEM: ");
			 carac_elemento=scan.next()+" volt";
			 PecasEletricas pecEle = new PecasEletricas(nomePeca, valor_unid,unid, quat_Pecas, valor_unid, tipo_elemento,carac_elemento);
			 
			 lista.add(pecEle);
			 
		 }else {
			 System.out.println("Elemento não identificado");
		 }
		 
		 System.out.println("DESEJA CADASTRAR OUTRA PEÇA\n"
		 		+ "1- SIM\n"
		 		+ "2- NÃO");
		 int opcao=scan.nextInt();
		 if(opcao==2) {
			 System.out.println("voltando ao menu...");
			 cont_pecas.cadastrarPecas(lista);
			 break;
		 }else if(opcao==1) {
			 System.out.println("Peça salva com sucesso.");
		 }
		 
		 
		}
		 
		
	}

	
	public void tela_criaOS() {
	    RelatoriosUsu relatUsu = new RelatoriosUsu();
	    Control_Usuario cont_usu = new Control_Usuario();
	    Control_Ordem_De_Serico cont_os = new Control_Ordem_De_Serico();

	    System.out.println("----------------- CRIAR OS -----------------");

	   
	    ArrayList<String> vetFunc = relatUsu.vw_Funcionario();
	    if (vetFunc == null || vetFunc.isEmpty()) {
	        System.out.println("Nenhum funcionário disponível.");
	        return;
	    }

	    System.out.print("DIGITE O NÚMERO DO FUNCIONÁRIO: ");
	    int numFuncionario = scan.nextInt();
	    String idFunc = Main.estratoID(vetFunc, numFuncionario);
	    if (idFunc == null) {
	        System.out.println("Funcionário inválido.");
	        return;
	    }

	   
	    ArrayList<String> vetCli = relatUsu.vw_Clientes();
	    if (vetCli == null || vetCli.isEmpty()) {
	        System.out.println("Nenhum cliente disponível.");
	        return;
	    }

	    System.out.print("\nDIGITE O NÚMERO DO CLIENTE: ");
	    int numCliente = scan.nextInt();
	    String idCliente = Main.estratoID(vetCli, numCliente);
	    if (idCliente == null) {
	        System.out.println("Cliente inválido.");
	        return;
	    }

	    scan.nextLine(); 
	    System.out.print("DESCRIÇÃO DA ATIVIDADE: ");
	    String observacao = scan.nextLine();

	    
	    float custoHH = 50;

	    
	    Ordem_De_Servico os = new Ordem_De_Servico(idFunc, idCliente, observacao, custoHH);

	   
	    boolean confirmar = tela_confir("CRIAR");
	    if (confirmar) {
	        cont_os.criaOS(os);
	        System.out.println("OS criada com sucesso!");
	        System.out.println(os.toString());
	    } else {
	        System.out.println("Criação de OS cancelada.");
	    }
	}


	
	public void tela_lancamentoPecas() {
		RelatoriosOs relatOs=new RelatoriosOs();
		RelatoriosPecas relatPec =new  RelatoriosPecas();
		Control_Pecas cont_pecas =new Control_Pecas();
		
		
		System.out.println("----------------- ALOCAR PECAS -----------------\n");

		ArrayList<String> vet = relatOs.relat_OsCompleto();
		
		System.out.println("\nINSIRA O ID QUE VAMOS ALTERA");
		System.out.println("ID OS:");		
		int id=scan.nextInt();
		String id_os=Main.estratoID(vet, id);

		ArrayList<String> vet2=relatPec.relat_Pecas();
		while(true) {
			System.out.println("\nINSIRA O ID PARA ALOCAR A PEÇAS OU 0 PARA PULAR ESTA ETAPA");
			System.out.println("DIGITE O ID PEÇAS:");
			int idP=scan.nextInt();
			String id_pecas=Main.estratoID(vet2, idP);
			if(idP==0) {
				break;
			}else {
			System.out.println("QUANT. PEÇAS:");
			int quant_pecas=scan.nextInt();
			cont_pecas.lancamentoPecas(id_os, id_pecas, quant_pecas);
			
			}
			
		}
	}		
	public boolean tela_confir(String operacao) {
		System.out.println("DESEJA "+operacao+"?");
		System.out.println("DIGITE S PARA CONFIRMAR OU N PARA CANCELAR ,POR FAVOR.");
		String status=scan.next();
		if(status.equalsIgnoreCase("S")) {
			return true;	
		}else if (status.equalsIgnoreCase("N")) {
			return false;
		}
		System.out.println("TENTE NOVAMENTE");
	return false;
	}	
	public void tela_removeUsuario() {

	    RelatoriosUsu relatUsu = new RelatoriosUsu();
	    Control_Usuario cont_usu = new Control_Usuario();

	    while (true) {
	        ArrayList<String> vet = new ArrayList<>();

	        System.out.println("------------- REMOÇÃO DE USUARIOS -------------");
	        System.out.println("INSIRA O TIPO DE USUARIO:\n"
	                + "1-FUNCIONARIO\n"
	                + "2-CLIENTE");

	        int tipo = scan.nextInt();

	        if (tipo == 1) {
	            vet = relatUsu.vw_Funcionario();
	            if (vet == null) continue;
	            System.out.println("0 PARA SAIR OU ID DO FUNCIONARIO:");
	        } 
	        else if (tipo == 2) {
	            vet = relatUsu.vw_Clientes();
	            if (vet == null) continue;
	            System.out.println("0 PARA SAIR OU ID DO CLIENTE:");
	        } else if(tipo == 0) {
	        	break;
	        }
	        else {
	            System.out.println("OPÇÃO INVÁLIDA!");
	            continue;
	        }

	        int idEscolhido = scan.nextInt();
	        if (idEscolhido == 0) {
	            break;
	        }

	        boolean confirmacao = tela_confir("REMOVER");
	        if (!confirmacao) {
	            return;
	        }

	       
	        String idReal = Main.estratoID(vet, idEscolhido);

	        if (idReal == null) {
	            System.out.println("ID não encontrado na lista de usuários.");
	            continue;
	        }

	        
	        boolean removido = cont_usu.removeUsuarioC(idReal);

	        if (removido) {
	            System.out.println("Usuário removido com sucesso! ID REAL: " + idReal);
	        } else {
	            System.out.println("Falha ao remover o usuário.");
	        }
	    }
	}

	public void tela_removerPecas() {
		RelatoriosPecas relatPec =new RelatoriosPecas();
		Control_Pecas cont_pecas=new Control_Pecas();
		
		while(true) {
			System.out.println("------------- REMOÇÃO DE PEÇAS -------------");
			
			
			ArrayList<String> vet=relatPec.relat_Pecas();
			System.out.print("0 SAIR OU ID DA PEÇA:");
			int id=scan.nextInt();
			
			if(id==0) {
				break;
			}else {
				boolean bol=tela_confir("REMOVER");
				if(bol==true) {
				String id_num=Main.estratoID(vet, id);
				cont_pecas.removerPecas(id_num);
				
				}else {
					return;
				}
			}
			
		}
	}
	public void tela_removeOS() {
		 RelatoriosOs relatOs=new RelatoriosOs();
		Control_Ordem_De_Serico cont_os=new Control_Ordem_De_Serico();
		
		while(true) {
			System.out.println("------------- REMOVE ORDEM DE SERVICO -------------");
			
			ArrayList<String> vet=relatOs.relat_OsCompleto();
			
			System.out.print("0 SAIR OU ID DA O.S:");
			int id=scan.nextInt();
			if(id==0) {
				break;
			}else {
				boolean bol=tela_confir("REMOVER");
				if(bol==true) {
					
					String idO=Main.estratoID(vet, id);
					cont_os.removeOS(idO);
					
				}else {
					return;
				}
				
			}
			
		}
	}
	public void relat_Usuario() {
		RelatoriosUsu relatUsu =new RelatoriosUsu();
		System.out.println("------------- RELATORIO GERAL DE USUARIOS -------------");
		relatUsu.vw_Clientes();
		relatUsu.vw_Funcionario();
	}
	public void relat_OrdemServico() {
		 RelatoriosOs relatOs=new RelatoriosOs();
		System.out.println("------------- RELATORIO GERAL DE ORDEM DE SERVIÇOS -------------");
		relatOs.relat_OsCompleto();	
	}
	public void relat_pecas() {
		RelatoriosPecas relatPec =new RelatoriosPecas();
		relatPec.relat_Pecas();
	}
	public void relat_pecasUsadas() {
		RelatoriosOs relatOs=new RelatoriosOs();
		RelatoriosPecas relatPec =new RelatoriosPecas();
		System.out.println("------------- RELATORIO PEÇAS POR OS -------------");
		ArrayList<String> vet =relatOs.relat_OsCompleto();
		System.out.print("INSIRA 0 PARA SAIR OU  ID DA O.S:");
		int num=scan.nextInt();
		if(num==0) {
			return;
		}
		String id_num=Main.estratoID(vet, num);
		relatPec.relat_pecas_usadas(id_num);
	}
	public void relat_pecasTipo() {
		RelatoriosPecas relatPec =new RelatoriosPecas();
		System.out.println("------------- RELATORIO PEÇAS POR TIPO------------");	
		relatPec.vw_pecaTipo();
	}
	public void removePecasUsadas() {
		 RelatoriosOs relatOs=new RelatoriosOs();
		RelatoriosPecas relatPec =new RelatoriosPecas();
		Control_Pecas cont_pecas=new Control_Pecas();
		
		while(true) {
		System.out.println("-----------------REMOVE DE PEÇAS USADAS-----------------");
		
		ArrayList<String> vet=relatOs.relat_OsCompleto();
		System.out.print("INSIRA ID DA O.S:");
		
		int  num=scan.nextInt();	
		if(num==0) {
			break;
		}
		String id_os=Main.estratoID(vet, num);
		
		ArrayList<String> vet2=relatPec.relat_pecas_usadas(id_os);
		System.out.print("INSIRA ID DA PEÇA A SER REMOVIDA:");
		int num2=scan.nextInt();
		System.out.print("INSIRA ID OS:");
		int num1=scan.nextInt();
		System.out.print("INSIRA QUANTIDADE:");
		int num3=scan.nextInt();
		
		boolean bol=tela_confir("REMOVER");
		if(bol==true) {
			cont_pecas.removePecasU(num1, num2, num3);
		}else {
			return;
		}
		
		}
	}
	public int tela_atualizar() {
		System.out.println("--------- UPDATE ---------");
		System.out.println("1- ATUALIZAR USUARIOS\n"
				+ "2- ATUALIZAR PEÇAS\n"
				+ "3- ATUALIZAR ORDENS DE SERVIÇO\n"
				+ "4- SAIR");
		return scan.nextInt();
	}
	public void tela_atualizarUsu() {

	    RelatoriosUsu relatUsu = new RelatoriosUsu();
	    Control_Usuario cont_usu = new Control_Usuario();

	    ArrayList<String> vet = new ArrayList<>();

	    System.out.println("INSIRA O TIPO DE USUARIO:\n"
	            + "1-FUNCIONARIO\n"
	            + "2-CLIENTE");

	    int tipo = scan.nextInt();

	    if (tipo == 1) {
	        vet = relatUsu.vw_Funcionario();
	        System.out.println("0 PARA SAIR OU ID DO FUNCIONARIO:");
	    } 
	    else if (tipo == 2) {
	        vet = relatUsu.vw_Clientes();
	        System.out.println("0 PARA SAIR OU ID DO CLIENTE:");
	    }

	    int id = scan.nextInt();
	    if (id == 0) return;

	    System.out.println("\n2- NOME\n"
	            + "3- CPF\n"
	            + "4- TELEFONE\n"
	            + "5- SENHA\n"
	            + "6- TIPO\n"
	            + "7- MOTO_MODELO\n"
	            + "8- MOTO_ANO\n"
	            + "9- MOTO_PLACA");

	    System.out.print("INSIRA A COLUNA QUE VAI MODIFICAR:");
	    int opcao = scan.nextInt();

	    String coluna = null;

	    switch (opcao) {
	        case 2 -> coluna = "nome";
	        case 3 -> coluna = "cpf";
	        case 4 -> coluna = "telefone";
	        case 5 -> coluna = "senha";
	        case 6 -> coluna = "tipo";
	        case 7 -> coluna = "moto_modelo";
	        case 8 -> coluna = "moto_ano";
	        case 9 -> coluna = "moto_placa";
	    }

	    System.out.print("INSIRA O NOVO VALOR:");
	    String novoValor = scan.next();

	    
	    String idUsuario = Main.estratoID(vet, id);

	    boolean bol = tela_confir("ATUALIZAR");

	    if (bol) {
	        cont_usu.atuli_usu(idUsuario, coluna, novoValor);
	    }
	}
	public void tela_atualizarOS() {
	    RelatoriosOs relatOs = new RelatoriosOs();
	    Control_Ordem_De_Serico cont_os = new Control_Ordem_De_Serico();     
	    ArrayList<String> vet = relatOs.relat_orcaGeral();

	    System.out.print("0 PARA SAIR OU ID DA ORDEM DE SERVIÇO: ");
	    int num = scan.nextInt();
	    if (num == 0) {
	        return;
	    }

	    System.out.println("\n2- STATUS\n"
	            + "3- ID_FUNCIONARIO\n"
	            + "4- ID_CLIENTE\n"
	            + "5- CARGA_HORARIA\n"
	            + "6- VALOR_PECAS\n"
	            + "7- VALOR_HH\n"
	            + "8- VALOR_TOTAL\n"
	            + "9- OBSERVACAO");
	    System.out.print("INSIRA A COLUNA QUE VAI MODIFICAR: ");
	    int opcao = scan.nextInt();

	   
	    String coluna = null;
	    switch (opcao) {
	        case 2 -> coluna = "status";
	        case 3 -> coluna = "id_funcionario";
	        case 4 -> coluna = "id_cliente";
	        case 5 -> coluna = "carga_horaria";
	        case 6 -> coluna = "valor_pecas";
	        case 7 -> coluna = "valor_hh";
	        case 8 -> coluna = "valor_total";
	        case 9 -> coluna = "observacao";
	        default -> {
	            System.out.println("Opção inválida!");
	            return;
	        }
	    }

	    System.out.print("INSIRA O NOVO VALOR: ");
	    String entrada = scan.next();
	    Object novoValor = null;

	    try {
	        
	        switch (coluna) {
	            case "status":
	            case "observacao":
	                novoValor = entrada;
	                break;
	            case "carga_horaria":
	                novoValor = Integer.parseInt(entrada);
	                break;
	            case "valor_pecas":
	            case "valor_hh":
	            case "valor_total":
	                novoValor = Double.parseDouble(entrada);
	                break;
	            case "id_funcionario":
	            case "id_cliente":
	                if (entrada.length() != 24) {
	                    System.out.println("ID inválido! Deve ter 24 caracteres.");
	                    return;
	                }
	                novoValor = new ObjectId(entrada); 
	                break;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Valor inválido para a coluna selecionada!");
	        return;
	    }

	    String idOS = Main.estratoID(vet, num);
	    if (idOS == null) {
	        System.out.println("ID da OS inválido!");
	        return;
	    }

	    boolean bol = tela_confir("ATUALIZAR");
	    if (bol) {
	        cont_os.atualiazarso(idOS, coluna, novoValor);
	    } else {
	        System.out.println("Atualização cancelada.");
	    }
	}

	public void tela_atualizarPecas() {
		
		RelatoriosPecas relatPec =new RelatoriosPecas();
		Control_Pecas cont_pecas=new Control_Pecas();
		
		ArrayList<String> vet = relatPec.relat_Pecas();
		
		System.out.print("0 PARA SAIR OU ID PEÇAS");
		
		
		
		int id=scan.nextInt();
		if(id==0) {
			return;
		}
		System.out.println("\n2- NOME\n"
				+ "3- VALOR_PECAS\n"
				+ "4- UNIDADE\n"
				+ "5- QUANTIDADE\n"
				+ "6- VALOR_UNIDADE\n"
				+ "7- TIPO_ELEMENTO\n");
		System.out.print("INSIRA A COLUNA QUE VAI MODIFICAR:");
		int opcao=scan.nextInt();
		
		String coluna = null;
		switch (opcao) {
	    case 2 -> coluna = "nome";
	    case 3 -> coluna = "valor_pecas";
	    case 4 -> coluna = "unidade";
	    case 5 -> coluna = "quantidade";
	    case 6 -> coluna = "valor_unidade";
	    case 7 -> coluna = "tipo_elemento";
	}
			
		System.out.print("INSIRA O NOVO VALOR:");
		String novoValor=scan.next();
		String idpecas = Main.estratoID(vet, id);
		
		boolean bol=tela_confir("ATUALIZAR");
		if(bol==true) {
			cont_pecas.atualizarpecas(idpecas, coluna, novoValor);
		}else {
			return;
		}
		
	}
}
	
	

