package com.pa.twb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "sygic_travel_id")
    private String sygicTravelId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "name")
    private String name;

    @Column(name = "marker")
    private String marker;

    @Lob
    @Column(name = "perex")
    private String perex;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "categories")
    private String categories;

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

    public String getSygicTravelId() {
        return sygicTravelId;
    }

    public Attraction sygicTravelId(String sygicTravelId) {
        this.sygicTravelId = sygicTravelId;
        return this;
    }

    public void setSygicTravelId(String sygicTravelId) {
        this.sygicTravelId = sygicTravelId;
    }

    public Double getRating() {
        return rating;
    }

    public Attraction rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getLat() {
        return lat;
    }

    public Attraction lat(Double lat) {
        this.lat = lat;
        return this;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public Attraction lng(Double lng) {
        this.lng = lng;
        return this;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public String getMarker() {
        return marker;
    }

    public Attraction marker(String marker) {
        this.marker = marker;
        return this;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getPerex() {
        return perex;
    }

    public Attraction perex(String perex) {
        this.perex = perex;
        return this;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Attraction thumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategories() {
        return categories;
    }

    public Attraction categories(String categories) {
        this.categories = categories;
        return this;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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
            ", sygicTravelId='" + getSygicTravelId() + "'" +
            ", rating=" + getRating() +
            ", lat=" + getLat() +
            ", lng=" + getLng() +
            ", name='" + getName() + "'" +
            ", marker='" + getMarker() + "'" +
            ", perex='" + getPerex() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", categories='" + getCategories() + "'" +
            ", adultPrice=" + getAdultPrice() +
            ", childPrice=" + getChildPrice() +
            ", accessible='" + isAccessible() + "'" +
            ", facilities='" + isFacilities() + "'" +
            ", openTime='" + getOpenTime() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            "}";
    }
}
