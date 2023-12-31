package com.sfd.thesmartestate.lms.entities;

import com.sfd.thesmartestate.employee.entities.Employee;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_lead_assign_history")
@Data

@SuppressFBWarnings("EI_EXPOSE_REP")
public class LeadAssignHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    private Employee assignedBy;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    private Employee assignedTo;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    private Employee assignedFrom;


    private LocalDateTime assignmentTime;
}
