package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;

import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.AttractionRepository;
import com.pa.twb.service.AttractionService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import static com.pa.twb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AttractionResource REST controller.
 *
 * @see AttractionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmarttoursApp.class)
public class AttractionResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBTITLE = "AAAAAAAAAA";
    private static final String UPDATED_SUBTITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final BigDecimal DEFAULT_ADULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ADULT_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CHILD_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CHILD_PRICE = new BigDecimal(2);

    private static final Boolean DEFAULT_ACCESSIBLE = false;
    private static final Boolean UPDATED_ACCESSIBLE = true;

    private static final Boolean DEFAULT_FACILITIES = false;
    private static final Boolean UPDATED_FACILITIES = true;

    private static final Instant DEFAULT_OPEN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OPEN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLOSE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AttractionRepository attractionRepository;
    @Mock
    private AttractionRepository attractionRepositoryMock;
    
    @Mock
    private AttractionService attractionServiceMock;

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionMockMvc;

    private Attraction attraction;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttractionResource attractionResource = new AttractionResource(attractionService);
        this.restAttractionMockMvc = MockMvcBuilders.standaloneSetup(attractionResource)
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
    public static Attraction createEntity(EntityManager em) {
        Attraction attraction = new Attraction()
            .name(DEFAULT_NAME)
            .subtitle(DEFAULT_SUBTITLE)
            .description(DEFAULT_DESCRIPTION)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .adultPrice(DEFAULT_ADULT_PRICE)
            .childPrice(DEFAULT_CHILD_PRICE)
            .accessible(DEFAULT_ACCESSIBLE)
            .facilities(DEFAULT_FACILITIES)
            .openTime(DEFAULT_OPEN_TIME)
            .closeTime(DEFAULT_CLOSE_TIME);
        return attraction;
    }

    @Before
    public void initTest() {
        attraction = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttraction() throws Exception {
        int databaseSizeBeforeCreate = attractionRepository.findAll().size();

        // Create the Attraction
        restAttractionMockMvc.perform(post("/api/attractions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attraction)))
            .andExpect(status().isCreated());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeCreate + 1);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAttraction.getSubtitle()).isEqualTo(DEFAULT_SUBTITLE);
        assertThat(testAttraction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAttraction.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testAttraction.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testAttraction.getAdultPrice()).isEqualTo(DEFAULT_ADULT_PRICE);
        assertThat(testAttraction.getChildPrice()).isEqualTo(DEFAULT_CHILD_PRICE);
        assertThat(testAttraction.isAccessible()).isEqualTo(DEFAULT_ACCESSIBLE);
        assertThat(testAttraction.isFacilities()).isEqualTo(DEFAULT_FACILITIES);
        assertThat(testAttraction.getOpenTime()).isEqualTo(DEFAULT_OPEN_TIME);
        assertThat(testAttraction.getCloseTime()).isEqualTo(DEFAULT_CLOSE_TIME);
    }

    @Test
    @Transactional
    public void createAttractionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attractionRepository.findAll().size();

        // Create the Attraction with an existing ID
        attraction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttractionMockMvc.perform(post("/api/attractions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attraction)))
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttractions() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        // Get all the attractionList
        restAttractionMockMvc.perform(get("/api/attractions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attraction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].subtitle").value(hasItem(DEFAULT_SUBTITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].adultPrice").value(hasItem(DEFAULT_ADULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].childPrice").value(hasItem(DEFAULT_CHILD_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].accessible").value(hasItem(DEFAULT_ACCESSIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].facilities").value(hasItem(DEFAULT_FACILITIES.booleanValue())))
            .andExpect(jsonPath("$.[*].openTime").value(hasItem(DEFAULT_OPEN_TIME.toString())))
            .andExpect(jsonPath("$.[*].closeTime").value(hasItem(DEFAULT_CLOSE_TIME.toString())));
    }
    
    public void getAllAttractionsWithEagerRelationshipsIsEnabled() throws Exception {
        AttractionResource attractionResource = new AttractionResource(attractionServiceMock);
        when(attractionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAttractionMockMvc = MockMvcBuilders.standaloneSetup(attractionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAttractionMockMvc.perform(get("/api/attractions?eagerload=true"))
        .andExpect(status().isOk());

        verify(attractionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllAttractionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        AttractionResource attractionResource = new AttractionResource(attractionServiceMock);
            when(attractionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAttractionMockMvc = MockMvcBuilders.standaloneSetup(attractionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAttractionMockMvc.perform(get("/api/attractions?eagerload=true"))
        .andExpect(status().isOk());

            verify(attractionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAttraction() throws Exception {
        // Initialize the database
        attractionRepository.saveAndFlush(attraction);

        // Get the attraction
        restAttractionMockMvc.perform(get("/api/attractions/{id}", attraction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attraction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.subtitle").value(DEFAULT_SUBTITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.adultPrice").value(DEFAULT_ADULT_PRICE.intValue()))
            .andExpect(jsonPath("$.childPrice").value(DEFAULT_CHILD_PRICE.intValue()))
            .andExpect(jsonPath("$.accessible").value(DEFAULT_ACCESSIBLE.booleanValue()))
            .andExpect(jsonPath("$.facilities").value(DEFAULT_FACILITIES.booleanValue()))
            .andExpect(jsonPath("$.openTime").value(DEFAULT_OPEN_TIME.toString()))
            .andExpect(jsonPath("$.closeTime").value(DEFAULT_CLOSE_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAttraction() throws Exception {
        // Get the attraction
        restAttractionMockMvc.perform(get("/api/attractions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttraction() throws Exception {
        // Initialize the database
        attractionService.save(attraction);

        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();

        // Update the attraction
        Attraction updatedAttraction = attractionRepository.findById(attraction.getId()).get();
        // Disconnect from session so that the updates on updatedAttraction are not directly saved in db
        em.detach(updatedAttraction);
        updatedAttraction
            .name(UPDATED_NAME)
            .subtitle(UPDATED_SUBTITLE)
            .description(UPDATED_DESCRIPTION)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .adultPrice(UPDATED_ADULT_PRICE)
            .childPrice(UPDATED_CHILD_PRICE)
            .accessible(UPDATED_ACCESSIBLE)
            .facilities(UPDATED_FACILITIES)
            .openTime(UPDATED_OPEN_TIME)
            .closeTime(UPDATED_CLOSE_TIME);

        restAttractionMockMvc.perform(put("/api/attractions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttraction)))
            .andExpect(status().isOk());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
        Attraction testAttraction = attractionList.get(attractionList.size() - 1);
        assertThat(testAttraction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAttraction.getSubtitle()).isEqualTo(UPDATED_SUBTITLE);
        assertThat(testAttraction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAttraction.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testAttraction.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testAttraction.getAdultPrice()).isEqualTo(UPDATED_ADULT_PRICE);
        assertThat(testAttraction.getChildPrice()).isEqualTo(UPDATED_CHILD_PRICE);
        assertThat(testAttraction.isAccessible()).isEqualTo(UPDATED_ACCESSIBLE);
        assertThat(testAttraction.isFacilities()).isEqualTo(UPDATED_FACILITIES);
        assertThat(testAttraction.getOpenTime()).isEqualTo(UPDATED_OPEN_TIME);
        assertThat(testAttraction.getCloseTime()).isEqualTo(UPDATED_CLOSE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingAttraction() throws Exception {
        int databaseSizeBeforeUpdate = attractionRepository.findAll().size();

        // Create the Attraction

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttractionMockMvc.perform(put("/api/attractions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attraction)))
            .andExpect(status().isBadRequest());

        // Validate the Attraction in the database
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttraction() throws Exception {
        // Initialize the database
        attractionService.save(attraction);

        int databaseSizeBeforeDelete = attractionRepository.findAll().size();

        // Get the attraction
        restAttractionMockMvc.perform(delete("/api/attractions/{id}", attraction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Attraction> attractionList = attractionRepository.findAll();
        assertThat(attractionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attraction.class);
        Attraction attraction1 = new Attraction();
        attraction1.setId(1L);
        Attraction attraction2 = new Attraction();
        attraction2.setId(attraction1.getId());
        assertThat(attraction1).isEqualTo(attraction2);
        attraction2.setId(2L);
        assertThat(attraction1).isNotEqualTo(attraction2);
        attraction1.setId(null);
        assertThat(attraction1).isNotEqualTo(attraction2);
    }
}
