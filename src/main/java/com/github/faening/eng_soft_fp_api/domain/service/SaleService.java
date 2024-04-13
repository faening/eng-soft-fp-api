package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Sale;
import com.github.faening.eng_soft_fp_api.data.repository.SaleRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.sale.SaleRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.sale.SaleResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
public class SaleService extends AbstractService<SaleRequestDTO, SaleResponseDTO> {
    private final SaleRepository repository;
    private final SaleRequestMapper requestMapper;
    private final SaleResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String SALE_EMPLOYEE_ID_VALIDATION_MESSAGE = "saleService.validation.employeeId";
    private static final String SALE_DATE_VALIDATION_MESSAGE = "saleService.validation.date";
    private static final String SALE_AMOUNT_VALIDATION_MESSAGE = "saleService.validation.amount";

    @Autowired
    public SaleService(
        SaleRepository repository,
        SaleRequestMapper requestMapper,
        SaleResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<SaleResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(sale -> responseMapper.toDTO(sale, SaleResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO getById(Integer id) {
        return responseMapper.toDTO(searchSaleById(id), SaleResponseDTO.class);
    }

    public Sale getEntityById(Integer id) {
        return searchSaleById(id);
    }

    public List<SaleResponseDTO> getSalesByEmployeeIdAndDateRange(Integer employeeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeService.getEmployeeEntityById(employeeId);
        return repository
            .findByEmployeeAndDateBetween(employee, startDate, endDate)
            .stream()
            .map(sales -> responseMapper.toDTO(sales, SaleResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO create(SaleRequestDTO request) {
        validate(request);
        Sale sale = requestMapper.toEntity(request, Sale.class);
        return responseMapper.toDTO(repository.save(sale), SaleResponseDTO.class);
    }

    @Override
    public SaleResponseDTO update(Integer id, SaleRequestDTO request) {
        validate(id);
        validate(request);
        Sale sale = searchSaleById(id);
        requestMapper.updateSourceFromDestination(sale, request);
        return responseMapper.toDTO(repository.save(sale), SaleResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        Sale sale = searchSaleById(id);
        repository.delete(sale);
    }

    private Sale searchSaleById(Integer id) {
        validate(id);
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(SaleRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage(SALE_EMPLOYEE_ID_VALIDATION_MESSAGE));
        if (request.getDate() == null) throw new IllegalArgumentException(getLocalizedMessage(SALE_DATE_VALIDATION_MESSAGE));
        if (request.getAmount() == null) throw new IllegalArgumentException(getLocalizedMessage(SALE_AMOUNT_VALIDATION_MESSAGE));
    }
}