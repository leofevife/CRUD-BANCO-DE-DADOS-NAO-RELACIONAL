package Conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class TesteMongo {

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = client.getDatabase("SGM");

            System.out.println("Conexão realizada com sucesso ao MongoDB!");

        } catch (Exception e) {
            System.out.println("Erro ao conectar no MongoDB:");
            e.printStackTrace();
        	}
    	}
    }