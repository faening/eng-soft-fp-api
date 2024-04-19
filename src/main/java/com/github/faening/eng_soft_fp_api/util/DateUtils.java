package com.github.faening.eng_soft_fp_api.util;

import java.time.*;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
public class DateUtils {
    /**
     * Converte um LocalDateTime para Date
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converte um Date para LocalDateTime
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Retorna um array com o primeiro e o último dia de um mês
     * @param year Ano
     * @param month Mês
     * @return Array com o primeiro e o último dia do mês
     */
    public static LocalDate[] getFirstAndLastDayOfMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        return new LocalDate[]{firstDayOfMonth, lastDayOfMonth};
    }

    /**
     * Retorna a quantidade de dias úteis em um mês
     * @param year Ano
     * @param month Mês
     * @return Quantidade de dias úteis
    */
    public static int getWorkingDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        int workingDays = 0;
        for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                workingDays++;
            }
        }

        return workingDays;
    }

    /**
     * Converte um LocalTime para minutos
     * @param time LocalTime
     * @return Minutos
     */
    public static int toMinutes(LocalTime time) {
        return time.getHour() * 60 + time.getMinute();
    }

    /**
     * Converte um LocalTime para horas
     * @param time LocalTime
     * @return Horas
     */
    @SuppressWarnings("ConstantValue")
    public static int toHours(LocalTime time) {
        return time.getHour() + time.getMinute() / 60;
    }
}