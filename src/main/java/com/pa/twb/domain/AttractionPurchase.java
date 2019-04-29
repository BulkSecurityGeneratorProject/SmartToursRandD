package com.pa.twb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "user_latitude")
    private Double userLatitude;

    @Column(name = "user_longitude")
    private Double userLongitude;

    @Column(name = "user_distance")
    private Double userDistance;

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

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public AttractionPurchase userLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
        return this;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public AttractionPurchase userLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
        return this;
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

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public AttractionPurchase attraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
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
            ", userLatitude=" + getUserLatitude() +
            ", userLongitude=" + getUserLongitude() +
            ", userDistance=" + getUserDistance() +
            ", purchased='" + isPurchased() + "'" +
            "}";
    }
}
