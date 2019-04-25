package com.pa.twb.web.rest.ext.attractioneventtype;

import com.pa.twb.SmarttoursApp;
import com.pa.twb.domain.AttractionEventType;
import com.pa.twb.repository.ext.ExtAttractionEventTypeRepository;
import com.pa.twb.service.ext.ExtAttractionEventTypeService;
import com.pa.twb.web.rest.TestUtil;
import com.pa.twb.web.rest.errors.ExceptionTranslator;
import com.pa.twb.web.rest.errors.ext.AttractionEventTypeNotFoundException;
import com.pa.twb.web.rest.ext.ExtAttractionEventTypeResource;
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
import static com.pa.twb.web.rest.ext.attractioneventtype.AttractionEventTypeDataUtil.createCreateAttractionEventTypeEntityDTO;
import static com.pa.twb.web.rest.ext.attractioneventtype.AttractionEventTypeDataUtil.createUpdateAttractionEventTypeEntityDTO;
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
public class ExtAttractionEventTypeResourceIntTest {
    @Autowired
    private ExtAttractionEventTypeRepository extAttractionEventTypeRepository;

    @Autowired
    private ExtAttractionEventTypeService extAttractionEventTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttractionEventTypeMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtAttractionEventTypeResource extAttractionEventTypeResource = new ExtAttractionEventTypeResource(extAttractionEventTypeService);
        this.restAttractionEventTypeMockMvc = MockMvcBuilders.standaloneSetup(extAttractionEventTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void testCreateAttractionEventType() throws Exception {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionEventTypeRepository.findAll().size();
        this.restAttractionEventTypeMockMvc.perform(post("/api/ext-attraction-event-type")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionEventTypeEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isCreated());

        List<AttractionEventType> list = extAttractionEventTypeRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate + 1);
        AttractionEventType test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(DEFAULT_NAME)
    }

    @Test
    @Transactional
    public void testCreateAttractionEventTypeInvalidParent() {
        // some database setup

        int databaseSizeBeforeCreate = extAttractionEventTypeRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionEventTypeMockMvc.perform(post("/api/ext-attraction-event-type")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createCreateAttractionEventTypeEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isCreated())).
            hasCause(new AttractionEventTypeNotFoundException());

        List<AttractionEventType> list = extAttractionEventTypeRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void testUpdateAttractionEventType() throws Exception {
        // some database setup

        int databaseSizeBeforeUpdate = extAttractionEventTypeRepository.findAll().size();
        this.restAttractionEventTypeMockMvc.perform(put("/api/ext-attraction-event-type")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionEventTypeEntityDTO(1L)))) //update
            .andDo(print())
            .andExpect(status().isOk());

        List<AttractionEventType> list = extAttractionEventTypeRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
        AttractionEventType test = list.get(list.size() - 1);
        // assertThat(test.getName()).isEqualTo(UPDATED_NAME)
    }

    @Test
    @Transactional
    public void testUpdateNonExistentAttractionEventType() {
        int databaseSizeBeforeUpdate = extAttractionEventTypeRepository.findAll().size();
        assertThatThrownBy(() ->
            this.restAttractionEventTypeMockMvc.perform(put("/api/ext-attraction-event-type")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(createUpdateAttractionEventTypeEntityDTO(Long.MAX_VALUE))))
                .andExpect(status().isOk())).
            hasCause(new AttractionEventTypeNotFoundException());

        List<AttractionEventType> list = extAttractionEventTypeRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void testGetAttractionEventType() throws Exception {
        // some database setup

        this.restAttractionEventTypeMockMvc.perform(get("/api/ext-attraction-event-type/{id}", 1L)) //update
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testGetNonExistentAttractionEventType() {
        assertThatThrownBy(() ->
            this.restAttractionEventTypeMockMvc.perform(get("/api/ext-attraction-event-type/{id}", Long.MAX_VALUE))
                .andExpect(status().isOk())).
            hasCause(new AttractionEventTypeNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllAttractionEventType() throws Exception {
        // some database setup

        this.restAttractionEventTypeMockMvc.perform(get("/api/ext-attraction-event-type?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testDeleteAttractionEventType() throws Exception {
        // some database setup

        int databaseSizeBeforeDelete = extAttractionEventTypeRepository.findAll().size();
        this.restAttractionEventTypeMockMvc.perform(delete("/api/ext-attraction-event-type/{id}", 1L))
            .andDo(print())
            .andExpect(status().isNoContent());

        List<AttractionEventType> list = extAttractionEventTypeRepository.findAll();
        assertThat(list).hasSize(databaseSizeBeforeDelete - 1);

        Optional<AttractionEventType> test = extAttractionEventTypeRepository.findById(1L); // update
        assertThat(test.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testDeleteNonExistentAttractionEventType() {
        assertThatThrownBy(() ->
            this.restAttractionEventTypeMockMvc.perform(delete("/api/ext-attraction-event-type/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent())).
            hasCause(new AttractionEventTypeNotFoundException());
    }

    @Test
    @Transactional
    public void testGetAllDeletedAttractionEventType() throws Exception {
        // some database setup

        this.restAttractionEventTypeMockMvc.perform(get("/api/ext-attraction-event-type/deleted?sort=id,desc"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(1L))); //update
        // .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void testRecoverDeletedAttractionEventType() throws Exception {
        // some database setup

        this.restAttractionEventTypeMockMvc.perform(post("/api/ext-attraction-event-type/recover/{id}", 1L)) // update
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(1L)); //update
        // .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void testRecoverNonExistentAttractionEventType() {
        assertThatThrownBy(() ->
            this.restAttractionEventTypeMockMvc.perform(post("/api/ext-attraction-event-type/recover/{id}", Long.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isOk())).
            hasCause(new AttractionEventTypeNotFoundException());
    }
}
