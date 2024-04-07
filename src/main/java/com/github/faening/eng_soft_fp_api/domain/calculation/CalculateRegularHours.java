package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

/*
 * Requisito: [RD001] Calcular Horas Normais Trabalhadas
 *
 * Descrição:
 * Esta classe é responsável por calcular as horas normais recebidas por um funcionário em um determinado mês.
 * A jornada padrão de trabalho considerada é de 8 horas por dia e 40 horas semanais. Isso significa que um funcionário trabalha
 * 8 horas por dia de segunda a sexta-feira, totalizando 40 horas semanais. Não são consideradas horas de trabalho nos finais de semana.
 * Este é o padrão adotado em nossa aplicação e é válido legalmente.
 *
 * Funcionamento:
 * O cálculo das horas normais trabalhadas é feito com base nas horas registradas na tabela `hours_worked_sheet.regular_hours` para o
 * funcionário no mês corrente. Para isso, é utilizado o serviço `HoursWorkedSheetService`, que permite buscar as horas trabalhadas
 * pelo `employee_id` e a `date`.
 *
 * Se o funcionário trabalhou a mesma quantidade de horas do mês, o valor do salário base do funcionário é retornado.
 * Caso contrário, o valor da hora do funcionário é multiplicado pela quantidade de horas trabalhadas no mês.
 *
 * Observe que alguns funcinários são "estagiários" e possuem uma carga horária diferente, por exemplo, 6 horas diárias.
 *
 * Exemplos:
 * Exemplo 1:
 *   Dias Trabalhados em Março: 21 dias
 *   Horas Trabalhadas em Março: 168 horas
 *   Salário Base: R$ 2.000,00
 *   Se o total de horas do funcionário for igual a 168, o salário a ser pago é: R$ 2.000,00
 *
 * Exemplo 2:
 *   Dias Trabalhados em Março: 21 dias
 *   Horas Trabalhadas em Março: 160 horas
 *   Salário Base: R$ 2.000,00
 *   Valor da Hora: R$ 11,90
 *   Se o total de horas do funcionário for igual a 160, o salário a ser pago é: 160 * 11,90 = R$ 1.904,00
 */

@SuppressWarnings("unused")
public class CalculateRegularHours implements PayrollCalculation {
    /*
    * Dicas de codificação:
    *
    * Dica 1:
    * Sempre crie uma branch separada para desenvolver uma funcionalidade. Isso permite que você trabalhe em um ambiente isolado e evita
    * conflitos com o código de seus colegas.
    *
    * Outro ponto importante é que, a branch de origem sempre deve ser a `develop`.
    *
    * Lembre-se de seguir o padrão de nomenclatura de branches. Por exemplo: PRL-001, PRL-002, PRL-003, etc. Observe isso na respectiva
    * tarefa do Trello.
    *
    *
    *
    * Dica 2:
    * Sempre busque a separação de responsabilidades. Se você perceber que o método está fazendo mais de uma coisa, considere dividí-lo em
    * métodos menores. Isso facilita a leitura e a manutenção do código. Por exemplo:
    *
    * public void calculate(CalculationParameters parameters) {
    *   metodo1();
    *   metodo2();
    *   return ...
    * }
    *
    *
    *
    * Dica 3:
    * Evite aninhar if's. Se você perceber que isso está acontecendo, considere decompor o código ou criar métodos menores. Por exemplo:
    *
    * if (condicao1) {
    *    if (condicao2) {
    *      ...
    *   }
    * }
    *
    * Pode ser decomposto em:
    *
    * if (condicao1 && condicao2) {
    *   ...
    * }
    *
    * ou
    *
    * if (condicao1) {
    *   metodo1();
    * }
    *
    * if (condicao2) {
    *   metodo2();
    * }
    *
    *
    *
    * Dica 4:
    * Evite duplicação de código. Se você perceber que um trecho de código está sendo repetido, considere reduzi-lo para evitar a duplicação.
    * Por exemplo:
    *
    * if (condicao1) {
    *   metodo1();
    * }
    *
    * if (condicao2) {
    *   metodo1();
    * }
    *
    * Pode ser decomposto em:
    *
    * if (condicao1 || condicao2) {
    *   metodo1();
    * }
    *
    *
    *
    * Dica 5:
    * Não se esqueça de tratar os casos de erro. Se algo der errado, retorne uma exceção. Sempre que possível, faça a verificação das
    * condições de erro no início do método. Por exemplo:
    *
    * if (condicao1) {
    *   if (condicao1 == null) throw new RuntimeException("Mensagem de erro");
    *   ...
    * }
    *
    *
    * Dica 6:
    * Não se esqueça de testar o seu código. Testes de unidade são uma ótima forma de garantir que o seu código está funcionando. Se você
    * não sabe como fazer testes de unidade, procure aprender ou use o ChatGPT. Eles são muito importantes para garantir a qualidade do seu
    * código.
    *
    *
    * Dica 7:
    * Atenção ao retorno do método calculate. Ele deve retornar um objeto do tipo PayrollItemRequestDTO.
    *
    *
    *
    * Dica 8:
    * Por fim e não menos importante, ao terminar sua implementação, remova esses comentários e faça o commit e push do código.
    * */
    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return null;
    }
}