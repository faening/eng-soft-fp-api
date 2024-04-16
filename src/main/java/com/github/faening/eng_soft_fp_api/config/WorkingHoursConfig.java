package com.github.faening.eng_soft_fp_api.config;

import org.springframework.stereotype.Component;

@Component
public class WorkingHoursConfig {
    public int getFullTimeHours() {
        return 8;
    }

    public int getReducedHours() {
        return 6;
    }
}