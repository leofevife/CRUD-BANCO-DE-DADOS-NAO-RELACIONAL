package Model;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import Conexao.ConexaoMongo;

public class UsuarioDAO {

    private final MongoDatabase database;
    private final MongoCollection<Document> colecaoUsuarios;
    private final MongoCollection<Document> colecaoOS;
    private final MongoCollection<Document> colecaoPecasUsadas;

    public UsuarioDAO() {
        this.database = ConexaoMongo.getDatabase();
        this.colecaoUsuarios = database.getCollection("usuarios");
        this.colecaoOS = database.getCollection("ordem_de_servicos");
        this.colecaoPecasUsadas = database.getCollection("pecas_usadas");
    }

    public void insertFuncionario(Funcionario func) {

        Document doc = new Document()
                .append("nome", func.getNome())
                .append("cpf", func.getCpf())
                .append("telefone", func.getTelefone())
                .append("senha", func.getSenha())
                .append("tipo", func.getTipo())
                .append("salario", func.getSalario())
                .append("moto_modelo", null)
                .append("moto_ano", 0)
                .append("moto_placa", null);

        colecaoUsuarios.insertOne(doc);

        System.out.println("USUÁRIO FUNCIONÁRIO CADASTRADO COM SUCESSO!");
    }

    public void insertCliente(Cliente cli) {

        Document doc = new Document()
                .append("nome", cli.getNome())
                .append("cpf", cli.getCpf())
                .append("telefone", cli.getTelefone())
                .append("senha", cli.getSenha())
                .append("tipo", cli.getTipo())
                .append("salario", 0)
                .append("moto_modelo", cli.getMoto_modelo())
                .append("moto_ano", cli.getMoto_Ano())
                .append("moto_placa", cli.getMoto_Placa());

        colecaoUsuarios.insertOne(doc);

        System.out.println("USUÁRIO CLIENTE CADASTRADO COM SUCESSO!");
    }


    private Usuario returnObj(Document doc) {

        String tipo = doc.getString("tipo");

        if (tipo.equals("CLIENTE")) {
            Cliente cli = new Cliente();
            cli.setId(doc.getObjectId("_id").hashCode());
            cli.setNome(doc.getString("nome"));
            cli.setCpf(doc.getString("cpf"));
            cli.setTelefone(doc.getString("telefone"));
            cli.setSenha(doc.getString("senha"));
            cli.setTipo(tipo);
            cli.setMoto_Ano(doc.getInteger("moto_ano", 0));
            cli.setMoto_modelo(doc.getString("moto_modelo"));
            cli.setMoto_Placa(doc.getString("moto_placa"));
            return cli;

        } else {
            Funcionario func = new Funcionario();
            func.setId(doc.getObjectId("_id").hashCode());
            func.setNome(doc.getString("nome"));
            func.setCpf(doc.getString("cpf"));
            func.setTelefone(doc.getString("telefone"));
            func.setSenha(doc.getString("senha"));
            func.setTipo(tipo);
            func.setSalario(doc.getDouble("salario").floatValue());
            return func;
        }
    }

    public void proc_Orca_P_usu(int id_os) {

        Document filtro = new Document("_id_os", id_os);
        Document os = colecaoOS.find(filtro).first();

        if (os == null) {
            System.out.println("Nenhum orçamento encontrado!");
            return;
        }

        System.out.println(
                "ORÇAMENTO: " + os.getInteger("_id_os") +
                " | CLIENTE: " + os.getInteger("id_cliente") +
                " | MODELO MOTO: " + os.getString("moto_modelo") +
                " | PLACA: " + os.getString("moto_placa") +
                " | VALOR PEÇAS: " + os.getDouble("valor_pecas") +
                " | MÃO DE OBRA: " + os.getDouble("mao_de_obra") +
                " | TOTAL: " + os.getDouble("valor_total")
        );
    }


    public Ordem_De_Servico relat_OrcaOBJ(int id_os) {

        Document os = colecaoOS.find(new Document("_id_os", id_os)).first();

        if (os == null) {
            System.out.println("ORDENS NÃO ENCONTRADA...");
            return null;
        }

        Ordem_De_Servico obj = new Ordem_De_Servico();
        obj.setId(id_os);
        obj.setStatus(os.getString("status"));
        obj.setFuncionario(os.getInteger("id_funcionario"));
        obj.setCliente(os.getInteger("id_cliente"));
        obj.setCargaHoraria(os.getInteger("carga_horaria"));
        obj.setValorPecas(os.getDouble("valor_pecas").floatValue());
        obj.setCustoHH(os.getDouble("custo_hh").floatValue());
        obj.setValorTotal(os.getDouble("valor_total").floatValue());
        obj.setObservacao(os.getString("observacao"));

        return obj;
    }

    public Usuario return_usuario(String id) {

        try {
            Document doc = colecaoUsuarios.find(
                    new Document("_id_hash", id)
            ).first();

            if (doc != null)
                return returnObj(doc);

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null;
    }
    
    public void removeUsuario(String id_num) {

        MongoCursor<Document> cursorOS = colecaoOS.find(
                new Document("$or",
                        java.util.List.of(
                                new Document("id_cliente", id_num),
                                new Document("id_funcionario", id_num)
                        )
                )
        ).iterator();

        while (cursorOS.hasNext()) {
            Document os = cursorOS.next();
            int idOS = os.getInteger("_id_os");

            colecaoPecasUsadas.deleteMany(new Document("num_os", idOS));
        }

      
        colecaoOS.deleteMany(
                new Document("$or",
                        java.util.List.of(
                                new Document("id_cliente", id_num),
                                new Document("id_funcionario", id_num)
                        )
                )
        );

        
        var result = colecaoUsuarios.deleteOne(new Document("_id_hash", id_num));

        if (result.getDeletedCount() > 0) {
            System.out.println("USUÁRIO REMOVIDO COM SUCESSO!");
        } else {
            System.out.println("Nenhum usuário encontrado com esse ID.");
        }
    }
    public boolean removeUsuarioC(String idUsuario) {	

        try {
            ObjectId objectId = new ObjectId(idUsuario);
            DeleteResult result = colecaoUsuarios.deleteOne(Filters.eq("_id", objectId));

            if (result.getDeletedCount() > 0) {
                System.out.println("Usuário removido com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum usuário encontrado com este ID.");
                return false;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido! O ID precisa ser um ObjectId válido.");
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }


    public void count_usu() {
        long total = colecaoUsuarios.countDocuments();
        System.out.println("TOTAL DE USUÁRIOS: " + total);
    }

    public void updat_usu(String id, String campo, String novoValor) {

    	    if (id == null || id.isEmpty()) {
    	        System.out.println("ERRO: ID está nulo ou vazio!");
    	        return;
    	    }

    	    try {
    	        MongoDatabase db = ConexaoMongo.getDatabase();
    	        MongoCollection<Document> col = db.getCollection("usuarios");

    	        ObjectId objectId = new ObjectId(id);

    	        Document filtro = new Document("_id", objectId);
    	        Document update = new Document("$set", new Document(campo, novoValor));

    	        col.updateOne(filtro, update);

    	        System.out.println("Atualizado com sucesso!");

    	    } catch (Exception e) {
    	        System.out.println("Erro ao atualizar: " + e.getMessage());
    	    }
    	}




}
	