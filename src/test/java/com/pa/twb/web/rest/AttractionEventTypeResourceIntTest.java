package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;

import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.repository.AttractionEventTypeRepository;
import com.pa.twb.service.AttractionEventTypeService;
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
import java.util.List;


import static com.pa.twb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pa.twb.domain.enumeration.EventType;
/**
 * Test class for the AttractionEventTypeResource REST controller.
 *
 * @see AttractionEventTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmarttoursApp.class)
public class AttractionEventTypeResourceIntTest {

    private static final EventType DEFAULT_EVENT_TYPE = EventType.NONE;
    private static final EventType UPDATED_EVENT_TYPE = EventType.GET_ACTIVE;

    @Autowired
    private AttractionEventTypeRepository attractionEventTypeRepository;

    

    @Autowired
    private AttractionEventTypeService attractionEventTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionEventTypeMockMvc;

    private AttractionEventType attractionEventType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttractionEventTypeResource attractionEventTypeResource = new AttractionEventTypeResource(attractionEventTypeService);
        this.restAttractionEventTypeMockMvc = MockMvcBuilders.standaloneSetup(attractionEventTypeResource)
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
    public static AttractionEventType createEntity(EntityManager em) {
        AttractionEventType attractionEventType = new AttractionEventType()
            .eventType(DEFAULT_EVENT_TYPE);
        return attractionEventType;
    }

    @Before
    public void initTest() {
        attractionEventType = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttractionEventType() throws Exception {
        int databaseSizeBeforeCreate = attractionEventTypeRepository.findAll().size();

        // Create the AttractionEventType
        restAttractionEventTypeMockMvc.perform(post("/api/attraction-event-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionEventType)))
            .andExpect(status().isCreated());

        // Validate the AttractionEventType in the database
        List<AttractionEventType> attractionEventTypeList = attractionEventTypeRepository.findAll();
        assertThat(attractionEventTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AttractionEventType testAttractionEventType = attractionEventTypeList.get(attractionEventTypeList.size() - 1);
        assertThat(testAttractionEventType.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void createAttractionEventTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attractionEventTypeRepository.findAll().size();

        // Create the AttractionEventType with an existing ID
        attractionEventType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttractionEventTypeMockMvc.perform(post("/api/attraction-event-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionEventType)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionEventType in the database
        List<AttractionEventType> attractionEventTypeList = attractionEventTypeRepository.findAll();
        assertThat(attractionEventTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttractionEventTypes() throws Exception {
        // Initialize the database
        attractionEventTypeRepository.saveAndFlush(attractionEventType);

        // Get all the attractionEventTypeList
        restAttractionEventTypeMockMvc.perform(get("/api/attraction-event-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attractionEventType.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getAttractionEventType() throws Exception {
        // Initialize the database
        attractionEventTypeRepository.saveAndFlush(attractionEventType);

        // Get the attractionEventType
        restAttractionEventTypeMockMvc.perform(get("/api/attraction-event-types/{id}", attractionEventType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attractionEventType.getId().intValue()))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAttractionEventType() throws Exception {
        // Get the attractionEventType
        restAttractionEventTypeMockMvc.perform(get("/api/attraction-event-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttractionEventType() throws Exception {
        // Initialize the database
        attractionEventTypeService.save(attractionEventType);

        int databaseSizeBeforeUpdate = attractionEventTypeRepository.findAll().size();

        // Update the attractionEventType
        AttractionEventType updatedAttractionEventType = attractionEventTypeRepository.findById(attractionEventType.getId()).get();
        // Disconnect from session so that the updates on updatedAttractionEventType are not directly saved in db
        em.detach(updatedAttractionEventType);
        updatedAttractionEventType
            .eventType(UPDATED_EVENT_TYPE);

        restAttractionEventTypeMockMvc.perform(put("/api/attraction-event-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttractionEventType)))
            .andExpect(status().isOk());

        // Validate the AttractionEventType in the database
        List<AttractionEventType> attractionEventTypeList = attractionEventTypeRepository.findAll();
        assertThat(attractionEventTypeList).hasSize(databaseSizeBeforeUpdate);
        AttractionEventType testAttractionEventType = attractionEventTypeList.get(attractionEventTypeList.size() - 1);
        assertThat(testAttractionEventType.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttractionEventType() throws Exception {
        int databaseSizeBeforeUpdate = attractionEventTypeRepository.findAll().size();

        // Create the AttractionEventType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttractionEventTypeMockMvc.perform(put("/api/attraction-event-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionEventType)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionEventType in the database
        List<AttractionEventType> attractionEventTypeList = attractionEventTypeRepository.findAll();
        assertThat(attractionEventTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttractionEventType() throws Exception {
        // Initialize the database
        attractionEventTypeService.save(attractionEventType);

        int databaseSizeBeforeDelete = attractionEventTypeRepository.findAll().size();

        // Get the attractionEventType
        restAttractionEventTypeMockMvc.perform(delete("/api/attraction-event-types/{id}", attractionEventType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttractionEventType> attractionEventTypeList = attractionEventTypeRepository.findAll();
        assertThat(attractionEventTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttractionEventType.class);
        AttractionEventType attractionEventType1 = new AttractionEventType();
        attractionEventType1.setId(1L);
        AttractionEventType attractionEventType2 = new AttractionEventType();
        attractionEventType2.setId(attractionEventType1.getId());
        assertThat(attractionEventType1).isEqualTo(attractionEventType2);
        attractionEventType2.setId(2L);
        assertThat(attractionEventType1).isNotEqualTo(attractionEventType2);
        attractionEventType1.setId(null);
        assertThat(attractionEventType1).isNotEqualTo(attractionEventType2);
    }
}
