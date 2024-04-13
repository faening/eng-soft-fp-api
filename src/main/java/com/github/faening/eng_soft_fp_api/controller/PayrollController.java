package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/payroll")
public class PayrollController extends AbstractController<PayrollRequestDTO, PayrollResponseDTO> {
    private final PayrollService service;

    @Autowired
    public PayrollController(PayrollService service) {
        super(service);
        this.service = service;
    }

    @Override
    public ResponseEntity<List<PayrollResponseDTO>> getAll() {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PayrollResponseDTO> getById(Integer id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = {"/employee"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayrollResponseDTO> getPayrollByEmployeeIdAndMonthAndYear(
        @RequestParam Integer employeeId,
        @RequestParam Month month,
        @RequestParam Integer year
    ) {
        PayrollResponseDTO payroll = service.getByEmployeeIdAndMonthAndYear(employeeId, month, year);
        return ResponseEntity.ok(payroll);
    }

    @PostMapping(value = {"/calculate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PayrollResponseDTO> calculatePayroll(
        @RequestParam Integer employeeId,
        @RequestParam Month month,
        @RequestParam Integer year
    ) {
        PayrollResponseDTO payroll = service.calculate(employeeId, month, year);
        return ResponseEntity.ok(payroll);
    }

    @Override
    public ResponseEntity<PayrollResponseDTO> update(Integer id, PayrollRequestDTO request) {
        return ResponseEntity.noContent().build();
    }
}