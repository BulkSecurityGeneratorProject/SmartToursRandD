package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.AttractionPurchaseRepository;
import com.pa.twb.service.AttractionPurchaseService;
import com.pa.twb.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.pa.twb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AttractionPurchaseResource REST controller.
 *
 * @see AttractionPurchaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmarttoursApp.class)
public class AttractionPurchaseResourceIntTest {

    private static final Long DEFAULT_ATTRACTION_ID = 1L;
    private static final Long UPDATED_ATTRACTION_ID = 2L;

    private static final Double DEFAULT_USER_DISTANCE = 1D;
    private static final Double UPDATED_USER_DISTANCE = 2D;

    private static final Double DEFAULT_WEATHER_TEMPERATURE = 1D;
    private static final Double UPDATED_WEATHER_TEMPERATURE = 2D;

    private static final Double DEFAULT_WEATHER_MIN_TEMPERATURE = 1D;
    private static final Double UPDATED_WEATHER_MIN_TEMPERATURE = 2D;

    private static final Double DEFAULT_WEATHER_MAX_TEMPERATURE = 1D;
    private static final Double UPDATED_WEATHER_MAX_TEMPERATURE = 2D;

    private static final Double DEFAULT_WEATHER_HUMIDITY = 1D;
    private static final Double UPDATED_WEATHER_HUMIDITY = 2D;

    private static final Boolean DEFAULT_PURCHASED = false;
    private static final Boolean UPDATED_PURCHASED = true;

    @Autowired
    private AttractionPurchaseRepository attractionPurchaseRepository;
    @Mock
    private AttractionPurchaseRepository attractionPurchaseRepositoryMock;
    
    @Mock
    private AttractionPurchaseService attractionPurchaseServiceMock;

    @Autowired
    private AttractionPurchaseService attractionPurchaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionPurchaseMockMvc;

    private AttractionPurchase attractionPurchase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttractionPurchaseResource attractionPurchaseResource = new AttractionPurchaseResource(attractionPurchaseService);
        this.restAttractionPurchaseMockMvc = MockMvcBuilders.standaloneSetup(attractionPurchaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttractionPurchase createEntity(EntityManager em) {
        AttractionPurchase attractionPurchase = new AttractionPurchase()
            .attractionId(DEFAULT_ATTRACTION_ID)
            .userDistance(DEFAULT_USER_DISTANCE)
            .weatherTemperature(DEFAULT_WEATHER_TEMPERATURE)
            .weatherMinTemperature(DEFAULT_WEATHER_MIN_TEMPERATURE)
            .weatherMaxTemperature(DEFAULT_WEATHER_MAX_TEMPERATURE)
            .weatherHumidity(DEFAULT_WEATHER_HUMIDITY)
            .purchased(DEFAULT_PURCHASED);
        return attractionPurchase;
    }

    @Before
    public void initTest() {
        attractionPurchase = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttractionPurchase() throws Exception {
        int databaseSizeBeforeCreate = attractionPurchaseRepository.findAll().size();

        // Create the AttractionPurchase
        restAttractionPurchaseMockMvc.perform(post("/api/attraction-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionPurchase)))
            .andExpect(status().isCreated());

        // Validate the AttractionPurchase in the database
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeCreate + 1);
        AttractionPurchase testAttractionPurchase = attractionPurchaseList.get(attractionPurchaseList.size() - 1);
        assertThat(testAttractionPurchase.getAttractionId()).isEqualTo(DEFAULT_ATTRACTION_ID);
        assertThat(testAttractionPurchase.getUserDistance()).isEqualTo(DEFAULT_USER_DISTANCE);
        assertThat(testAttractionPurchase.getWeatherTemperature()).isEqualTo(DEFAULT_WEATHER_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherMinTemperature()).isEqualTo(DEFAULT_WEATHER_MIN_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherMaxTemperature()).isEqualTo(DEFAULT_WEATHER_MAX_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherHumidity()).isEqualTo(DEFAULT_WEATHER_HUMIDITY);
        assertThat(testAttractionPurchase.isPurchased()).isEqualTo(DEFAULT_PURCHASED);
    }

    @Test
    @Transactional
    public void createAttractionPurchaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attractionPurchaseRepository.findAll().size();

        // Create the AttractionPurchase with an existing ID
        attractionPurchase.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttractionPurchaseMockMvc.perform(post("/api/attraction-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionPurchase)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionPurchase in the database
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttractionPurchases() throws Exception {
        // Initialize the database
        attractionPurchaseRepository.saveAndFlush(attractionPurchase);

        // Get all the attractionPurchaseList
        restAttractionPurchaseMockMvc.perform(get("/api/attraction-purchases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attractionPurchase.getId().intValue())))
            .andExpect(jsonPath("$.[*].attractionId").value(hasItem(DEFAULT_ATTRACTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].userDistance").value(hasItem(DEFAULT_USER_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].weatherTemperature").value(hasItem(DEFAULT_WEATHER_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].weatherMinTemperature").value(hasItem(DEFAULT_WEATHER_MIN_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].weatherMaxTemperature").value(hasItem(DEFAULT_WEATHER_MAX_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].weatherHumidity").value(hasItem(DEFAULT_WEATHER_HUMIDITY.doubleValue())))
            .andExpect(jsonPath("$.[*].purchased").value(hasItem(DEFAULT_PURCHASED.booleanValue())));
    }
    
    public void getAllAttractionPurchasesWithEagerRelationshipsIsEnabled() throws Exception {
        AttractionPurchaseResource attractionPurchaseResource = new AttractionPurchaseResource(attractionPurchaseServiceMock);
        when(attractionPurchaseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAttractionPurchaseMockMvc = MockMvcBuilders.standaloneSetup(attractionPurchaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAttractionPurchaseMockMvc.perform(get("/api/attraction-purchases?eagerload=true"))
        .andExpect(status().isOk());

        verify(attractionPurchaseServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllAttractionPurchasesWithEagerRelationshipsIsNotEnabled() throws Exception {
        AttractionPurchaseResource attractionPurchaseResource = new AttractionPurchaseResource(attractionPurchaseServiceMock);
            when(attractionPurchaseServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAttractionPurchaseMockMvc = MockMvcBuilders.standaloneSetup(attractionPurchaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAttractionPurchaseMockMvc.perform(get("/api/attraction-purchases?eagerload=true"))
        .andExpect(status().isOk());

            verify(attractionPurchaseServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAttractionPurchase() throws Exception {
        // Initialize the database
        attractionPurchaseRepository.saveAndFlush(attractionPurchase);

        // Get the attractionPurchase
        restAttractionPurchaseMockMvc.perform(get("/api/attraction-purchases/{id}", attractionPurchase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attractionPurchase.getId().intValue()))
            .andExpect(jsonPath("$.attractionId").value(DEFAULT_ATTRACTION_ID.intValue()))
            .andExpect(jsonPath("$.userDistance").value(DEFAULT_USER_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.weatherTemperature").value(DEFAULT_WEATHER_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.weatherMinTemperature").value(DEFAULT_WEATHER_MIN_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.weatherMaxTemperature").value(DEFAULT_WEATHER_MAX_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.weatherHumidity").value(DEFAULT_WEATHER_HUMIDITY.doubleValue()))
            .andExpect(jsonPath("$.purchased").value(DEFAULT_PURCHASED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAttractionPurchase() throws Exception {
        // Get the attractionPurchase
        restAttractionPurchaseMockMvc.perform(get("/api/attraction-purchases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttractionPurchase() throws Exception {
        // Initialize the database
        attractionPurchaseService.save(attractionPurchase);

        int databaseSizeBeforeUpdate = attractionPurchaseRepository.findAll().size();

        // Update the attractionPurchase
        AttractionPurchase updatedAttractionPurchase = attractionPurchaseRepository.findById(attractionPurchase.getId()).get();
        // Disconnect from session so that the updates on updatedAttractionPurchase are not directly saved in db
        em.detach(updatedAttractionPurchase);
        updatedAttractionPurchase
            .attractionId(UPDATED_ATTRACTION_ID)
            .userDistance(UPDATED_USER_DISTANCE)
            .weatherTemperature(UPDATED_WEATHER_TEMPERATURE)
            .weatherMinTemperature(UPDATED_WEATHER_MIN_TEMPERATURE)
            .weatherMaxTemperature(UPDATED_WEATHER_MAX_TEMPERATURE)
            .weatherHumidity(UPDATED_WEATHER_HUMIDITY)
            .purchased(UPDATED_PURCHASED);

        restAttractionPurchaseMockMvc.perform(put("/api/attraction-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttractionPurchase)))
            .andExpect(status().isOk());

        // Validate the AttractionPurchase in the database
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeUpdate);
        AttractionPurchase testAttractionPurchase = attractionPurchaseList.get(attractionPurchaseList.size() - 1);
        assertThat(testAttractionPurchase.getAttractionId()).isEqualTo(UPDATED_ATTRACTION_ID);
        assertThat(testAttractionPurchase.getUserDistance()).isEqualTo(UPDATED_USER_DISTANCE);
        assertThat(testAttractionPurchase.getWeatherTemperature()).isEqualTo(UPDATED_WEATHER_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherMinTemperature()).isEqualTo(UPDATED_WEATHER_MIN_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherMaxTemperature()).isEqualTo(UPDATED_WEATHER_MAX_TEMPERATURE);
        assertThat(testAttractionPurchase.getWeatherHumidity()).isEqualTo(UPDATED_WEATHER_HUMIDITY);
        assertThat(testAttractionPurchase.isPurchased()).isEqualTo(UPDATED_PURCHASED);
    }

    @Test
    @Transactional
    public void updateNonExistingAttractionPurchase() throws Exception {
        int databaseSizeBeforeUpdate = attractionPurchaseRepository.findAll().size();

        // Create the AttractionPurchase

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttractionPurchaseMockMvc.perform(put("/api/attraction-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionPurchase)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionPurchase in the database
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttractionPurchase() throws Exception {
        // Initialize the database
        attractionPurchaseService.save(attractionPurchase);

        int databaseSizeBeforeDelete = attractionPurchaseRepository.findAll().size();

        // Get the attractionPurchase
        restAttractionPurchaseMockMvc.perform(delete("/api/attraction-purchases/{id}", attractionPurchase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttractionPurchase.class);
        AttractionPurchase attractionPurchase1 = new AttractionPurchase();
        attractionPurchase1.setId(1L);
        AttractionPurchase attractionPurchase2 = new AttractionPurchase();
        attractionPurchase2.setId(attractionPurchase1.getId());
        assertThat(attractionPurchase1).isEqualTo(attractionPurchase2);
        attractionPurchase2.setId(2L);
        assertThat(attractionPurchase1).isNotEqualTo(attractionPurchase2);
        attractionPurchase1.setId(null);
        assertThat(attractionPurchase1).isNotEqualTo(attractionPurchase2);
    }
}
