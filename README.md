# 🧠 [Orça.Ai](http://xn--ora-3la.ai/)

[**Orça.Ai**](http://xn--ora-3la.ai/) é um sistema web para gerenciamento de pedidos de orçamento de serviços digitais, como criação de sites, sistemas, aplicativos, etc.

Ideal pra quem quer organizar os pedidos dos clientes de forma prática, rápida e automatizada!

## 🚀 Funcionalidades

- 📬 Cadastro de pedidos de orçamento
- 🔍 Listagem com paginação
- ✏️ Atualização de status do orçamento
- ❌ Remoção de pedidos
- 📤 Exportação para `.csv` (Excel/Google Sheets)
- 📥 Importação de pedidos a partir de um arquivo `.csv`

## 🛠️ Tecnologias

- **Backend**: Java 21, Spring Boot
- **Banco de Dados**: PostgreSQL
- **Frontend**: (em construção) — será feito com Bootstrap/Tailwind
- **Gerenciador de DB**: DBeaver
- **IDE**: VS Code

## 🔧 Como rodar

1. Clone o projeto:
    
    ```bash
    git clone <https://github.com/seu-usuario/orca-ai.git>
    
    ```
    
2. Configure o banco de dados PostgreSQL e atualize o `application.properties:`
    
    ```java
    spring.datasource.url=jdbc:postgresql://localhost:5432/orcamentos
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```
    
3. Rode o projeto:
    
    ```java
    ./mvnw spring-boot:run
    ```
    
4. Teste as rotas via Postman ou Insomnia.

## 📁 Endpoints principais

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | /orcamentos | Lista todos os orçamentos |
| POST | /orcamentos | Cria novo orçamento |
| PUT | /orcamentos/{id} | Atualiza o status do orçamento |
| DELETE | /orcamentos/{id} | Remove orçamento por ID |
| GET | /orcamentos/export | Exporta orçamentos em formato CSV |
| POST | /orcamentos/import | Importa orçamentos via CSV |

## 💡 Futuras melhorias

- Autenticação com Spring Security
- Dashboard com métricas
- Integração com Google Sheets via API
- Envio automático de e-mails para novos pedidos
- Desenvolvimento do frontend
