package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

/*
 * Requisito: [RD004] Calcular Adicional Noturno
 *
 * Descrição:
 * Esta classe é responsável por calcular o adicional noturno recebido por um funcionário em um determinado mês.
 * O adicional noturno é um benefício concedido ao trabalhador que realiza suas atividades no período noturno, compreendido entre as 22
 * horas de um dia e as 5 horas do dia seguinte.
 *
 * Funcionamento:
 * Para realizar os cálculos, esta classe observa a propriedade `employee.work_shift_id` e `work_shift.night_shift_allowance`.
 * A propriedade `employee.work_shift_id` é uma chave estrangeira para a tabela `work_shift`, e a propriedade
 * `work_shift.night_shift_allowance` é um valor booleano que indica se a jornada de trabalho dá direito ao adicional noturno
 * (0 para não e 1 para sim).
 * Se a jornada de trabalho do funcionário dá direito ao adicional noturno, o valor da hora do funcionário tem um acréscimo de 20%.
 * O adicional noturno é calculado sobre o valor da hora do funcionário, portanto, é necessário replicar o cálculo feito na classe
 * `CalculateRegularHours`.
 * Além disso, a quantidade de horas trabalhadas no mês também deve ser considerada.
 *
 * Exemplos:
 * ...
 */

@SuppressWarnings("unused")
public class CalculateNightShiftAllowance implements PayrollCalculation {
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