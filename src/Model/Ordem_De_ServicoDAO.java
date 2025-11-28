package Model;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import Conexao.ConexaoMongo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class Ordem_De_ServicoDAO {

    private MongoCollection<Document> getCollection() {
        MongoDatabase db = ConexaoMongo.getDatabase();
        return db.getCollection("ordem_de_servicos");
    }

    public void insertOrdemServico(Ordem_De_Servico os) {
        try {
           
            if (os.getFuncionario() == null || os.getFuncionario().length() != 24 ||
                os.getCliente() == null || os.getCliente().length() != 24) {
                System.out.println("ERRO: ID do cliente ou funcionário inválido!");
                return;
            }

            ObjectId idFunc = new ObjectId(os.getFuncionario());
            ObjectId idCli = new ObjectId(os.getCliente());

            Document doc = new Document()
                    .append("status", (os.getStatus() != null) ? os.getStatus() : "ABERTO")
                    .append("id_funcionario", idFunc)
                    .append("id_cliente", idCli)
                    .append("carga_horaria", os.getCargaHoraria())
                    .append("valor_pecas", os.getValorPecas())
                    .append("valor_hh", os.getCustoHH())
                    .append("valor_total", os.getValorTotal())
                    .append("observacao", (os.getObservacao() != null) ? os.getObservacao() : "");

            getCollection().insertOne(doc); // insere no MongoDB

            System.out.println("OS cadastrada com sucesso!");
            System.out.println(doc.toJson());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar OS: " + e.getMessage());
        }
    }



    public void removeOrdemServico(String idOs) {
        try {
            ObjectId objectId = new ObjectId(idOs); // 24 chars hex
            DeleteResult result = getCollection().deleteOne(eq("_id", objectId));

            if (result.getDeletedCount() > 0) {
                System.out.println("OS removida com sucesso!");
            } else {
                System.out.println("Nenhuma OS encontrada com este ID!");
            }
        } catch (IllegalArgumentException invalidId) {
            System.out.println("ID inválido! O ID precisa ser um ObjectId válido.");
        } catch (Exception e) {
            System.out.println("Erro ao remover OS: " + e.getMessage());
        }
    }



    public void count_OS() {
        try {
            long total = getCollection().countDocuments();
            System.out.println("TOTAL DE ORDENS DE SERVIÇO: " + total);
        } catch (Exception e) {
            System.out.println("Erro ao contar OS: " + e.getMessage());
        }
    }
    public void atualizarOrdemServico(String idOS, String coluna, Object novoValor) {
        try {
            ObjectId objectId = new ObjectId(idOS);

            Bson filtro = eq("_id", objectId);
            Bson atualizacao = set(coluna, novoValor);

            UpdateResult resultado = getCollection().updateOne(filtro, atualizacao);

            if (resultado.getModifiedCount() > 0) {
                System.out.println("OS atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma OS encontrada com este ID.");
            }

        } catch (IllegalArgumentException invalidId) {
            System.out.println("ID inválido! O ID precisa ser um ObjectId válido.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar OS: " + e.getMessage());
        }
    }


}
