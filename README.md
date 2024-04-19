
# API REST para cálculo de folha de pagamento

Trabalho apresentado como requisito parcial de conclusão da disciplina de Engenharia de Software: Análise e Projeto de Software, do curso de Engenharia de Software do Centro Universitário Fundação Assis Gurgacz.


## Tabela de Conteúdos

* [Levantamento de Requisitos](#levantamento-de-requisitos)
* [Visão Geral](#visão-geral)
* [Instalação e Execução](#instalação-e-execução)
* [Uso](#uso)


## Levantamento de Requisitos
O levantamento de requisitos para esta API foi elaborado e está disponível para consulta no seguinte documento: [Levantamento de Requisitos](https://docs.google.com/document/d/1d7l1_d9jYhPvNlm6xPeDXi1dnitsLfw9q6Jw3shaoIY/edit?usp=sharing).

Este documento contém informações detalhadas sobre os requisitos funcionais e não funcionais da API, incluindo as especificações técnicas, casos de uso, e demais aspectos importantes para o desenvolvimento e utilização da aplicação.

Recomendamos a leitura cuidadosa do levantamento de requisitos antes de utilizar a API, pois isso fornecerá uma compreensão abrangente das funcionalidades, restrições e expectativas associadas ao sistema.

## Visão Geral

A Folha de Pagamento API permite calcular os salários dos funcionários com base em uma variedade de parâmetros, incluindo horas trabalhadas, salário base, adições e deduções. Ao utilizar esta API, as empresas podem simplificar o processo de folha de pagamento, reduzindo erros e aumentando a eficiência.


## Instalação e Execução

Para utilizar esta API, certifique-se de ter instalado os seguintes pré-requisitos:

- Java 17: Certifique-se de ter o JDK (Java Development Kit) versão 17 ou superior instalado em seu sistema. Você pode fazer o download e instalar o JDK mais recente no site oficial da Oracle ou de outras fontes confiáveis.
- Maven: Esta API é construída utilizando Maven como gerenciador de dependências. Verifique se você tem o Maven instalado em sua máquina. Se não, você pode baixá-lo e instalá-lo facilmente seguindo as instruções fornecidas no site oficial do Maven.
- Banco de Dados MySQL: A API é configurada para usar um banco de dados MySQL por padrão. Certifique-se de ter um servidor MySQL instalado e em execução em sua máquina. No entanto, as configurações do projeto podem ser facilmente modificadas para se adequarem a outros bancos de dados, se necessário.
- Postman: Recomendamos o uso do Postman para fazer requisições à API e testar suas funcionalidades. O Postman é uma ferramenta popular para testes de API e pode ser baixado gratuitamente do site oficial do Postman.

Após garantir que todos os pré-requisitos estejam instalados corretamente, siga as instruções no próximo tópico para configurar e executar a API em seu ambiente local.


## Uso

Esta API segue o paradigma REST, portanto, toda interação com a aplicação é realizada através do Postman ou de outras ferramentas similares para testes de API. Existem 18 rotas disponíveis para gerenciar dados de funcionários e calcular a folha de pagamento deles.

Para começar a utilizar a API, siga estas etapas:

1. Configuração do Ambiente: Certifique-se de ter configurado corretamente seu ambiente local, incluindo a instalação dos pré-requisitos mencionados na seção de Instalação.
2. Importação da Coleção no Postman: Faça o download da coleção de requisições da API, que está disponível no formato JSON ou XML. Em seguida, importe a coleção para o Postman, o que criará automaticamente todas as requisições necessárias.
3. Exploração das Rotas: Explore as 18 rotas disponíveis na API para gerenciar dados de funcionários e calcular a folha de pagamento. Cada rota tem uma função específica, como criar um novo funcionário, atualizar informações, listar funcionários, ou calcular a folha de pagamento.
4. Envio de Requisições: Utilize o Postman para enviar requisições HTTP para a API de acordo com suas necessidades. Certifique-se de preencher os parâmetros necessários e incluir os dados corretos no corpo da requisição, quando aplicável.
5. Interpretação das Respostas: Analise as respostas recebidas da API para garantir que as operações foram concluídas com sucesso. Verifique os códigos de status HTTP, bem como os dados retornados no corpo da resposta.

Ao seguir essas etapas, você poderá interagir plenamente com a API REST para gerenciar dados de funcionários e calcular suas folhas de pagamento de forma eficiente e precisa.