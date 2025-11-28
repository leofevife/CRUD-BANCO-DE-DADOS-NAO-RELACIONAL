package reports;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Conexao.ConexaoMongo;
import Model.Pecas;

public class RelatoriosPecas {

    public ArrayList<String> vw_pecaTipo() {

        MongoDatabase db = ConexaoMongo.getDatabase();
        MongoCollection<Document> colPecas = db.getCollection("pecas");

        MongoCursor<Document> cursor = colPecas.aggregate(
            java.util.Arrays.asList(
                new Document("$group", new Document("_id", "$tipo_elemento")
                        .append("total_estoque", new Document("$sum", "$quantidade"))
                        .append("unidade", new Document("$first", "$unidade"))
                        .append("valor_medio", new Document("$avg", "$valor_pecas"))
                )
            )
        ).iterator();

        if (!cursor.hasNext()) {
            System.out.println("SEM PEÇAS DISPONÍVEIS");
            return null;
        }

        ArrayList<String> vet = new ArrayList<>();
        int cont = 1;

        System.out.println("------------- PEÇAS POR TIPO -------------");

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            
            // PADRÃO: ID COMO STRING
            String id = String.valueOf(doc.get("_id"));

            System.out.println(
                "TIPO DE ELEMENTO: " + cont +
                " | ESTOQUE UNI: " + doc.getInteger("total_estoque") +
                " " + doc.getString("unidade") +
                " | VALOR MÉDIO: " + doc.getDouble("valor_medio")
            );

            vet.add(id);
            cont++;
        }

        cursor.close();
        return vet;
    }

    public ArrayList<String> relat_Pecas() {

        MongoDatabase db = ConexaoMongo.getDatabase();
        MongoCollection<Document> colPecas = db.getCollection("pecas");

        MongoCursor<Document> cursor = colPecas.find(
            new Document("quantidade", new Document("$gt", 0))
        ).iterator();

        if (!cursor.hasNext()) {
            System.out.println("SEM PEÇA CADASTRADA !!!");
            return null;
        }
        
        int cont = 1;
        ArrayList<String> vet = new ArrayList<>();
        
        System.out.println("------------------- LISTA DE PEÇAS -------------------");

        while (cursor.hasNext()) {
            Document doc = cursor.next();
           
            Pecas peca = new Pecas(
                doc.getString("nome"),
                doc.getDouble("valor_pecas").floatValue(),
                doc.getString("unidade"),
                doc.getInteger("quantidade"),
                doc.getDouble("valor_unidade").floatValue(),
                doc.getString("tipo_elemento")
            );

            System.out.println("ID=" + cont + " " + peca.toString());
            String id = String.valueOf(doc.get("_id"));
            System.out.println(cont);
            vet.add(id);
            cont++;
            
        }

        cursor.close();
		return vet;
    }

    public ArrayList<String> relat_pecas_usadas(String id_os) {

        MongoDatabase db = ConexaoMongo.getDatabase();
        
        MongoCollection<Document> colPecasUsadas = db.getCollection("pecas_usadas");
        MongoCollection<Document> colPecas = db.getCollection("pecas");

        ObjectId osId;
        
        int cont = 1;
        ArrayList<String> vet = new ArrayList<>();

        try {
            osId = new ObjectId(id_os);
        } catch (Exception e) {
            System.out.println("ID de OS inválido: " + id_os);
            return null;
        }

        MongoCursor<Document> cursor = colPecasUsadas.find(
            new Document("num_os", osId)
        ).iterator();

        System.out.println("------------- INSUMOS DA OS " + cont+ " -------------");

        if (!cursor.hasNext()) {
            System.out.println("Nenhuma peça usada encontrada para esta OS.");
            return null;
        }

   
        while (cursor.hasNext()) {

            Document usado = cursor.next();
            ObjectId idPeca = usado.getObjectId("id_pecas");

            
            Document peca = colPecas.find(new Document("_id", idPeca)).first();

            if (peca == null) {
                System.out.println("Peça não encontrada: " + idPeca);
                continue;
            }

            System.out.println(              
                " | NOME PEÇA: " + peca.getString("nome") +
                " | QUANTIDADE: " + usado.getInteger("quantidade") +
                " | CUSTO: " + usado.getDouble("custo")
            );
            vet.add(id_os);
            cont++;
        }

        cursor.close();
        return vet;
    }


}
