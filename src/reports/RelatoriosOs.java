package reports;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Conexao.ConexaoMongo;

public class RelatoriosOs {

	public ArrayList<String> relat_OsCompleto() {
	    ArrayList<String> vetor = new ArrayList<String>();
	    
	    MongoDatabase db = ConexaoMongo.getDatabase();
	    MongoCollection<Document> colOS = db.getCollection("ordem_de_servicos");

	    try (MongoCursor<Document> cursor = colOS.find().iterator()) {

	    	if (!cursor.hasNext()) {
	    	    System.out.println("SEM OS !!!");
	    	    return vetor; 
	    	}

	        int cont = 1;

	        while (cursor.hasNext()) {
	            Document os = cursor.next();

	            Object idClienteObj = os.get("id_cliente");
	            String idCliente = (idClienteObj != null) ? idClienteObj.toString() : "N/D";

	            Integer motoAno = os.getInteger("moto_ano", 0);
	            Double valorPecas = os.getDouble("valor_pecas");
	            Double valorHH = os.getDouble("valor_hh");
	            Double valorTotal = os.getDouble("valor_total");

	            System.out.println(
	                "ID: " + cont +
	                " | CLIENTE: " + idCliente +
	                " | MOTO MODELO: " + os.getString("moto_modelo") +
	                " | ANO: " + motoAno +
	                " | PLACA: " + os.getString("moto_placa") +
	                " | VALOR PEÇAS: " + valorPecas +
	                " | VALOR MÃO DE OBRA: " + valorHH +
	                " | VALOR TOTAL: " + valorTotal
	            );
	            String idOs = os.getObjectId("_id").toHexString();	 
	            vetor.add(idOs);
	            cont++;
	        }

	    } catch (Exception e) {
	        System.out.println("ERRO AO BUSCAR OS: " + e.getMessage());
	    }

	    return vetor;
	}



	public ArrayList<String> relat_orcaGeral() {

	    ArrayList<String> vet = new ArrayList<>();

	    MongoDatabase db = ConexaoMongo.getDatabase();
	    MongoCollection<Document> colOS = db.getCollection("ordem_de_servicos");
	    MongoCollection<Document> colUser = db.getCollection("usuarios");

	    try (MongoCursor<Document> cursor = colOS.find().iterator()) {

	        if (!cursor.hasNext()) {
	            System.out.println("SEM OS ABERTA");
	            return vet;
	        }

	        int cont = 1;

	        while (cursor.hasNext()) {
	            Document os = cursor.next();

	            String idOs = os.getObjectId("_id").toHexString();
	            String status = os.getString("status");
	            int cargaHoraria = os.getInteger("carga_horaria", 0);
	            double valorPecas = os.getDouble("valor_pecas") != null ? os.getDouble("valor_pecas") : 0;
	            double valorHH = os.getDouble("valor_hh") != null ? os.getDouble("valor_hh") : 0;
	            double valorTotal = os.getDouble("valor_total") != null ? os.getDouble("valor_total") : 0;
	            String observacao = os.getString("observacao") != null ? os.getString("observacao") : "";

	            // Buscar funcionário
	            String nomeFuncionario = "N/D";
	            String idFuncionarioStr = null;
	            Object idFuncionarioObj = os.get("id_funcionario");
	            if (idFuncionarioObj != null) {
	                idFuncionarioStr = idFuncionarioObj.toString()
	                        .replace("ObjectId(", "")
	                        .replace(")", "")
	                        .replace("\"", "");
	                Document funcionario = colUser.find(new Document("_id", new ObjectId(idFuncionarioStr))).first();
	                if (funcionario != null) {
	                    nomeFuncionario = funcionario.getString("nome");
	                }
	            }

	            // Buscar cliente
	            String nomeCliente = "N/D";
	            String modelo = "N/D";
	            int ano = 0;
	            String placa = "N/D";
	            String idClienteStr = null;
	            Object idClienteObj = os.get("id_cliente");
	            if (idClienteObj != null) {
	                idClienteStr = idClienteObj.toString()
	                        .replace("ObjectId(", "")
	                        .replace(")", "")
	                        .replace("\"", "");
	                Document cliente = colUser.find(new Document("_id", new ObjectId(idClienteStr))).first();
	                if (cliente != null) {
	                    nomeCliente = cliente.getString("nome");
	                    modelo = cliente.getString("moto_modelo");
	                    ano = cliente.getInteger("moto_ano", 0);
	                    placa = cliente.getString("moto_placa");
	                }
	            }

	            System.out.println("OS " + cont + ":");	           
	            System.out.println("status: " + status);
	            System.out.println("Funcionário: " + nomeFuncionario + " | ID: " + (idFuncionarioStr != null ? idFuncionarioStr : "null"));
	            System.out.println("Cliente: " + nomeCliente + " | ID: " + (idClienteStr != null ? idClienteStr : "null"));
	            System.out.println("MOTO: " + modelo + " | ANO: " + ano + " | PLACA: " + placa);
	            System.out.println("Carga Horária: " + cargaHoraria);
	            System.out.println("Valor Peças: " + valorPecas);
	            System.out.println("Valor HH: " + valorHH);
	            System.out.println("Valor Total: " + valorTotal);
	            System.out.println("Observação: " + observacao);
	            System.out.println("-----------------------------------");

	            vet.add(idOs);
	            cont++;
	        }

	    } catch (Exception e) {
	        System.out.println("ERRO NA BUSCA: " + e.getMessage());
	    }

	    return vet;
	}


}
