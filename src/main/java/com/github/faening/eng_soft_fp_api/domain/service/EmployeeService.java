package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.repository.EmployeeRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee.EmployeeRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee.EmployeeResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee.EmployeeSummaryMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends AbstractService<EmployeeRequestDTO, EmployeeResponseDTO> {
    private final EmployeeRepository repository;
    private final EmployeeRequestMapper requestMapper;
    private final EmployeeResponseMapper responseMapper;
    private final EmployeeSummaryMapper summaryMapper;
    private final TaxOrValueService taxOrValueService;

    private static final String VALIDATION_MESSAGE_NAME = "employeeService.validation.name";
    private static final String VALIDATION_MESSAGE_RG = "employeeService.validation.rg";
    private static final String VALIDATION_MESSAGE_CPF = "employeeService.validation.cpf";
    private static final String VALIDATION_MESSAGE_BIRTH_DATE = "employeeService.validation.birthDate";
    private static final String VALIDATION_MESSAGE_GENDER = "employeeService.validation.gender";
    private static final String VALIDATION_MESSAGE_ADDRESS_STREET = "employeeService.validation.addressStreet";
    private static final String VALIDATION_MESSAGE_ADDRESS_NUMBER = "employeeService.validation.addressNumber";
    private static final String VALIDATION_MESSAGE_ADDRESS_NEIGHBORHOOD = "employeeService.validation.addressNeighborhood";
    private static final String VALIDATION_MESSAGE_ADDRESS_CITY = "employeeService.validation.addressCity";
    private static final String VALIDATION_MESSAGE_ADDRESS_UF = "employeeService.validation.addressUF";
    private static final String VALIDATION_MESSAGE_ADDRESS_ZIP_CODE = "employeeService.validation.addressZipCode";
    private static final String VALIDATION_MESSAGE_ADDRESS_DEPARTMENT_ID = "employeeService.validation.departmentId";
    private static final String VALIDATION_MESSAGE_ADDRESS_JOB_ID = "employeeService.validation.jobId";
    private static final String VALIDATION_MESSAGE_ADDRESS_WORK_SHIFT_ID = "employeeService.validation.workShiftId";

    @Autowired
    public EmployeeService(
        EmployeeRepository repository,
        EmployeeRequestMapper requestMapper,
        EmployeeResponseMapper responseMapper,
        EmployeeSummaryMapper summaryMapper,
        TaxOrValueService taxOrValueService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.summaryMapper = summaryMapper;
        this.taxOrValueService = taxOrValueService;
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(employee -> responseMapper.toDTO(employee, EmployeeResponseDTO.class))
            .collect(Collectors.toList());
    }

    public List<EmployeeSummaryDTO> getAllSummaries() {
        return repository
            .findAll()
            .stream()
            .map(employee -> summaryMapper.toDTO(employee, EmployeeSummaryDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchEmployeeById(id), EmployeeResponseDTO.class);
    }

    public Employee getEntityById(Integer id) {
        return searchEmployeeById(id);
    }

    public EmployeeSummaryDTO getSummaryById(Integer id) {
        validate(id);
        return summaryMapper.toDTO(searchEmployeeById(id), EmployeeSummaryDTO.class);
    }

    @Override
    public EmployeeResponseDTO create(EmployeeRequestDTO request) {
        validate(request);
        Employee employee = requestMapper.toEntity(request, Employee.class);
        Employee savedEmployee = repository.save(employee);
        return responseMapper.toDTO(savedEmployee, EmployeeResponseDTO.class);
    }

    @Override
    public EmployeeResponseDTO update(Integer id, EmployeeRequestDTO request) {
        validate(id);
        validate(request);
        Employee employee = searchEmployeeById(id);
        requestMapper.updateSourceFromDestination(employee, request);
        Employee updatedEmployee = repository.save(employee);
        return responseMapper.toDTO(updatedEmployee, EmployeeResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private Employee searchEmployeeById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE));
    }

    public long getEmployeeCountByGender(Gender gender) {
        return repository.countByGender(gender);
    }
                                         @Override
    protected void validate(EmployeeRequestDTO request) {
        super.validate(request);
        if (request.getName() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_NAME));
        if (request.getRg() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_RG));
        if (request.getCpf() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_CPF));
        if (request.getBirthDate() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_BIRTH_DATE));
        if (request.getGender() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_GENDER));
        if (request.getAddressStreet() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_STREET));
        if (request.getAddressNumber() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_NUMBER));
        if (request.getAddressNeighborhood() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_NEIGHBORHOOD));
        if (request.getAddressCity() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_CITY));
        if (request.getAddressUF() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_UF));
        if (request.getAddressZipCode() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_ZIP_CODE));
        if (request.getDepartmentId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_DEPARTMENT_ID));
        if (request.getJobId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_JOB_ID));
        if (request.getWorkShiftId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_ADDRESS_WORK_SHIFT_ID));

        if (request.getAdmissionDate() == null) request.setAdmissionDate(LocalDate.now());
        if (request.getTimeServiceAllowance() == null) request.setTimeServiceAllowance(false);
        if (request.getSalary() == null) request.setSalary(taxOrValueService.getByType(TaxOrValueType.MINIMUM_WAGE).get(0).getFixedValue());
        if (request.getEnabled() == null) request.setEnabled(true);
    }
}