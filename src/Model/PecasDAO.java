package Model;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import Conexao.ConexaoMongo;

public class PecasDAO {

    private final MongoDatabase database;
    private final MongoCollection<Document> colecaoPecas;
    private final MongoCollection<Document> colecaoPecasUsadas;

    public PecasDAO() {
        this.database = ConexaoMongo.getDatabase();
        this.colecaoPecas = database.getCollection("pecas");
        this.colecaoPecasUsadas = database.getCollection("pecas_usadas");
    }

    public void insertPecas(ArrayList<Pecas> pecas) {

        for (Pecas pc : pecas) {

            Document doc = new Document()
                    .append("nome", pc.getNomePeca())
                    .append("valor_pecas", pc.getValorPecas())
                    .append("unidade", pc.getUnid())
                    .append("quantidade", pc.getQuat_Pecas())
                    .append("valor_unidade", pc.getValor_unid())
                    .append("tipo_elemento", pc.getTipo_elemento());

            if (pc instanceof PecasEletricas) {
                doc.append("caract_elemento", ((PecasEletricas) pc).getVoltagem());
            } else if (pc instanceof PecasMecanica) {
                doc.append("caract_elemento", ((PecasMecanica) pc).getMaterial());
            }

            colecaoPecas.insertOne(doc);
        }

    }

    public void insertPecasUsadas(String id_os, String id_pecas, int quant_pecas) {

        Document doc = new Document()
                .append("num_os", id_os)
                .append("id_pecas", id_pecas)
                .append("quant_pecas", quant_pecas);

        colecaoPecasUsadas.insertOne(doc);

        System.out.println("Registro de peças usadas inserido na OS " + id_os);
    }

    public void proc_RemovePecas(String idPecas) {
        try {
            ObjectId objectId = new ObjectId(idPecas);
            DeleteResult result = colecaoPecas.deleteOne(Filters.eq("_id", objectId));

            if (result.getDeletedCount() > 0) {
                System.out.println("Peça removida com sucesso!");
            } else {
                System.out.println("Nenhuma peça encontrada com este ID.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido! O ID precisa ser um ObjectId válido.");
        } catch (Exception e) {
            System.out.println("Erro ao remover peça: " + e.getMessage());
        }
    }


    
    
    
    public void removePecaUsada(int numOS, int idPeca, int quant) {

        Document filtro = new Document()
                .append("num_os", numOS)
                .append("id_pecas", idPeca)
                .append("quant_pecas", quant);

        DeleteResult result = colecaoPecasUsadas.deleteOne(filtro);

        if (result.getDeletedCount() > 0) {
            System.out.println("Peça usada removida com sucesso!");
        } else {
            System.out.println("Nenhum registro encontrado.");
        }
    }

    public void count_pecas() {
        long total = colecaoPecas.countDocuments();
        System.out.println("TOTAL DE PEÇAS: " + total);
    }

    public void atualizarPeca(String idPeca, String coluna, String novoValor) {
        try {
            
            ObjectId objectId = new ObjectId(idPeca);

            Document filtro = new Document("_id", objectId);
            Document update = new Document("$set", new Document(coluna, novoValor));

            UpdateResult result = colecaoPecas.updateOne(filtro, update);

            if (result.getMatchedCount() == 0) {
                System.out.println("Nenhuma peça encontrada com esse ID!");
            } 
            else if (result.getModifiedCount() == 0) {
                System.out.println("O valor novo é igual ao antigo. Nada foi alterado.");
            } 
            else {
                System.out.println("Peça atualizada com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar peça: " + e.getMessage());
        }
    }

}
