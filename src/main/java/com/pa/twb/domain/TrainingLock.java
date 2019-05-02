package com.pa.twb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TrainingLock.
 */
@Entity
@Table(name = "training_lock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrainingLock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_lock")
    private Boolean lock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isLock() {
        return lock;
    }

    public TrainingLock lock(Boolean lock) {
        this.lock = lock;
        return this;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrainingLock trainingLock = (TrainingLock) o;
        if (trainingLock.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trainingLock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrainingLock{" +
            "id=" + getId() +
            ", lock='" + isLock() + "'" +
            "}";
    }
}
