package com.pa.twb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.pa.twb.domain.enumeration.EventType;

/**
 * A AttractionEventType.
 */
@Entity
@Table(name = "attraction_event_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttractionEventType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @ManyToMany(mappedBy = "eventTypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attraction> attractions = new HashSet<>();

    @ManyToMany(mappedBy = "eventTypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AttractionPurchase> purchases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public AttractionEventType eventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public AttractionEventType attractions(Set<Attraction> attractions) {
        this.attractions = attractions;
        return this;
    }

    public AttractionEventType addAttraction(Attraction attraction) {
        this.attractions.add(attraction);
        attraction.getEventTypes().add(this);
        return this;
    }

    public AttractionEventType removeAttraction(Attraction attraction) {
        this.attractions.remove(attraction);
        attraction.getEventTypes().remove(this);
        return this;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Set<AttractionPurchase> getPurchases() {
        return purchases;
    }

    public AttractionEventType purchases(Set<AttractionPurchase> attractionPurchases) {
        this.purchases = attractionPurchases;
        return this;
    }

    public AttractionEventType addPurchase(AttractionPurchase attractionPurchase) {
        this.purchases.add(attractionPurchase);
        attractionPurchase.getEventTypes().add(this);
        return this;
    }

    public AttractionEventType removePurchase(AttractionPurchase attractionPurchase) {
        this.purchases.remove(attractionPurchase);
        attractionPurchase.getEventTypes().remove(this);
        return this;
    }

    public void setPurchases(Set<AttractionPurchase> attractionPurchases) {
        this.purchases = attractionPurchases;
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
        AttractionEventType attractionEventType = (AttractionEventType) o;
        if (attractionEventType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attractionEventType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttractionEventType{" +
            "id=" + getId() +
            ", eventType='" + getEventType() + "'" +
            "}";
    }
}
