package com.github.faening.eng_soft_fp_api.domain.calculation.generics;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class NightShiftCalculation {
    private static final LocalTime NIGHT_START = LocalTime.of(22, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(5, 0);

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    public static int calculateNightHours(LocalTime shiftStart, LocalTime shiftEnd) {
        // Se o turno começa e termina fora do período noturno, retorna 0
        if (shiftStart.isBefore(NIGHT_START) && shiftStart.isAfter(NIGHT_END) ||
            shiftEnd.isBefore(NIGHT_START) && shiftEnd.isAfter(NIGHT_END) && shiftStart.isAfter(NIGHT_START)) {
            return 0;
        }
        // Se o turno começa e termina no mesmo dia, calcula as horas noturnas para o mesmo dia
        if (shiftStart.isBefore(shiftEnd)) {
            return calculateNightHoursSameDay(shiftStart, shiftEnd);
        // Se o turno começa em um dia e termina no próximo, calcula as horas noturnas para o turno que se estende pela meia-noite
        } else {
            return calculateNightHoursOvernight(shiftStart, shiftEnd);
        }
    }

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno que começa e termina no mesmo dia.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    private static int calculateNightHoursSameDay(LocalTime shiftStart, LocalTime shiftEnd) {
        // Define o início do período noturno. Se o turno começa antes do início do período noturno, o início do período noturno é considerado.
        // Se o turno começa depois do fim do período noturno, o início do turno é considerado.
        LocalTime nightStart = (shiftStart.isBefore(NIGHT_START) && shiftStart.isAfter(NIGHT_END)) ? NIGHT_START : shiftStart;
        // Define o fim do período noturno. Se o turno termina depois do fim do período noturno, o fim do período noturno é considerado.
        // Se o turno termina antes do início do período noturno, o fim do turno é considerado.
        LocalTime nightEnd = (shiftEnd.isAfter(NIGHT_END) && shiftEnd.isBefore(NIGHT_START)) ? NIGHT_END : shiftEnd;

        // Se o turno começa depois do fim do período noturno e termina antes do início do período noturno, o início e o fim do turno são considerados.
        if (shiftStart.isAfter(NIGHT_END) && shiftEnd.isBefore(NIGHT_START)) {
            nightStart = shiftStart;
            nightEnd = shiftEnd;
        }

        long minutes = ChronoUnit.MINUTES.between(nightStart, nightEnd);
        return (int) Math.ceil(minutes / 60.0);
    }

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno que começa em um dia e termina no próximo.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    private static int calculateNightHoursOvernight(LocalTime shiftStart, LocalTime shiftEnd) {
        // Define o início do período noturno antes da meia-noite. Se o turno começa antes do início do período noturno, o início do período noturno é considerado.
        LocalTime nightStartBeforeMidnight = shiftStart.isBefore(NIGHT_START) ? NIGHT_START : shiftStart;
        long minutesBeforeMidnight = ChronoUnit.MINUTES.between(nightStartBeforeMidnight, LocalTime.MAX);
        int nightHoursBeforeMidnight = (int) Math.ceil(minutesBeforeMidnight / 60.0);

        // Define o fim do período noturno depois da meia-noite. Se o turno termina depois do fim do período noturno, o fim do período noturno é considerado.
        LocalTime nightEndAfterMidnight = shiftEnd.isAfter(NIGHT_END) ? NIGHT_END : shiftEnd;
        long minutesAfterMidnight = ChronoUnit.MINUTES.between(LocalTime.MIN, nightEndAfterMidnight);
        int nightHoursAfterMidnight = (int) Math.ceil(minutesAfterMidnight / 60.0);

        return nightHoursBeforeMidnight + nightHoursAfterMidnight;
    }
}