package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.UnionContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/union-contributions")
public class UnionContributionController extends AbstractController<UnionContributionRequestDTO, UnionContributionResponseDTO>{
    private final UnionContributionService service;

    @Autowired
    public UnionContributionController(UnionContributionService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = { "/employee" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnionContributionResponseDTO> getUnionContributionByEmployeeIdAndReleaseYear(
        @RequestParam Integer employeeId,
        @RequestParam Integer releaseYear
    ) {
        UnionContributionResponseDTO unionContribution = service.getByEmployeeIdAndReleaseYear(employeeId, releaseYear);
        return ResponseEntity.ok(unionContribution);
    }
}