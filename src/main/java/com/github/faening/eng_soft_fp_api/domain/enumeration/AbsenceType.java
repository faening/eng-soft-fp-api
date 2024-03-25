package com.github.faening.eng_soft_fp_api.domain.enumeration;

public enum AbsenceType {
    SICK_LEAVE("Atestado médico"),
    VACATION("Férias"),
    DAY_OFF("Folga"),
    MATERNITY_LEAVE("Licença maternidade"),
    PATERNITY_LEAVE("Licença paternidade"),
    UNPAID_LEAVE("Falta não justificada");

    private final String description;

    AbsenceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
