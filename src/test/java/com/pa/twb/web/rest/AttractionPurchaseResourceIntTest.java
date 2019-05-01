package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.AttractionPurchaseRepository;
import com.pa.twb.service.AttractionPurchaseService;
import com.pa.twb.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.pa.twb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
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

    private static final String DEFAULT_TRAVELING = "AAAAAAAAAA";
    private static final String UPDATED_TRAVELING = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY = "BBBBBBBBBB";

    private static final Double DEFAULT_USER_DISTANCE = 1D;
    private static final Double UPDATED_USER_DISTANCE = 2D;

    private static final Double DEFAULT_USER_LATITUDE = 1D;
    private static final Double UPDATED_USER_LATITUDE = 2D;

    private static final Double DEFAULT_USER_LONGITUDE = 1D;
    private static final Double UPDATED_USER_LONGITUDE = 2D;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ACTION_TAKEN_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTION_TAKEN_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ACTION_TAKEN = false;
    private static final Boolean UPDATED_ACTION_TAKEN = true;

    @Autowired
    private AttractionPurchaseRepository attractionPurchaseRepository;

    

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
            .traveling(DEFAULT_TRAVELING)
            .activity(DEFAULT_ACTIVITY)
            .userDistance(DEFAULT_USER_DISTANCE)
            .userLatitude(DEFAULT_USER_LATITUDE)
            .userLongitude(DEFAULT_USER_LONGITUDE)
            .createdAt(DEFAULT_CREATED_AT)
            .actionTakenAt(DEFAULT_ACTION_TAKEN_AT)
            .actionTaken(DEFAULT_ACTION_TAKEN);
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
        assertThat(testAttractionPurchase.getTraveling()).isEqualTo(DEFAULT_TRAVELING);
        assertThat(testAttractionPurchase.getActivity()).isEqualTo(DEFAULT_ACTIVITY);
        assertThat(testAttractionPurchase.getUserDistance()).isEqualTo(DEFAULT_USER_DISTANCE);
        assertThat(testAttractionPurchase.getUserLatitude()).isEqualTo(DEFAULT_USER_LATITUDE);
        assertThat(testAttractionPurchase.getUserLongitude()).isEqualTo(DEFAULT_USER_LONGITUDE);
        assertThat(testAttractionPurchase.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAttractionPurchase.getActionTakenAt()).isEqualTo(DEFAULT_ACTION_TAKEN_AT);
        assertThat(testAttractionPurchase.isActionTaken()).isEqualTo(DEFAULT_ACTION_TAKEN);
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
            .andExpect(jsonPath("$.[*].traveling").value(hasItem(DEFAULT_TRAVELING.toString())))
            .andExpect(jsonPath("$.[*].activity").value(hasItem(DEFAULT_ACTIVITY.toString())))
            .andExpect(jsonPath("$.[*].userDistance").value(hasItem(DEFAULT_USER_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].userLatitude").value(hasItem(DEFAULT_USER_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].userLongitude").value(hasItem(DEFAULT_USER_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].actionTakenAt").value(hasItem(DEFAULT_ACTION_TAKEN_AT.toString())))
            .andExpect(jsonPath("$.[*].actionTaken").value(hasItem(DEFAULT_ACTION_TAKEN.booleanValue())));
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
            .andExpect(jsonPath("$.traveling").value(DEFAULT_TRAVELING.toString()))
            .andExpect(jsonPath("$.activity").value(DEFAULT_ACTIVITY.toString()))
            .andExpect(jsonPath("$.userDistance").value(DEFAULT_USER_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.userLatitude").value(DEFAULT_USER_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.userLongitude").value(DEFAULT_USER_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.actionTakenAt").value(DEFAULT_ACTION_TAKEN_AT.toString()))
            .andExpect(jsonPath("$.actionTaken").value(DEFAULT_ACTION_TAKEN.booleanValue()));
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
            .traveling(UPDATED_TRAVELING)
            .activity(UPDATED_ACTIVITY)
            .userDistance(UPDATED_USER_DISTANCE)
            .userLatitude(UPDATED_USER_LATITUDE)
            .userLongitude(UPDATED_USER_LONGITUDE)
            .createdAt(UPDATED_CREATED_AT)
            .actionTakenAt(UPDATED_ACTION_TAKEN_AT)
            .actionTaken(UPDATED_ACTION_TAKEN);

        restAttractionPurchaseMockMvc.perform(put("/api/attraction-purchases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttractionPurchase)))
            .andExpect(status().isOk());

        // Validate the AttractionPurchase in the database
        List<AttractionPurchase> attractionPurchaseList = attractionPurchaseRepository.findAll();
        assertThat(attractionPurchaseList).hasSize(databaseSizeBeforeUpdate);
        AttractionPurchase testAttractionPurchase = attractionPurchaseList.get(attractionPurchaseList.size() - 1);
        assertThat(testAttractionPurchase.getTraveling()).isEqualTo(UPDATED_TRAVELING);
        assertThat(testAttractionPurchase.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testAttractionPurchase.getUserDistance()).isEqualTo(UPDATED_USER_DISTANCE);
        assertThat(testAttractionPurchase.getUserLatitude()).isEqualTo(UPDATED_USER_LATITUDE);
        assertThat(testAttractionPurchase.getUserLongitude()).isEqualTo(UPDATED_USER_LONGITUDE);
        assertThat(testAttractionPurchase.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAttractionPurchase.getActionTakenAt()).isEqualTo(UPDATED_ACTION_TAKEN_AT);
        assertThat(testAttractionPurchase.isActionTaken()).isEqualTo(UPDATED_ACTION_TAKEN);
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
