package com.github.faening.eng_soft_fp_api.domain.model;

import java.util.Date;

public record WorkShiftVO(
    Integer id,
    String description,
    Date startOfWorkday,
    Date startOfBreak,
    Date endOfBreak,
    Date endOfWorkday,
    Boolean nightShiftAllowance,
    Boolean active,
    EntityMetadataVO entityMetadata
) { }