package Conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexaoMongo {

    // URL do servidor MongoDB
    private static final String URI = "mongodb://localhost:27017";

    // Nome do banco que você quer acessar
    private static final String DATABASE_NAME = "SGM";

    // Cliente MongoDB (singleton)
    private static MongoClient mongoClient = null;

    // Construtor privado (evita instanciar)
    private ConexaoMongo() {}

    // Retorna a conexão (MongoClient)
    public static MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
            
        }
        return mongoClient;
    }

    // Retorna o Database
    public static MongoDatabase getDatabase() {
        return getClient().getDatabase(DATABASE_NAME);
    }

    // Fecha a conexão (se quiser usar no final da aplicação)
    public static void fecharConexao() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
           
        }
    }
}

