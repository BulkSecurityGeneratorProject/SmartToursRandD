package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;

import com.pa.twb.domain.AttractionGroupType;
import com.pa.twb.repository.AttractionGroupTypeRepository;
import com.pa.twb.service.AttractionGroupTypeService;
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

import com.pa.twb.domain.enumeration.GroupType;
/**
 * Test class for the AttractionGroupTypeResource REST controller.
 *
 * @see AttractionGroupTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmarttoursApp.class)
public class AttractionGroupTypeResourceIntTest {

    private static final GroupType DEFAULT_GROUP_TYPE = GroupType.NONE;
    private static final GroupType UPDATED_GROUP_TYPE = GroupType.KIDS;

    @Autowired
    private AttractionGroupTypeRepository attractionGroupTypeRepository;

    

    @Autowired
    private AttractionGroupTypeService attractionGroupTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionGroupTypeMockMvc;

    private AttractionGroupType attractionGroupType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttractionGroupTypeResource attractionGroupTypeResource = new AttractionGroupTypeResource(attractionGroupTypeService);
        this.restAttractionGroupTypeMockMvc = MockMvcBuilders.standaloneSetup(attractionGroupTypeResource)
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
    public static AttractionGroupType createEntity(EntityManager em) {
        AttractionGroupType attractionGroupType = new AttractionGroupType()
            .groupType(DEFAULT_GROUP_TYPE);
        return attractionGroupType;
    }

    @Before
    public void initTest() {
        attractionGroupType = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttractionGroupType() throws Exception {
        int databaseSizeBeforeCreate = attractionGroupTypeRepository.findAll().size();

        // Create the AttractionGroupType
        restAttractionGroupTypeMockMvc.perform(post("/api/attraction-group-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionGroupType)))
            .andExpect(status().isCreated());

        // Validate the AttractionGroupType in the database
        List<AttractionGroupType> attractionGroupTypeList = attractionGroupTypeRepository.findAll();
        assertThat(attractionGroupTypeList).hasSize(databaseSizeBeforeCreate + 1);
        AttractionGroupType testAttractionGroupType = attractionGroupTypeList.get(attractionGroupTypeList.size() - 1);
        assertThat(testAttractionGroupType.getGroupType()).isEqualTo(DEFAULT_GROUP_TYPE);
    }

    @Test
    @Transactional
    public void createAttractionGroupTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attractionGroupTypeRepository.findAll().size();

        // Create the AttractionGroupType with an existing ID
        attractionGroupType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttractionGroupTypeMockMvc.perform(post("/api/attraction-group-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionGroupType)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionGroupType in the database
        List<AttractionGroupType> attractionGroupTypeList = attractionGroupTypeRepository.findAll();
        assertThat(attractionGroupTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttractionGroupTypes() throws Exception {
        // Initialize the database
        attractionGroupTypeRepository.saveAndFlush(attractionGroupType);

        // Get all the attractionGroupTypeList
        restAttractionGroupTypeMockMvc.perform(get("/api/attraction-group-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attractionGroupType.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupType").value(hasItem(DEFAULT_GROUP_TYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getAttractionGroupType() throws Exception {
        // Initialize the database
        attractionGroupTypeRepository.saveAndFlush(attractionGroupType);

        // Get the attractionGroupType
        restAttractionGroupTypeMockMvc.perform(get("/api/attraction-group-types/{id}", attractionGroupType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attractionGroupType.getId().intValue()))
            .andExpect(jsonPath("$.groupType").value(DEFAULT_GROUP_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAttractionGroupType() throws Exception {
        // Get the attractionGroupType
        restAttractionGroupTypeMockMvc.perform(get("/api/attraction-group-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttractionGroupType() throws Exception {
        // Initialize the database
        attractionGroupTypeService.save(attractionGroupType);

        int databaseSizeBeforeUpdate = attractionGroupTypeRepository.findAll().size();

        // Update the attractionGroupType
        AttractionGroupType updatedAttractionGroupType = attractionGroupTypeRepository.findById(attractionGroupType.getId()).get();
        // Disconnect from session so that the updates on updatedAttractionGroupType are not directly saved in db
        em.detach(updatedAttractionGroupType);
        updatedAttractionGroupType
            .groupType(UPDATED_GROUP_TYPE);

        restAttractionGroupTypeMockMvc.perform(put("/api/attraction-group-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttractionGroupType)))
            .andExpect(status().isOk());

        // Validate the AttractionGroupType in the database
        List<AttractionGroupType> attractionGroupTypeList = attractionGroupTypeRepository.findAll();
        assertThat(attractionGroupTypeList).hasSize(databaseSizeBeforeUpdate);
        AttractionGroupType testAttractionGroupType = attractionGroupTypeList.get(attractionGroupTypeList.size() - 1);
        assertThat(testAttractionGroupType.getGroupType()).isEqualTo(UPDATED_GROUP_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAttractionGroupType() throws Exception {
        int databaseSizeBeforeUpdate = attractionGroupTypeRepository.findAll().size();

        // Create the AttractionGroupType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttractionGroupTypeMockMvc.perform(put("/api/attraction-group-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attractionGroupType)))
            .andExpect(status().isBadRequest());

        // Validate the AttractionGroupType in the database
        List<AttractionGroupType> attractionGroupTypeList = attractionGroupTypeRepository.findAll();
        assertThat(attractionGroupTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttractionGroupType() throws Exception {
        // Initialize the database
        attractionGroupTypeService.save(attractionGroupType);

        int databaseSizeBeforeDelete = attractionGroupTypeRepository.findAll().size();

        // Get the attractionGroupType
        restAttractionGroupTypeMockMvc.perform(delete("/api/attraction-group-types/{id}", attractionGroupType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AttractionGroupType> attractionGroupTypeList = attractionGroupTypeRepository.findAll();
        assertThat(attractionGroupTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttractionGroupType.class);
        AttractionGroupType attractionGroupType1 = new AttractionGroupType();
        attractionGroupType1.setId(1L);
        AttractionGroupType attractionGroupType2 = new AttractionGroupType();
        attractionGroupType2.setId(attractionGroupType1.getId());
        assertThat(attractionGroupType1).isEqualTo(attractionGroupType2);
        attractionGroupType2.setId(2L);
        assertThat(attractionGroupType1).isNotEqualTo(attractionGroupType2);
        attractionGroupType1.setId(null);
        assertThat(attractionGroupType1).isNotEqualTo(attractionGroupType2);
    }
}
