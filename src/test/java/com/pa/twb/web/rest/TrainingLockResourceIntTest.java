package com.pa.twb.web.rest;

import com.pa.twb.SmarttoursApp;
import com.pa.twb.domain.TrainingLock;
import com.pa.twb.repository.TrainingLockRepository;
import com.pa.twb.service.TrainingLockService;
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

/**
 * Test class for the TrainingLockResource REST controller.
 *
 * @see TrainingLockResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmarttoursApp.class)
public class TrainingLockResourceIntTest {

    private static final Boolean DEFAULT_LOCK = false;
    private static final Boolean UPDATED_LOCK = true;

    @Autowired
    private TrainingLockRepository trainingLockRepository;


    @Autowired
    private TrainingLockService trainingLockService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrainingLockMockMvc;

    private TrainingLock trainingLock;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrainingLock createEntity(EntityManager em) {
        TrainingLock trainingLock = new TrainingLock()
            .lock(DEFAULT_LOCK);
        return trainingLock;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrainingLockResource trainingLockResource = new TrainingLockResource(trainingLockService);
        this.restTrainingLockMockMvc = MockMvcBuilders.standaloneSetup(trainingLockResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        trainingLock = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrainingLock() throws Exception {
        int databaseSizeBeforeCreate = trainingLockRepository.findAll().size();

        // Create the TrainingLock
        restTrainingLockMockMvc.perform(post("/api/training-locks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingLock)))
            .andExpect(status().isCreated());

        // Validate the TrainingLock in the database
        List<TrainingLock> trainingLockList = trainingLockRepository.findAll();
        assertThat(trainingLockList).hasSize(databaseSizeBeforeCreate + 1);
        TrainingLock testTrainingLock = trainingLockList.get(trainingLockList.size() - 1);
        assertThat(testTrainingLock.isLock()).isEqualTo(DEFAULT_LOCK);
    }

    @Test
    @Transactional
    public void createTrainingLockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trainingLockRepository.findAll().size();

        // Create the TrainingLock with an existing ID
        trainingLock.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrainingLockMockMvc.perform(post("/api/training-locks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingLock)))
            .andExpect(status().isBadRequest());

        // Validate the TrainingLock in the database
        List<TrainingLock> trainingLockList = trainingLockRepository.findAll();
        assertThat(trainingLockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrainingLocks() throws Exception {
        // Initialize the database
        trainingLockRepository.saveAndFlush(trainingLock);

        // Get all the trainingLockList
        restTrainingLockMockMvc.perform(get("/api/training-locks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trainingLock.getId().intValue())))
            .andExpect(jsonPath("$.[*].lock").value(hasItem(DEFAULT_LOCK.booleanValue())));
    }


    @Test
    @Transactional
    public void getTrainingLock() throws Exception {
        // Initialize the database
        trainingLockRepository.saveAndFlush(trainingLock);

        // Get the trainingLock
        restTrainingLockMockMvc.perform(get("/api/training-locks/{id}", trainingLock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trainingLock.getId().intValue()))
            .andExpect(jsonPath("$.lock").value(DEFAULT_LOCK.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTrainingLock() throws Exception {
        // Get the trainingLock
        restTrainingLockMockMvc.perform(get("/api/training-locks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrainingLock() throws Exception {
        // Initialize the database
        trainingLockService.save(trainingLock);

        int databaseSizeBeforeUpdate = trainingLockRepository.findAll().size();

        // Update the trainingLock
        TrainingLock updatedTrainingLock = trainingLockRepository.findById(trainingLock.getId()).get();
        // Disconnect from session so that the updates on updatedTrainingLock are not directly saved in db
        em.detach(updatedTrainingLock);
        updatedTrainingLock
            .lock(UPDATED_LOCK);

        restTrainingLockMockMvc.perform(put("/api/training-locks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTrainingLock)))
            .andExpect(status().isOk());

        // Validate the TrainingLock in the database
        List<TrainingLock> trainingLockList = trainingLockRepository.findAll();
        assertThat(trainingLockList).hasSize(databaseSizeBeforeUpdate);
        TrainingLock testTrainingLock = trainingLockList.get(trainingLockList.size() - 1);
        assertThat(testTrainingLock.isLock()).isEqualTo(UPDATED_LOCK);
    }

    @Test
    @Transactional
    public void updateNonExistingTrainingLock() throws Exception {
        int databaseSizeBeforeUpdate = trainingLockRepository.findAll().size();

        // Create the TrainingLock

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrainingLockMockMvc.perform(put("/api/training-locks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trainingLock)))
            .andExpect(status().isBadRequest());

        // Validate the TrainingLock in the database
        List<TrainingLock> trainingLockList = trainingLockRepository.findAll();
        assertThat(trainingLockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrainingLock() throws Exception {
        // Initialize the database
        trainingLockService.save(trainingLock);

        int databaseSizeBeforeDelete = trainingLockRepository.findAll().size();

        // Get the trainingLock
        restTrainingLockMockMvc.perform(delete("/api/training-locks/{id}", trainingLock.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TrainingLock> trainingLockList = trainingLockRepository.findAll();
        assertThat(trainingLockList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrainingLock.class);
        TrainingLock trainingLock1 = new TrainingLock();
        trainingLock1.setId(1L);
        TrainingLock trainingLock2 = new TrainingLock();
        trainingLock2.setId(trainingLock1.getId());
        assertThat(trainingLock1).isEqualTo(trainingLock2);
        trainingLock2.setId(2L);
        assertThat(trainingLock1).isNotEqualTo(trainingLock2);
        trainingLock1.setId(null);
        assertThat(trainingLock1).isNotEqualTo(trainingLock2);
    }
}
