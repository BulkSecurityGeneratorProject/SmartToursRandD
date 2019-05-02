package com.pa.twb.web.rest.ext.attractionpurchase;

import com.pa.twb.SmarttoursApp;
import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.service.ext.ExtAttractionPurchaseService;
import com.pa.twb.service.ext.processing.MachineLearningTrainerService;
import com.pa.twb.web.rest.TestUtil;
import com.pa.twb.web.rest.errors.ExceptionTranslator;
import com.pa.twb.web.rest.errors.ext.AttractionPurchaseNotFoundException;
import com.pa.twb.web.rest.ext.ExtAttractionPurchaseResource;
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
import java.util.Optional;

import static com.pa.twb.web.rest.TestUtil.createFormattingConversionService;
import static com.pa.twb.web.rest.ext.attractionpurchase.AttractionPurchaseDataUtil.createCreateAttractionPurchaseEntityDTO;
import static com.pa.twb.web.rest.ext.attractionpurchase.AttractionPurchaseDataUtil.createUpdateAttractionPurchaseEntityDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TODO: Update DTOs for relevant data and adjust tests for data accordingly.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {SmarttoursApp.class}
)
@SuppressWarnings("unused")
public class ExtAttractionPurchaseResourceIntTest {
    @Autowired
    private MachineLearningTrainerService machineLearningTrainerService;

    @Autowired
    private ExtAttractionPurchaseRepository extAttractionPurchaseRepository;

    @Autowired
    private ExtAttractionPurchaseService extAttractionPurchaseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionPurchaseMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtAttractionPurchaseResource extAttractionPurchaseResource = new ExtAttractionPurchaseResource(extAttractionPurchaseService, machineLearningTrainerService);
        this.restAttractionPurchaseMockMvc = MockMvcBuilders.standaloneSetup(extAttractionPurchaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void testCreateAttractionPurchase() throws Exception {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionPurchaseRepository.findAll().size();
        this.restAttractionPurchaseMockMvc.perform(post("/api/ext-attraction-purchase")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionPurchaseEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isCreated());

        List<AttractionPurchase> list = extAttractionPurchaseRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate + 1);
        AttractionPurchase test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(DEFAULT_NAME)
    }

    @Test
    @Transactional
    public void testCreateAttractionPurchaseInvalidParent() {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionPurchaseRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionPurchaseMockMvc.perform(post("/api/ext-attraction-purchase")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionPurchaseEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isCreated())).
            hasCause(new AttractionPurchaseNotFoundException());

        List<AttractionPurchase> list = extAttractionPurchaseRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void testUpdateAttractionPurchase() throws Exception {
        // some database setup

        int databaseSizeBeforeUpdate = extAttractionPurchaseRepository.findAll().size();
        this.restAttractionPurchaseMockMvc.perform(put("/api/ext-attraction-purchase")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionPurchaseEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isOk());

        List<AttractionPurchase> list = extAttractionPurchaseRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
        AttractionPurchase test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(UPDATED_NAME)
    }

    @Test
    @Transactional
    public void testUpdateNonExistentAttractionPurchase() {
        int databaseSizeBeforeUpdate = extAttractionPurchaseRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionPurchaseMockMvc.perform(put("/api/ext-attraction-purchase")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionPurchaseEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isOk())).
            hasCause(new AttractionPurchaseNotFoundException());

        List<AttractionPurchase> list = extAttractionPurchaseRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void testGetAttractionPurchase() throws Exception {
        // some database setup

        this.restAttractionPurchaseMockMvc.perform(get("/api/ext-attraction-purchase/{id}", 1L)) //update
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testGetNonExistentAttractionPurchase() {
        assertThatThrownBy(() ->
            this.restAttractionPurchaseMockMvc.perform(get("/api/ext-attraction-purchase/{id}", Long.MAX_VALUE))
                .andExpect(status().isOk())).
            hasCause(new AttractionPurchaseNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllAttractionPurchase() throws Exception {
        // some database setup

        this.restAttractionPurchaseMockMvc.perform(get("/api/ext-attraction-purchase?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testDeleteAttractionPurchase() throws Exception {
        // some database setup

        int databaseSizeBeforeDelete = extAttractionPurchaseRepository.findAll().size();
        this.restAttractionPurchaseMockMvc.perform(delete("/api/ext-attraction-purchase/{id}", 1L))
            .andDo(print())
            .andExpect(status().isNoContent());

        List<AttractionPurchase> list = extAttractionPurchaseRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeDelete - 1);

        Optional<AttractionPurchase> test = extAttractionPurchaseRepository.findById(1L); // update
        assertThat(test.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testDeleteNonExistentAttractionPurchase() {
        assertThatThrownBy(() ->
            this.restAttractionPurchaseMockMvc.perform(delete("/api/ext-attraction-purchase/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent())).
            hasCause(new AttractionPurchaseNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllDeletedAttractionPurchase() throws Exception {
        // some database setup

        this.restAttractionPurchaseMockMvc.perform(get("/api/ext-attraction-purchase/deleted?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testRecoverDeletedAttractionPurchase() throws Exception {
        // some database setup

        this.restAttractionPurchaseMockMvc.perform(post("/api/ext-attraction-purchase/recover/{id}", 1L)) // update
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testRecoverNonExistentAttractionPurchase() {
        assertThatThrownBy(() ->
            this.restAttractionPurchaseMockMvc.perform(post("/api/ext-attraction-purchase/recover/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isOk())).
            hasCause(new AttractionPurchaseNotFoundException());
    }
}
