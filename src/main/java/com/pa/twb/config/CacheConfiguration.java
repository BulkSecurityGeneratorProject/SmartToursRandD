package com.pa.twb.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.pa.twb.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.pa.twb.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Attraction.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Attraction.class.getName() + ".attractionGroupTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Attraction.class.getName() + ".attractionEventTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionGroupType.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionEventType.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionPurchase.class.getName(), jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionPurchase.class.getName() + ".attractionGroupTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionPurchase.class.getName() + ".attractionEventTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionGroupType.class.getName() + ".attractions", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionGroupType.class.getName() + ".attractionPurchases", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionEventType.class.getName() + ".attractions", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionEventType.class.getName() + ".attractionPurchases", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Attraction.class.getName() + ".groupTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.Attraction.class.getName() + ".eventTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionPurchase.class.getName() + ".groupTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionPurchase.class.getName() + ".eventTypes", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionGroupType.class.getName() + ".purchases", jcacheConfiguration);
            cm.createCache(com.pa.twb.domain.AttractionEventType.class.getName() + ".purchases", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
