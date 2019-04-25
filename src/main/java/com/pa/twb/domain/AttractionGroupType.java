package com.pa.twb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.pa.twb.domain.enumeration.GroupType;

/**
 * A AttractionGroupType.
 */
@Entity
@Table(name = "attraction_group_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AttractionGroupType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupType groupType;

    @ManyToMany(mappedBy = "groupTypes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attraction> attractions = new HashSet<>();

    @ManyToMany(mappedBy = "groupTypes")
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

    public GroupType getGroupType() {
        return groupType;
    }

    public AttractionGroupType groupType(GroupType groupType) {
        this.groupType = groupType;
        return this;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public AttractionGroupType attractions(Set<Attraction> attractions) {
        this.attractions = attractions;
        return this;
    }

    public AttractionGroupType addAttraction(Attraction attraction) {
        this.attractions.add(attraction);
        attraction.getGroupTypes().add(this);
        return this;
    }

    public AttractionGroupType removeAttraction(Attraction attraction) {
        this.attractions.remove(attraction);
        attraction.getGroupTypes().remove(this);
        return this;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Set<AttractionPurchase> getPurchases() {
        return purchases;
    }

    public AttractionGroupType purchases(Set<AttractionPurchase> attractionPurchases) {
        this.purchases = attractionPurchases;
        return this;
    }

    public AttractionGroupType addPurchase(AttractionPurchase attractionPurchase) {
        this.purchases.add(attractionPurchase);
        attractionPurchase.getGroupTypes().add(this);
        return this;
    }

    public AttractionGroupType removePurchase(AttractionPurchase attractionPurchase) {
        this.purchases.remove(attractionPurchase);
        attractionPurchase.getGroupTypes().remove(this);
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
        AttractionGroupType attractionGroupType = (AttractionGroupType) o;
        if (attractionGroupType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attractionGroupType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttractionGroupType{" +
            "id=" + getId() +
            ", groupType='" + getGroupType() + "'" +
            "}";
    }
}
