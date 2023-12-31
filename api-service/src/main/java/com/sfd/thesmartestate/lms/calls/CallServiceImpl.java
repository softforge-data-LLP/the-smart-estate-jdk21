package com.sfd.thesmartestate.lms.calls;

import com.sfd.thesmartestate.lms.exceptions.LeadException;
import com.sfd.thesmartestate.lms.services.LeadService;
import com.sfd.thesmartestate.lms.services.LeadUpdateService;
import com.sfd.thesmartestate.employee.entities.Employee;
import com.sfd.thesmartestate.employee.services.EmployeeService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP")

public class CallServiceImpl implements CallService {
    private final CallRepository repository;
    private final LeadService leadService;
    private final LeadUpdateService leadUpdateService;
    private final EmployeeService employeeService;

    @Override
    public Call create(Call call, Long leadId) {
        Call oldCall = repository.findByPhoneAndStartTime(call.getPhone(), call.getStartTime());
        if(Objects.isNull(oldCall)) {
            Employee loggedImEmployee = employeeService.findLoggedInEmployee();
            call.setCreatedAt(LocalDateTime.now());
            call.setCreatedBy(loggedImEmployee);
            call = repository.saveAndFlush(call);
            leadUpdateService.updateLeadCallDetails(call, leadId);
            return call;
        }
        throw new LeadException("Call log already exists");
    }

    @Override
    public Call findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new LeadException("Unable to find call with id " + id));
    }

    @Override
    public Set<Call> getAllByLeadId(Long leadId) {
        return leadService.findById(leadId).getCalls();
    }
}
