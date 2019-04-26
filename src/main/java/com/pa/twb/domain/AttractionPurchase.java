package com.pa.twb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "attraction_id")
    private Long attractionId;

    @Column(name = "user_distance")
    private Double userDistance;

    @Column(name = "weather_category")
    private String weatherCategory;

    @Column(name = "purchased")
    private Boolean purchased;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "attraction_purchase_group_types",
               joinColumns = @JoinColumn(name = "attraction_purchases_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "group_types_id", referencedColumnName = "id"))
    private Set<AttractionGroupType> groupTypes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "attraction_purchase_event_types",
               joinColumns = @JoinColumn(name = "attraction_purchases_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "event_types_id", referencedColumnName = "id"))
    private Set<AttractionEventType> eventTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttractionId() {
        return attractionId;
    }

    public AttractionPurchase attractionId(Long attractionId) {
        this.attractionId = attractionId;
        return this;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public Double getUserDistance() {
        return userDistance;
    }

    public AttractionPurchase userDistance(Double userDistance) {
        this.userDistance = userDistance;
        return this;
    }

    public void setUserDistance(Double userDistance) {
        this.userDistance = userDistance;
    }

    public String getWeatherCategory() {
        return weatherCategory;
    }

    public void setWeatherCategory(String weatherCategory) {
        this.weatherCategory = weatherCategory;
    }

    public AttractionPurchase weatherCategory(String weatherCategory) {
        this.weatherCategory = weatherCategory;
        return this;
    }

    public Boolean isPurchased() {
        return purchased;
    }

    public AttractionPurchase purchased(Boolean purchased) {
        this.purchased = purchased;
        return this;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Set<AttractionGroupType> getGroupTypes() {
        return groupTypes;
    }

    public AttractionPurchase groupTypes(Set<AttractionGroupType> attractionGroupTypes) {
        this.groupTypes = attractionGroupTypes;
        return this;
    }

    public AttractionPurchase addGroupTypes(AttractionGroupType attractionGroupType) {
        this.groupTypes.add(attractionGroupType);
        attractionGroupType.getPurchases().add(this);
        return this;
    }

    public AttractionPurchase removeGroupTypes(AttractionGroupType attractionGroupType) {
        this.groupTypes.remove(attractionGroupType);
        attractionGroupType.getPurchases().remove(this);
        return this;
    }

    public void setGroupTypes(Set<AttractionGroupType> attractionGroupTypes) {
        this.groupTypes = attractionGroupTypes;
    }

    public Set<AttractionEventType> getEventTypes() {
        return eventTypes;
    }

    public AttractionPurchase eventTypes(Set<AttractionEventType> attractionEventTypes) {
        this.eventTypes = attractionEventTypes;
        return this;
    }

    public AttractionPurchase addEventTypes(AttractionEventType attractionEventType) {
        this.eventTypes.add(attractionEventType);
        attractionEventType.getPurchases().add(this);
        return this;
    }

    public AttractionPurchase removeEventTypes(AttractionEventType attractionEventType) {
        this.eventTypes.remove(attractionEventType);
        attractionEventType.getPurchases().remove(this);
        return this;
    }

    public void setEventTypes(Set<AttractionEventType> attractionEventTypes) {
        this.eventTypes = attractionEventTypes;
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
            ", attractionId=" + getAttractionId() +
            ", userDistance=" + getUserDistance() +
            ", weatherCategory='" + getWeatherCategory() + "'" +
            ", purchased='" + isPurchased() + "'" +
            "}";
    }
}
