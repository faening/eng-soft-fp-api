package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

/*
 * Requisito: [RD003] Calcular Comissão
 *
 * Descrição:
 * Esta classe é responsável por calcular as comissões recebidas por um funcionário em um determinado mês.
 * As comissões são calculadas como percentuais sobre o valor das vendas realizadas pelo funcionário. O percentual de comissão pode variar
 * de acordo com a faixa de lucro nas vendas ou ser um valor fixo, conforme acordado com o funcionário.
 * Nós não vamos gerenciar os fluxos de venda e produtos diretamente. Consideramos que o percentual padrão da comissão é de 5% sobre o total
 * de vendas. As vendas são representadas por eventos financeiros com o código rubric_id 1207 (Comissão).
 *
 * Para realizar os cálculos, esta classe utiliza o serviço `FinancialEventService` para buscar os eventos financeiros de um funcionário em
 * um determinado mês, com um rubric_id específico. Todas as informações relevantes da comissão estão disponíveis na tabela
 * `financial_event`, especialmente no campo 'value'. Portanto, a classe simplesmente busca esses dados e retorna o valor da comissão.
 *
 * Ao calcular a comissão, deve-se observar o campo `release_date` para considerar o mês corrente, e também verificar se o campo status
 * está como 'APPROVED' para garantir que o valor da comissão seja considerado.
 * Possíveis descontos que podem ser aplicados sobre o valor da comissão devem ser descritos nos requisitos e considerados durante o
 * cálculo.
 *
 * Funcionamento:
 * ...
 *
 * Exemplos:
 * ...
 */

@SuppressWarnings("unused")
public class CalculateSalesAllowance implements PayrollCalculation {
    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
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
        return null;
    }

}