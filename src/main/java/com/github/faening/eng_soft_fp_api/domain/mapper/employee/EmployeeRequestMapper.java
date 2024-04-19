package com.github.faening.eng_soft_fp_api.domain.mapper.employee;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.DepartmentService;
import com.github.faening.eng_soft_fp_api.domain.service.JobService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class EmployeeRequestMapper extends AbstractMapper<Employee, EmployeeRequestDTO> {
    private final DepartmentService departmentSerice;
    private final JobService jobService;
    private final WorkShiftService workShiftService;

    @Autowired
    public EmployeeRequestMapper(
        ModelMapper modelMapper,
        DepartmentService departmentSerice,
        JobService jobService,
        WorkShiftService workShiftService
    ) {
        super(modelMapper);
        this.departmentSerice = departmentSerice;
        this.jobService = jobService;
        this.workShiftService = workShiftService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Employee.class, EmployeeRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getPerson().getName(), EmployeeRequestDTO::setName);
                mapper.when(notNull).map(src -> src.getPerson().getRg(), EmployeeRequestDTO::setRg);
                mapper.when(notNull).map(src -> src.getPerson().getCpf(), EmployeeRequestDTO::setCpf);
                mapper.when(notNull).map(src -> src.getPerson().getBirthDate(), EmployeeRequestDTO::setBirthDate);
                mapper.when(notNull).map(src -> src.getPerson().getGender(), EmployeeRequestDTO::setGender);
                mapper.when(notNull).map(src -> src.getAddress().getAddressStreet(), EmployeeRequestDTO::setAddressStreet);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNumber(), EmployeeRequestDTO::setAddressNumber);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNeighborhood(), EmployeeRequestDTO::setAddressNeighborhood);
                mapper.when(notNull).map(src -> src.getAddress().getAddressComplement(), EmployeeRequestDTO::setAddressComplement);
                mapper.when(notNull).map(src -> src.getAddress().getAddressCity(), EmployeeRequestDTO::setAddressCity);
                mapper.when(notNull).map(src -> src.getAddress().getAddressUF(), EmployeeRequestDTO::setAddressUF);
                mapper.when(notNull).map(src -> src.getAddress().getAddressZipCode(), EmployeeRequestDTO::setAddressZipCode);
                mapper.when(notNull).map(Employee::getPhone, EmployeeRequestDTO::setPhone);
                mapper.when(notNull).map(Employee::getEmail, EmployeeRequestDTO::setEmail);
                mapper.when(notNull).map(Employee::getAdmissionDate, EmployeeRequestDTO::setAdmissionDate);
                mapper.when(notNull).map(Employee::getTimeServiceAllowance, EmployeeRequestDTO::setTimeServiceAllowance);
                mapper.when(notNull).map(src -> src.getDepartment().getId(), EmployeeRequestDTO::setDepartmentId);
                mapper.when(notNull).map(src -> src.getJob().getId(), EmployeeRequestDTO::setJobId);
                mapper.when(notNull).map(src -> src.getWorkShift().getId(), EmployeeRequestDTO::setWorkShiftId);
                mapper.when(notNull).map(Employee::getSalary, EmployeeRequestDTO::setSalary);
                mapper.when(notNull).map(Employee::getEnabled, EmployeeRequestDTO::setEnabled);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Department> departmentIdToDepartmentConverter = ctx -> departmentSerice.getEntityById(ctx.getSource());
        Converter<Integer, Job> jobIdToJobConverter = ctx -> jobService.getEntityById(ctx.getSource());
        Converter<Integer, WorkShift> workShiftIdToWorkShiftConverter = ctx -> workShiftService.getEntityById(ctx.getSource());

        // https://stackoverflow.com/questions/49831753/modelmapper-matches-multiple-source-property-hierarchies
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(EmployeeRequestDTO.class, Employee.class)
            .addMappings(mapper -> {
                mapper.skip(Employee::setId);
                mapper.skip(Employee::setEntityMetadata);
                mapper.skip(Employee::setAbsenceSheets);
                mapper.skip(Employee::setHoursWorkedSheets);
                mapper.skip(Employee::setLoans);
                mapper.skip(Employee::setBenefitVouchers);
                mapper.skip(Employee::setManagedDepartments);
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getName, (dest, v) -> dest.getPerson().setName(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getRg, (dest, v) -> dest.getPerson().setRg(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getCpf, (dest, v) -> dest.getPerson().setCpf(v));
                mapper.when(notNull).<LocalDate>map(EmployeeRequestDTO::getBirthDate, (dest, v) -> dest.getPerson().setBirthDate(v));
                mapper.when(notNull).<Gender>map(EmployeeRequestDTO::getGender, (dest, v) -> dest.getPerson().setGender(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressNeighborhood, (dest, v) -> dest.getAddress().setAddressNeighborhood(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(EmployeeRequestDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(EmployeeRequestDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
                mapper.when(notNull).map(EmployeeRequestDTO::getPhone, Employee::setPhone);
                mapper.when(notNull).map(EmployeeRequestDTO::getEmail, Employee::setEmail);
                mapper.when(notNull).map(EmployeeRequestDTO::getAdmissionDate, Employee::setAdmissionDate);
                mapper.when(notNull).map(EmployeeRequestDTO::getTimeServiceAllowance, Employee::setTimeServiceAllowance);
                mapper.when(notNull).map(EmployeeRequestDTO::getDepartmentId, Employee::setDepartment);
                mapper.when(notNull).map(EmployeeRequestDTO::getJobId, Employee::setJob);
                mapper.when(notNull).map(EmployeeRequestDTO::getWorkShiftId, Employee::setWorkShift);
                mapper.when(notNull).using(departmentIdToDepartmentConverter).map(EmployeeRequestDTO::getDepartmentId, Employee::setDepartment);
                mapper.when(notNull).using(jobIdToJobConverter).map(EmployeeRequestDTO::getJobId, Employee::setJob);
                mapper.when(notNull).using(workShiftIdToWorkShiftConverter).map(EmployeeRequestDTO::getWorkShiftId, Employee::setWorkShift);
                mapper.when(notNull).map(EmployeeRequestDTO::getSalary, Employee::setSalary);
                mapper.when(notNull).map(EmployeeRequestDTO::getEnabled, Employee::setEnabled);
            });
    }
}