package reports;

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Conexao.ConexaoMongo;

public class RelatoriosUsu {

	public ArrayList<String> vw_Clientes() {

	    MongoDatabase db = ConexaoMongo.getDatabase();
	    MongoCollection<Document> colUsu = db.getCollection("usuarios");

	    try (MongoCursor<Document> cursor = colUsu.find(
	            new Document("tipo", "CLIENTE")
	    ).iterator()) {

	        if (!cursor.hasNext()) {
	            System.out.println("SEM CLIENTES CADASTRADOS");
	            return null;
	        }

	        int cont = 1;
	        ArrayList<String> vet = new ArrayList<>();
	        System.out.println("------------- CLIENTES CADASTRADOS -------------");

	        while (cursor.hasNext()) {

	            Document cli = cursor.next();

	            // PEGAR CORRETAMENTE O _id COMO STRING HEXADECIMAL
	            String id = cli.getObjectId("_id").toHexString();

	            String nome = cli.getString("nome");
	            String modelo = cli.getString("moto_modelo");
	            int ano = cli.getInteger("moto_ano", 0);
	            String placa = cli.getString("moto_placa");

	            System.out.println(
	                    "ID: " + cont +
	                    " | NOME: " + nome +
	                    " | MODELO: " + modelo +
	                    " | ANO: " + ano +
	                    " | PLACA: " + placa
	            );

	            vet.add(id);
	            cont++;
	        }

	        return vet;

	    } catch (Exception e) {
	        System.out.println("ERRO AO LISTAR CLIENTES: " + e.getMessage());
	        return null;
	    }
	}

	public ArrayList<String> vw_Funcionario() {

	    MongoDatabase db = ConexaoMongo.getDatabase();
	    MongoCollection<Document> colUsu = db.getCollection("usuarios");

	    try (MongoCursor<Document> cursor = colUsu.find(
	            new Document("tipo", "FUNCIONARIO")
	    ).iterator()) {

	        if (!cursor.hasNext()) {
	            System.out.println("SEM FUNCIONÁRIOS CADASTRADOS");
	            return null;
	        }

	        int cont = 1;
	        ArrayList<String> vet = new ArrayList<>();

	        System.out.println("------------- FUNCIONÁRIOS CADASTRADOS -------------");

	        while (cursor.hasNext()) {

	            Document func = cursor.next();

	            // PEGAR CORRETAMENTE O _id
	            String id = func.getObjectId("_id").toHexString();

	            System.out.println(
	                "ID: " + cont +
	                " | NOME: " + func.getString("nome")
	            );

	            vet.add(id);
	            cont++;
	        }

	        return vet;

	    } catch (Exception e) {
	        System.out.println("ERRO AO LISTAR FUNCIONÁRIOS: " + e.getMessage());
	        return null;
	    }
	}



}
