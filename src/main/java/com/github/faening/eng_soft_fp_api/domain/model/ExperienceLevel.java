package com.github.faening.eng_soft_fp_api.domain.model;

public enum ExperienceLevel {
    ENTRY_LEVEL("Em Experiência"),
    JUNIOR("Junior"),
    MID_LEVEL("Pleno"),
    SENIOR("Sênior"),
    EXPERT("Expert");

    private final String experienceLevel;

    ExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }
}
