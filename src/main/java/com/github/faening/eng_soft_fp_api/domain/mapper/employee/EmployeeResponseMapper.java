package com.github.faening.eng_soft_fp_api.domain.mapper.employee;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class EmployeeResponseMapper extends AbstractMapper<Employee, EmployeeResponseDTO> {
    @Autowired
    public EmployeeResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Employee.class, EmployeeResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Employee::getId, EmployeeResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getPerson().getName(), EmployeeResponseDTO::setName);
                mapper.when(notNull).map(src -> src.getPerson().getRg(), EmployeeResponseDTO::setRg);
                mapper.when(notNull).map(src -> src.getPerson().getCpf(), EmployeeResponseDTO::setCpf);
                mapper.when(notNull).map(src -> src.getPerson().getBirthDate(), EmployeeResponseDTO::setBirthDate);
                mapper.when(notNull).map(src -> src.getPerson().getGender(), EmployeeResponseDTO::setGender);
                mapper.when(notNull).map(src -> src.getAddress().getAddressStreet(), EmployeeResponseDTO::setAddressStreet);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNumber(), EmployeeResponseDTO::setAddressNumber);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNeighborhood(), EmployeeResponseDTO::setAddressNeighborhood);
                mapper.when(notNull).map(src -> src.getAddress().getAddressComplement(), EmployeeResponseDTO::setAddressComplement);
                mapper.when(notNull).map(src -> src.getAddress().getAddressCity(), EmployeeResponseDTO::setAddressCity);
                mapper.when(notNull).map(src -> src.getAddress().getAddressUF(), EmployeeResponseDTO::setAddressUF);
                mapper.when(notNull).map(src -> src.getAddress().getAddressZipCode(), EmployeeResponseDTO::setAddressZipCode);
                mapper.when(notNull).map(Employee::getPhone, EmployeeResponseDTO::setPhone);
                mapper.when(notNull).map(Employee::getEmail, EmployeeResponseDTO::setEmail);
                mapper.when(notNull).map(Employee::getAdmissionDate, EmployeeResponseDTO::setAdmissionDate);
                mapper.when(notNull).map(Employee::getTimeServiceAllowance, EmployeeResponseDTO::setTimeServiceAllowance);
                mapper.when(notNull).map(Employee::getDepartment, EmployeeResponseDTO::setDepartment);
                mapper.when(notNull).map(Employee::getJob, EmployeeResponseDTO::setJob);
                mapper.when(notNull).map(Employee::getWorkShift, EmployeeResponseDTO::setWorkShift);
                mapper.when(notNull).map(Employee::getSalary, EmployeeResponseDTO::setSalary);
                mapper.when(notNull).map(Employee::getEnabled, EmployeeResponseDTO::setEnabled);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), EmployeeResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), EmployeeResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(EmployeeResponseDTO.class, Employee.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(EmployeeResponseDTO::getId, Employee::setId);
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getName, (dest, v) -> dest.getPerson().setName(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getRg, (dest, v) -> dest.getPerson().setRg(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getCpf, (dest, v) -> dest.getPerson().setCpf(v));
                mapper.when(notNull).<LocalDate>map(EmployeeResponseDTO::getBirthDate, (dest, v) -> dest.getPerson().setBirthDate(v));
                mapper.when(notNull).<Gender>map(EmployeeResponseDTO::getGender, (dest, v) -> dest.getPerson().setGender(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressNeighborhood, (dest, v) -> dest.getAddress().setAddressNeighborhood(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(EmployeeResponseDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(EmployeeResponseDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
                mapper.when(notNull).map(EmployeeResponseDTO::getPhone, Employee::setPhone);
                mapper.when(notNull).map(EmployeeResponseDTO::getEmail, Employee::setEmail);
                mapper.when(notNull).map(EmployeeResponseDTO::getAdmissionDate, Employee::setAdmissionDate);
                mapper.when(notNull).map(EmployeeResponseDTO::getTimeServiceAllowance, Employee::setTimeServiceAllowance);
                mapper.when(notNull).map(EmployeeResponseDTO::getDepartment, Employee::setDepartment);
                mapper.when(notNull).map(EmployeeResponseDTO::getJob, Employee::setJob);
                mapper.when(notNull).map(EmployeeResponseDTO::getWorkShift, Employee::setWorkShift);
                mapper.when(notNull).map(EmployeeResponseDTO::getSalary, Employee::setSalary);
                mapper.when(notNull).map(EmployeeResponseDTO::getEnabled, Employee::setEnabled);
                mapper.when(notNull).<LocalDateTime>map(EmployeeResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(EmployeeResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
                mapper.skip(Employee::setAbsenceSheets);
                mapper.skip(Employee::setHoursWorkedSheets);
                mapper.skip(Employee::setLoans);
                mapper.skip(Employee::setBenefitVouchers);
                mapper.skip(Employee::setManagedDepartments);
            });
    }
}
