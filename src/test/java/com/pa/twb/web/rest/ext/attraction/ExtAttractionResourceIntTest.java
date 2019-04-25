package com.pa.twb.web.rest.ext.attraction;

import com.pa.twb.SmarttoursApp;
import com.pa.twb.domain.Attraction;
import com.pa.twb.repository.ext.ExtAttractionRepository;
import com.pa.twb.service.ext.ExtAttractionService;
import com.pa.twb.web.rest.TestUtil;
import com.pa.twb.web.rest.errors.ExceptionTranslator;
import com.pa.twb.web.rest.errors.ext.AttractionNotFoundException;
import com.pa.twb.web.rest.ext.ExtAttractionResource;
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
import static com.pa.twb.web.rest.ext.attraction.AttractionDataUtil.createCreateAttractionEntityDTO;
import static com.pa.twb.web.rest.ext.attraction.AttractionDataUtil.createUpdateAttractionEntityDTO;
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
public class ExtAttractionResourceIntTest {
    @Autowired
    private ExtAttractionRepository extAttractionRepository;

    @Autowired
    private ExtAttractionService extAttractionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtAttractionResource extAttractionResource = new ExtAttractionResource(extAttractionService);
        this.restAttractionMockMvc = MockMvcBuilders.standaloneSetup(extAttractionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void testCreateAttraction() throws Exception {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionRepository.findAll().size();
        this.restAttractionMockMvc.perform(post("/api/ext-attraction")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isCreated());

        List<Attraction> list = extAttractionRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate + 1);
        Attraction test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(DEFAULT_NAME)
    }

    @Test
    @Transactional
    public void testCreateAttractionInvalidParent() {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionMockMvc.perform(post("/api/ext-attraction")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isCreated())).
            hasCause(new AttractionNotFoundException());

        List<Attraction> list = extAttractionRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void testUpdateAttraction() throws Exception {
        // some database setup

        int databaseSizeBeforeUpdate = extAttractionRepository.findAll().size();
        this.restAttractionMockMvc.perform(put("/api/ext-attraction")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isOk());

        List<Attraction> list = extAttractionRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
        Attraction test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(UPDATED_NAME)
    }

    @Test
    @Transactional
    public void testUpdateNonExistentAttraction() {
        int databaseSizeBeforeUpdate = extAttractionRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionMockMvc.perform(put("/api/ext-attraction")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isOk())).
            hasCause(new AttractionNotFoundException());

        List<Attraction> list = extAttractionRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void testGetAttraction() throws Exception {
        // some database setup

        this.restAttractionMockMvc.perform(get("/api/ext-attraction/{id}", 1L)) //update
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testGetNonExistentAttraction() {
        assertThatThrownBy(() ->
            this.restAttractionMockMvc.perform(get("/api/ext-attraction/{id}", Long.MAX_VALUE))
                .andExpect(status().isOk())).
            hasCause(new AttractionNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllAttraction() throws Exception {
        // some database setup

        this.restAttractionMockMvc.perform(get("/api/ext-attraction?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testDeleteAttraction() throws Exception {
        // some database setup

        int databaseSizeBeforeDelete = extAttractionRepository.findAll().size();
        this.restAttractionMockMvc.perform(delete("/api/ext-attraction/{id}", 1L))
            .andDo(print())
            .andExpect(status().isNoContent());

        List<Attraction> list = extAttractionRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeDelete - 1);

        Optional<Attraction> test = extAttractionRepository.findById(1L); // update
        assertThat(test.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testDeleteNonExistentAttraction() {
        assertThatThrownBy(() ->
            this.restAttractionMockMvc.perform(delete("/api/ext-attraction/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent())).
            hasCause(new AttractionNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllDeletedAttraction() throws Exception {
        // some database setup

        this.restAttractionMockMvc.perform(get("/api/ext-attraction/deleted?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testRecoverDeletedAttraction() throws Exception {
        // some database setup

        this.restAttractionMockMvc.perform(post("/api/ext-attraction/recover/{id}", 1L)) // update
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testRecoverNonExistentAttraction() {
        assertThatThrownBy(() ->
            this.restAttractionMockMvc.perform(post("/api/ext-attraction/recover/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isOk())).
            hasCause(new AttractionNotFoundException());
    }
}
