### 📂 Estrutura de Pastas

#### 📁 controller/
Contém as classes responsáveis pelos **endpoints da API**. Cada controller recebe as requisições HTTP, valida os dados e repassa para a camada de serviço.

#### 📁 dto/
(DTO = Data Transfer Object)  
Armazena os objetos usados para a **entrada e saída de dados**. Aqui ficam os `Request` (dados que chegam) e os `Response` (dados que são enviados de volta), evitando expor diretamente os modelos da aplicação.

#### 📁 model/
Contém as **entidades do sistema**, que representam as tabelas do banco de dados. Cada classe aqui é uma entidade mapeada com `@Entity`.

#### 📁 repository/
Responsável por **acessar e manipular os dados no banco**. As interfaces estendem `JpaRepository`, oferecendo métodos prontos como `save`, `findAll`, `deleteById`, etc.

#### 📁 service/
Aqui ficam as **regras de negócio da aplicação**. Os services recebem dados do controller, processam o que for necessário e conversam com os repositórios para buscar ou salvar informações.

## 🔍 Testes via Insomnia

Para testar a API, use o arquivo da collection do Insomnia:

📂 `insomnia/advocacia-insomnia-requests.json`

Importe no Insomnia através de:
`Application → Import → From File`
