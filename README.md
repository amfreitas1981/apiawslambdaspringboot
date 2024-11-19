# AWS Lambda Function with Spring Boot

![Java Version](https://img.shields.io/badge/Java-17-blue)
![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-2.7.0-green)
![Tests Executed](https://img.shields.io/badge/Tests-Passed-brightgreen)

**Descrição:** Este projeto demonstra a integração entre AWS Lambda e Spring Boot para a execução de funções serverless com um contexto de aplicação Spring.


## 🛠 Estrutura do Projeto

- **`MyLambdaFunction`**: Classe principal que implementa a interface `RequestHandler` da AWS Lambda, inicializa o contexto do Spring e delega a lógica para o controlador.
- **`MyController`**: Controlador responsável por tratar a lógica de negócios e retornar a resposta.
- **`MockContext`**: Implementação mock da interface `Context` da AWS Lambda, utilizada para simular o contexto em testes.
- **Testes**:
    - **`MyLambdaFunctionTest`**: Testa os métodos principais da função Lambda.
    - **`MockContextTest`**: Testa os métodos da classe `MockContext`.

---

## ⚙️ Configuração

### Pré-requisitos

- **Java 11** ou superior
- **Maven** (opcional para build manual)
- **AWS SDK** (pré-configurado em sua máquina, se necessário)
- **Spring Boot** (incluído nas dependências do projeto)

---

## 🚀 Executando a Aplicação Localmente

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```
2. Execute a aplicação:
   ```
   mvn spring-boot:run
   ```
3. O método `main` na classe `MyLambdaFunction` será executado e você verá os seguintes resultados no console:
- Logs do `MockContext`.
- A resposta gerada pelo método `handleRequest`.


## 🧪 Executando os Testes

### Ferramentas Recomendadas
- IntelliJ IDEA ou Eclipse (com suporte a JUnit 5)
- Maven (para executar os testes via linha de comando)

### Execução dos Testes
1. Para executar todos os testes, use:
   ```
   mvn test
   ``` 
2. Testes principais:
- `MyLambdaFunctionTest`: Verifica a integração entre o contexto Lambda e o controlador do Spring.
- `MockContextTest`: Valida a implementação mock do contexto da AWS Lambda.

3. Verifique os resultados dos testes no terminal. Todos os testes devem ser aprovados.


## 📜 Documentação das Classes

### `MyLambdaFunction`
- **Descrição:** Inicializa o contexto Spring e processa a entrada da função Lambda.
- **Funções principais:**
  - `main(String[] args)`: Inicializa o contexto Spring e simula uma execução Lambda.
  - `handleRequest(Object input, Context context)`: Trata a entrada e delega a lógica para o controlador.

### `MyController`
- **Descrição:** Controlador responsável por processar a lógica e gerar uma resposta.
- **Função principal:**
    - `handleRequest(Object input)`: Gera uma resposta com uma mensagem customizada, incluindo a porta do servidor.

### `MockContext`
- **Descrição:** Implementação mock da interface `Context` da AWS Lambda, usada em testes para simular o contexto da função.
- **Funções principais:**
    - Métodos como `getAwsRequestId`, `getLogger` e outros, retornam valores simulados.


## 🌐 Exemplo de Uso

O projeto pode ser integrado com o AWS Lambda em produção. Para isso:
1. Compile o projeto como um **arquivo JAR**:
   ```
   mvn package
   ``` 
2. Faça o upload do JAR para a AWS Lambda e configure o handler:
   ```
   com.example.demo.MyLambdaFunction::handleRequest
   ```
3. Simular uma entrada JSON e teste a função diretamente no console da AWS.


## 📚 Referências

- [Documentação Oficial do AWS Lambda](https://docs.aws.amazon.com/lambda/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Mockito Documentation](https://site.mockito.org/)

---


## 👨‍💻 Autor

- Nome do Desenvolvedor: Alexandre Freitas
- Contato: [seu.email@dominio.com]

---


## 📝 Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

### O que está incluído?
1. **Estrutura do Projeto**: Breve explicação de cada classe e seus propósitos.
2. **Configuração e Execução**: Instruções para configurar e executar localmente.
3. **Testes**: Orientações para executar os testes, incluindo as ferramentas recomendadas.
4. **Documentação Detalhada**: Descrição das funções principais das classes.
5. **Exemplo de Uso**: Orientações para integrar o projeto com o AWS Lambda.
6. **Referências**: Links úteis para aprofundar o conhecimento.
7. **Seção do Autor**: Para adicionar seus dados de contato.
8. **Licença**: Informação sobre a licença do projeto.

