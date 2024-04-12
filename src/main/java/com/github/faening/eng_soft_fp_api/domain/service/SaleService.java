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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class SaleService extends AbstractService<SaleRequestDTO, SaleResponseDTO> {
    private final SaleRepository saleRepository;
    private final SaleRequestMapper saleRequestMapper;
    private final SaleResponseMapper saleResponseMapper;
    private final EmployeeService employeeService;

    @Autowired
    public SaleService(
        SaleRepository saleRepository,
        SaleRequestMapper saleRequestMapper,
        SaleResponseMapper saleResponseMapper,
        EmployeeService employeeService,
        MessageSource messageSource
    ) {
        this.saleRepository = saleRepository;
        this.saleRequestMapper = saleRequestMapper;
        this.saleResponseMapper = saleResponseMapper;
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }

    @Override
    public List<SaleResponseDTO> getAll() {
        return saleRepository
            .findAll()
            .stream()
            .map(sale -> saleResponseMapper.toDTO(sale, SaleResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO getById(Integer id) {
        return saleResponseMapper.toDTO(searchSaleById(id), SaleResponseDTO.class);
    }

    @Override
    public SaleResponseDTO create(SaleRequestDTO request) {
        validateSaleRequestDTO(request);
        Sale sale = saleRequestMapper.toEntity(request, Sale.class);
        return saleResponseMapper.toDTO(saleRepository.save(sale), SaleResponseDTO.class);
    }

    @Override
    public SaleResponseDTO update(Integer id, SaleRequestDTO request) {
        validateId(id);
        validateSaleRequestDTO(request);
        Sale sale = searchSaleById(id);
        saleRequestMapper.updateSourceFromDestination(sale, request);
        return saleResponseMapper.toDTO(saleRepository.save(sale), SaleResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        Sale sale = searchSaleById(id);
        saleRepository.delete(sale);
    }

    /**
     * Busca as vendas de um funcionário em um intervalo de datas.
     *
     * @param employeId Id do funcionário
     * @param startDate Data de início do intervalo
     * @param endDate Data de fim do intervalo
     *
     * @return Lista de vendas
     */
    public List<SaleResponseDTO> getSalesByEmployeeIdAndDateRange(Integer employeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeService.getEmployeeEntityById(employeId);
        return saleRepository
            .findByEmployeeAndDateBetween(employee, startDate, endDate)
            .stream()
            .map(sales -> saleResponseMapper.toDTO(sales, SaleResponseDTO.class))
            .collect(Collectors.toList());
    }

    public Sale searchSaleById(Integer id) {
        validateId(id);
        return saleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhuma venda encontrada com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("saleService.validation.saleId"));
    }

    private void validateSaleRequestDTO(SaleRequestDTO saleRequestDTO) {
        if (saleRequestDTO.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage("saleService.validation.employeeId"));
        if (saleRequestDTO.getDate() == null)  throw new IllegalArgumentException(getLocalizedMessage("saleService.validation.date"));
        if (saleRequestDTO.getAmount() == null) throw new IllegalArgumentException(getLocalizedMessage("saleService.validation.amount"));
    }
}