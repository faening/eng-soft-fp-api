package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

/*
 * Requisito: [RD006] Calcular Adicional por Periculosidade
 *
 * Descrição:
 * Esta classe é responsável por calcular o adicional por periculosidade recebido por um funcionário em um determinado mês.
 * O adicional por periculosidade é um benefício concedido ao trabalhador que exerce suas atividades em condições consideradas perigosas,
 * que oferecem risco iminente à sua integridade física.
 *
 * Funcionamento:
 * Para realizar os cálculos, esta classe observa a propriedade `employee.job_id` e `job.dangerousness`.
 * A propriedade `employee.job_id` é uma chave estrangeira para a tabela `job`, e a propriedade `job.dangerousness` é um valor
 * booleano que indica se o cargo é perigoso ou não. (0 = Não Perigoso, 1 = Perigoso).
 * O adicional por insalubridade é calculado sobre o salário base do funcionário, portanto, é necessário observar a propriedade
 * `employee.base_salary`.
 * Além disos, as alíquotas de insalubridade são armazenadas na tabela `tax_or_value` com o `type`: `DANGEROUSNESS_ALLOWANCE`.
 *
 * Exemplos:
 * ...
 */

@SuppressWarnings("unused")
public class CalculateDangerousnessAllowance implements PayrollCalculation {
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