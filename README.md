# 🛠 SGM - Sistema de Gestão de Manutenção

Sistema de Gestão de Manutenção (SGM) desenvolvido em **Java (JDK 21)** com **MongoDB**, utilizando o padrão **MVC (Model–View–Controller)** e **DAO** para persistência de dados.

O projeto tem como objetivo **gerenciar ordens de serviço, clientes, usuários, peças e relatórios** de forma modular e organizada, seguindo boas práticas de desenvolvimento orientado a objetos e utilizando um banco de dados NoSQL orientado a documentos.

## 📜 Módulos e Responsabilidades

| Módulo | Função |
| :--- | :--- |
| **Conexao** | Gerencia a conexão com o cluster/banco MongoDB via **MongoDB Java Driver**. |
| **Model** | Contém as classes das entidades e suas DAOs adaptadas para manipulação de **Documentos (BSON)**. |
| **Controller** | Faz o controle das operações de CRUD, intermediando o Model e o View. |
| **Reports** | Responsável por gerar relatórios (listagens, ordens de serviço, etc.) consultando as coleções. |
| **View** | Camada de interface — interage com o usuário via console ou interface simples. |
| **Principal** | Classe principal (Main.java) que inicia o sistema. |
| **Diagramas** | Diagramas de classes e modelo de documentos do banco. |

## 🧠 Padrão de Arquitetura

O sistema segue o padrão MVC (Model–View–Controller). Diferente da versão relacional, a camada DAO agora persiste objetos convertendo-os para **Documentos** (`org.bson.Document`) em coleções do MongoDB, dispensando o uso de tabelas rígidas e scripts SQL.

## 🗂️ Estrutura do Projeto

```text
SGM/
│
├── Diagramas/
│   ├── Diagrama de Coleções MongoDB
│   └── Diagrama de Classes
│
├── lib/
│   └── (Drivers do MongoDB e dependências .jar)
│
├── fonte/
│   ├── Conexao/
│   │   └── Conexao.java  
│   │
│   ├── Model
│   │   ├── Cliente.java
│   │   ├── Funcionario.java
│   │   ├── Ordem_De_Servico.java
│   │   ├── Ordem_De_ServicoDAO.java
│   │   ├── Pecas.java
│   │   ├── PecasDAO.java
│   │   ├── Usuario.java
│   │   └── UsuarioDAO.java
│   │
│   ├── controller/ 
│   │   ├── Control_Ordem_De_Serico.java
│   │   ├── Control_Pecas.java
│   │   ├── Control_Usuario.java
│   │
│   ├── reports/ 
│   │   ├── RelatoriosOs.java
│   │   ├── RelatoriosPecas.java
│   │   ├── RelatoriosUsu.java
│   │
│   ├── view/ 
│   │   └── Tela.java
│   │
│   └── principal/
│       └── Main.java # Classe principal de inicialização
│
└── module-info.java
```

## ⚙️ Requisitos do Ambiente (Linux)

Antes de rodar o sistema, verifique se os seguintes componentes estão instalados:

| Dependência | Versão recomendada | Verificar instalação |
| :--- | :--- | :--- |
| **Java JDK** | 21 ou superior | `java -version` |
| **MongoDB Server** | 6.0 ou superior | `mongod --version` |
| **Git** | 2.0+ | `git --version` |
| **Eclipse IDE ou VS Code** | Com extensão Java | - |

## 🧩 Configuração do Banco de Dados

Diferente do SQL, o MongoDB cria o banco e as coleções automaticamente na primeira inserção de dados. 

1. **Inicie o serviço do MongoDB** (caso não esteja rodando):
   ```bash
   sudo systemctl start mongod
   ```

2. **Verifique o status:**
   ```bash
   sudo systemctl status mongod
   ```

*Nota: Não é necessário rodar scripts de criação de tabelas (CREATE TABLE) ou Triggers, pois o esquema é flexível.*

## 🔌 Configuração da Conexão (Conexao.java)

Edite o arquivo `fonte/Conexao/Conexao.java` para apontar para sua instância local ou Atlas do MongoDB:

```java
// Exemplo de configuração
private static final String CONNECTION_STRING = "mongodb://localhost:27017";
private static final String DATABASE_NAME = "SGM";
```

## 🚀 Execução do Projeto (via Terminal Linux)

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seuusuario/SGM.git
   cd SGM/fonte
   ```

2. **Compile todas as classes:**
   *Certifique-se de que os `.jar` do driver do MongoDB (ex: `mongodb-driver-sync`, `bson`, `mongodb-driver-core`) estejam na pasta `../lib/`.*

   ```bash
   javac -d ../bin -cp ".:../lib/*" */*.java */*/*.java
   ```

3. **Execute o programa principal:**
   ```bash
   java -cp "../bin:../lib/*" principal.Main
   ```

## 👨‍💻 Autores

* Bruno Oliveira Duarte
* Larrisa Moraes de Jesus
* Leo Fernandes

**Projeto acadêmico de banco de dados | Estudantes de Ciências da Computação**
