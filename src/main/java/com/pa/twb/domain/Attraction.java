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

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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

    @Lob
    @Column(name = "ds_summary")
    private String dsSummary;

    @Column(name = "ds_icon")
    private String dsIcon;

    @Column(name = "ds_apparent_temperature_high")
    private Double dsApparentTemperatureHigh;

    @Column(name = "ds_apparent_temperature_low")
    private Double dsApparentTemperatureLow;

    @Column(name = "ds_dew_point")
    private Double dsDewPoint;

    @Column(name = "ds_humidity")
    private Double dsHumidity;

    @Column(name = "ds_pressure")
    private Double dsPressure;

    @Column(name = "ds_wind_speed")
    private Double dsWindSpeed;

    @Column(name = "ds_wind_gust")
    private Double dsWindGust;

    @Column(name = "ds_cloud_cover")
    private Double dsCloudCover;

    @Column(name = "ds_visibility")
    private Long dsVisibility;

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

    @OneToMany(mappedBy = "attraction")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AttractionPurchase> attractionPurchases = new HashSet<>();

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Attraction latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Attraction longitude(Double longitude) {
        this.longitude = longitude;
        return this;
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

    public String getDsSummary() {
        return dsSummary;
    }

    public void setDsSummary(String dsSummary) {
        this.dsSummary = dsSummary;
    }

    public Attraction dsSummary(String dsSummary) {
        this.dsSummary = dsSummary;
        return this;
    }

    public String getDsIcon() {
        return dsIcon;
    }

    public void setDsIcon(String dsIcon) {
        this.dsIcon = dsIcon;
    }

    public Attraction dsIcon(String dsIcon) {
        this.dsIcon = dsIcon;
        return this;
    }

    public Double getDsApparentTemperatureHigh() {
        return dsApparentTemperatureHigh;
    }

    public void setDsApparentTemperatureHigh(Double dsApparentTemperatureHigh) {
        this.dsApparentTemperatureHigh = dsApparentTemperatureHigh;
    }

    public Attraction dsApparentTemperatureHigh(Double dsApparentTemperatureHigh) {
        this.dsApparentTemperatureHigh = dsApparentTemperatureHigh;
        return this;
    }

    public Double getDsApparentTemperatureLow() {
        return dsApparentTemperatureLow;
    }

    public void setDsApparentTemperatureLow(Double dsApparentTemperatureLow) {
        this.dsApparentTemperatureLow = dsApparentTemperatureLow;
    }

    public Attraction dsApparentTemperatureLow(Double dsApparentTemperatureLow) {
        this.dsApparentTemperatureLow = dsApparentTemperatureLow;
        return this;
    }

    public Double getDsDewPoint() {
        return dsDewPoint;
    }

    public void setDsDewPoint(Double dsDewPoint) {
        this.dsDewPoint = dsDewPoint;
    }

    public Attraction dsDewPoint(Double dsDewPoint) {
        this.dsDewPoint = dsDewPoint;
        return this;
    }

    public Double getDsHumidity() {
        return dsHumidity;
    }

    public void setDsHumidity(Double dsHumidity) {
        this.dsHumidity = dsHumidity;
    }

    public Attraction dsHumidity(Double dsHumidity) {
        this.dsHumidity = dsHumidity;
        return this;
    }

    public Double getDsPressure() {
        return dsPressure;
    }

    public void setDsPressure(Double dsPressure) {
        this.dsPressure = dsPressure;
    }

    public Attraction dsPressure(Double dsPressure) {
        this.dsPressure = dsPressure;
        return this;
    }

    public Double getDsWindSpeed() {
        return dsWindSpeed;
    }

    public void setDsWindSpeed(Double dsWindSpeed) {
        this.dsWindSpeed = dsWindSpeed;
    }

    public Attraction dsWindSpeed(Double dsWindSpeed) {
        this.dsWindSpeed = dsWindSpeed;
        return this;
    }

    public Double getDsWindGust() {
        return dsWindGust;
    }

    public void setDsWindGust(Double dsWindGust) {
        this.dsWindGust = dsWindGust;
    }

    public Attraction dsWindGust(Double dsWindGust) {
        this.dsWindGust = dsWindGust;
        return this;
    }

    public Double getDsCloudCover() {
        return dsCloudCover;
    }

    public void setDsCloudCover(Double dsCloudCover) {
        this.dsCloudCover = dsCloudCover;
    }

    public Attraction dsCloudCover(Double dsCloudCover) {
        this.dsCloudCover = dsCloudCover;
        return this;
    }

    public Long getDsVisibility() {
        return dsVisibility;
    }

    public void setDsVisibility(Long dsVisibility) {
        this.dsVisibility = dsVisibility;
    }

    public Attraction dsVisibility(Long dsVisibility) {
        this.dsVisibility = dsVisibility;
        return this;
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

    public Set<AttractionPurchase> getAttractionPurchases() {
        return attractionPurchases;
    }

    public void setAttractionPurchases(Set<AttractionPurchase> attractionPurchases) {
        this.attractionPurchases = attractionPurchases;
    }

    public Attraction attractionPurchases(Set<AttractionPurchase> attractionPurchases) {
        this.attractionPurchases = attractionPurchases;
        return this;
    }

    public Attraction addAttractionPurchases(AttractionPurchase attractionPurchase) {
        this.attractionPurchases.add(attractionPurchase);
        attractionPurchase.setAttraction(this);
        return this;
    }

    public Attraction removeAttractionPurchases(AttractionPurchase attractionPurchase) {
        this.attractionPurchases.remove(attractionPurchase);
        attractionPurchase.setAttraction(null);
        return this;
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
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", name='" + getName() + "'" +
            ", marker='" + getMarker() + "'" +
            ", perex='" + getPerex() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", categories='" + getCategories() + "'" +
            ", dsSummary='" + getDsSummary() + "'" +
            ", dsIcon='" + getDsIcon() + "'" +
            ", dsApparentTemperatureHigh=" + getDsApparentTemperatureHigh() +
            ", dsApparentTemperatureLow=" + getDsApparentTemperatureLow() +
            ", dsDewPoint=" + getDsDewPoint() +
            ", dsHumidity=" + getDsHumidity() +
            ", dsPressure=" + getDsPressure() +
            ", dsWindSpeed=" + getDsWindSpeed() +
            ", dsWindGust=" + getDsWindGust() +
            ", dsCloudCover=" + getDsCloudCover() +
            ", dsVisibility=" + getDsVisibility() +
            ", adultPrice=" + getAdultPrice() +
            ", childPrice=" + getChildPrice() +
            ", accessible='" + isAccessible() + "'" +
            ", facilities='" + isFacilities() + "'" +
            ", openTime='" + getOpenTime() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            "}";
    }
}
