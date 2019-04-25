package com.pa.twb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Attraction.
 */
@Entity
@Table(name = "attraction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attraction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "subtitle")
    private String subtitle;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "adult_price", precision = 10, scale = 2)
    private BigDecimal adultPrice;

    @Column(name = "child_price", precision = 10, scale = 2)
    private BigDecimal childPrice;

    @Column(name = "jhi_accessible")
    private Boolean accessible;

    @Column(name = "facilities")
    private Boolean facilities;

    @Column(name = "open_time")
    private Instant openTime;

    @Column(name = "close_time")
    private Instant closeTime;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "attraction_group_types",
               joinColumns = @JoinColumn(name = "attractions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "group_types_id", referencedColumnName = "id"))
    private Set<AttractionGroupType> groupTypes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "attraction_event_types",
               joinColumns = @JoinColumn(name = "attractions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "event_types_id", referencedColumnName = "id"))
    private Set<AttractionEventType> eventTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Attraction name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Attraction subtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public Attraction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Attraction latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Attraction longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public Attraction adultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
        return this;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public Attraction childPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
        return this;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public Boolean isAccessible() {
        return accessible;
    }

    public Attraction accessible(Boolean accessible) {
        this.accessible = accessible;
        return this;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public Boolean isFacilities() {
        return facilities;
    }

    public Attraction facilities(Boolean facilities) {
        this.facilities = facilities;
        return this;
    }

    public void setFacilities(Boolean facilities) {
        this.facilities = facilities;
    }

    public Instant getOpenTime() {
        return openTime;
    }

    public Attraction openTime(Instant openTime) {
        this.openTime = openTime;
        return this;
    }

    public void setOpenTime(Instant openTime) {
        this.openTime = openTime;
    }

    public Instant getCloseTime() {
        return closeTime;
    }

    public Attraction closeTime(Instant closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public void setCloseTime(Instant closeTime) {
        this.closeTime = closeTime;
    }

    public Set<AttractionGroupType> getGroupTypes() {
        return groupTypes;
    }

    public Attraction groupTypes(Set<AttractionGroupType> attractionGroupTypes) {
        this.groupTypes = attractionGroupTypes;
        return this;
    }

    public Attraction addGroupTypes(AttractionGroupType attractionGroupType) {
        this.groupTypes.add(attractionGroupType);
        attractionGroupType.getAttractions().add(this);
        return this;
    }

    public Attraction removeGroupTypes(AttractionGroupType attractionGroupType) {
        this.groupTypes.remove(attractionGroupType);
        attractionGroupType.getAttractions().remove(this);
        return this;
    }

    public void setGroupTypes(Set<AttractionGroupType> attractionGroupTypes) {
        this.groupTypes = attractionGroupTypes;
    }

    public Set<AttractionEventType> getEventTypes() {
        return eventTypes;
    }

    public Attraction eventTypes(Set<AttractionEventType> attractionEventTypes) {
        this.eventTypes = attractionEventTypes;
        return this;
    }

    public Attraction addEventTypes(AttractionEventType attractionEventType) {
        this.eventTypes.add(attractionEventType);
        attractionEventType.getAttractions().add(this);
        return this;
    }

    public Attraction removeEventTypes(AttractionEventType attractionEventType) {
        this.eventTypes.remove(attractionEventType);
        attractionEventType.getAttractions().remove(this);
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
        Attraction attraction = (Attraction) o;
        if (attraction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attraction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attraction{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", subtitle='" + getSubtitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", adultPrice=" + getAdultPrice() +
            ", childPrice=" + getChildPrice() +
            ", accessible='" + isAccessible() + "'" +
            ", facilities='" + isFacilities() + "'" +
            ", openTime='" + getOpenTime() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            "}";
    }
}
