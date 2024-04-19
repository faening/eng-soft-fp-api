package com.github.faening.eng_soft_fp_api.domain.mapper.employee;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class EmployeeSummaryMapper extends AbstractMapper<Employee, EmployeeSummaryDTO> {
    @Autowired
    public EmployeeSummaryMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Employee.class, EmployeeSummaryDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Employee::getId, EmployeeSummaryDTO::setId);
                mapper.when(notNull).map(src -> src.getPerson().getName(), EmployeeSummaryDTO::setName);
                mapper.when(notNull).map(src -> src.getPerson().getRg(), EmployeeSummaryDTO::setRg);
                mapper.when(notNull).map(src -> src.getPerson().getCpf(), EmployeeSummaryDTO::setCpf);
                mapper.when(notNull).map(src -> src.getPerson().getBirthDate(), EmployeeSummaryDTO::setBirthDate);
                mapper.when(notNull).map(src -> src.getPerson().getGender(), EmployeeSummaryDTO::setGender);
                mapper.when(notNull).map(src -> src.getAddress().getAddressStreet(), EmployeeSummaryDTO::setAddressStreet);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNumber(), EmployeeSummaryDTO::setAddressNumber);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNeighborhood(), EmployeeSummaryDTO::setAddressNeighborhood);
                mapper.when(notNull).map(src -> src.getAddress().getAddressComplement(), EmployeeSummaryDTO::setAddressComplement);
                mapper.when(notNull).map(src -> src.getAddress().getAddressCity(), EmployeeSummaryDTO::setAddressCity);
                mapper.when(notNull).map(src -> src.getAddress().getAddressUF(), EmployeeSummaryDTO::setAddressUF);
                mapper.when(notNull).map(src -> src.getAddress().getAddressZipCode(), EmployeeSummaryDTO::setAddressZipCode);
                mapper.when(notNull).map(Employee::getPhone, EmployeeSummaryDTO::setPhone);
                mapper.when(notNull).map(Employee::getEmail, EmployeeSummaryDTO::setEmail);
                mapper.when(notNull).map(Employee::getAdmissionDate, EmployeeSummaryDTO::setAdmissionDate);
                mapper.when(notNull).map(Employee::getTimeServiceAllowance, EmployeeSummaryDTO::setTimeServiceAllowance);
                mapper.when(notNull).map(src -> src.getDepartment().getId(), EmployeeSummaryDTO::setDepartmentId);
                mapper.when(notNull).map(src -> src.getJob().getId(), EmployeeSummaryDTO::setJobId);
                mapper.when(notNull).map(src -> src.getWorkShift().getId(), EmployeeSummaryDTO::setWorkShiftId);
                mapper.when(notNull).map(Employee::getSalary, EmployeeSummaryDTO::setSalary);
                mapper.when(notNull).map(Employee::getEnabled, EmployeeSummaryDTO::setEnabled);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), EmployeeSummaryDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), EmployeeSummaryDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(EmployeeSummaryDTO.class, Employee.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(EmployeeSummaryDTO::getId, Employee::setId);
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getName, (dest, v) -> dest.getPerson().setName(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getRg, (dest, v) -> dest.getPerson().setRg(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getCpf, (dest, v) -> dest.getPerson().setCpf(v));
                mapper.when(notNull).<LocalDate>map(EmployeeSummaryDTO::getBirthDate, (dest, v) -> dest.getPerson().setBirthDate(v));
                mapper.when(notNull).<Gender>map(EmployeeSummaryDTO::getGender, (dest, v) -> dest.getPerson().setGender(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressNeighborhood, (dest, v) -> dest.getAddress().setAddressNeighborhood(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(EmployeeSummaryDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(EmployeeSummaryDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
                mapper.when(notNull).map(EmployeeSummaryDTO::getPhone, Employee::setPhone);
                mapper.when(notNull).map(EmployeeSummaryDTO::getEmail, Employee::setEmail);
                mapper.when(notNull).map(EmployeeSummaryDTO::getAdmissionDate, Employee::setAdmissionDate);
                mapper.when(notNull).map(EmployeeSummaryDTO::getTimeServiceAllowance, Employee::setTimeServiceAllowance);
                mapper.when(notNull).map(EmployeeSummaryDTO::getDepartmentId, Employee::setDepartment);
                mapper.when(notNull).map(EmployeeSummaryDTO::getJobId, Employee::setJob);
                mapper.when(notNull).map(EmployeeSummaryDTO::getWorkShiftId, Employee::setWorkShift);
                mapper.when(notNull).map(EmployeeSummaryDTO::getSalary, Employee::setSalary);
                mapper.when(notNull).map(EmployeeSummaryDTO::getEnabled, Employee::setEnabled);
                mapper.when(notNull).<LocalDateTime>map(EmployeeSummaryDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(EmployeeSummaryDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
                mapper.skip(Employee::setAbsenceSheets);
                mapper.skip(Employee::setHoursWorkedSheets);
                mapper.skip(Employee::setLoans);
                mapper.skip(Employee::setBenefitVouchers);
                mapper.skip(Employee::setManagedDepartments);
            });
    }
}