package com.sfd.thesmartestate.lms.entities;

import com.sfd.thesmartestate.users.entities.User;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_lead_deactivation_reasons")
@Data
@SuppressFBWarnings("EI_EXPOSE_REP")
public class DeactivationReason implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdateAt;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private User updatedBy;

    @Column(name = "is_active")
    private Boolean active;
}