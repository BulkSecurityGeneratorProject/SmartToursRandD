package com.pa.twb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AttractionPurchase.
 */
@Entity
@Table(name = "attraction_purchase")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttractionPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "traveling")
    private String traveling;

    @Column(name = "activity")
    private String activity;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "action_taken_at")
    private Instant actionTakenAt;

    @Column(name = "action_taken")
    private Boolean actionTaken;

    @ManyToOne
    @JsonIgnoreProperties("attractionPurchases")
    private Attraction attraction;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraveling() {
        return traveling;
    }

    public AttractionPurchase traveling(String traveling) {
        this.traveling = traveling;
        return this;
    }

    public void setTraveling(String traveling) {
        this.traveling = traveling;
    }

    public String getActivity() {
        return activity;
    }

    public AttractionPurchase activity(String activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Double getDistance() {
        return distance;
    }

    public AttractionPurchase distance(Double distance) {
        this.distance = distance;
        return this;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AttractionPurchase createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getActionTakenAt() {
        return actionTakenAt;
    }

    public AttractionPurchase actionTakenAt(Instant actionTakenAt) {
        this.actionTakenAt = actionTakenAt;
        return this;
    }

    public void setActionTakenAt(Instant actionTakenAt) {
        this.actionTakenAt = actionTakenAt;
    }

    public Boolean isActionTaken() {
        return actionTaken;
    }

    public AttractionPurchase actionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
        return this;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public AttractionPurchase attraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
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
        AttractionPurchase attractionPurchase = (AttractionPurchase) o;
        if (attractionPurchase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attractionPurchase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttractionPurchase{" +
            "id=" + getId() +
            ", traveling='" + getTraveling() + "'" +
            ", activity='" + getActivity() + "'" +
            ", distance=" + getDistance() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", actionTakenAt='" + getActionTakenAt() + "'" +
            ", actionTaken='" + isActionTaken() + "'" +
            "}";
    }
}
