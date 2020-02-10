package org.jhipster.web.rest;

import org.jhipster.JhipsterApp;
import org.jhipster.domain.Turnstile;
import org.jhipster.repository.TurnstileRepository;
import org.jhipster.service.TurnstileService;
import org.jhipster.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link TurnstileResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class TurnstileResourceIT {

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_TB_CONTROLLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TB_CONTROLLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TB_DISPLAY_ID = "AAAAAAAAAA";
    private static final String UPDATED_TB_DISPLAY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CAMERA_ID = "AAAAAAAAAA";
    private static final String UPDATED_CAMERA_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_X_1 = 1D;
    private static final Double UPDATED_X_1 = 2D;

    private static final Double DEFAULT_Y_1 = 1D;
    private static final Double UPDATED_Y_1 = 2D;

    private static final Double DEFAULT_X_2 = 1D;
    private static final Double UPDATED_X_2 = 2D;

    private static final Double DEFAULT_Y_2 = 1D;
    private static final Double UPDATED_Y_2 = 2D;

    @Autowired
    private TurnstileRepository turnstileRepository;

    @Autowired
    private TurnstileService turnstileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTurnstileMockMvc;

    private Turnstile turnstile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TurnstileResource turnstileResource = new TurnstileResource(turnstileService);
        this.restTurnstileMockMvc = MockMvcBuilders.standaloneSetup(turnstileResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Turnstile createEntity(EntityManager em) {
        Turnstile turnstile = new Turnstile()
            .identifier(DEFAULT_IDENTIFIER)
            .tbControllerId(DEFAULT_TB_CONTROLLER_ID)
            .tbDisplayId(DEFAULT_TB_DISPLAY_ID)
            .cameraId(DEFAULT_CAMERA_ID)
            .x1(DEFAULT_X_1)
            .y1(DEFAULT_Y_1)
            .x2(DEFAULT_X_2)
            .y2(DEFAULT_Y_2);
        return turnstile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Turnstile createUpdatedEntity(EntityManager em) {
        Turnstile turnstile = new Turnstile()
            .identifier(UPDATED_IDENTIFIER)
            .tbControllerId(UPDATED_TB_CONTROLLER_ID)
            .tbDisplayId(UPDATED_TB_DISPLAY_ID)
            .cameraId(UPDATED_CAMERA_ID)
            .x1(UPDATED_X_1)
            .y1(UPDATED_Y_1)
            .x2(UPDATED_X_2)
            .y2(UPDATED_Y_2);
        return turnstile;
    }

    @BeforeEach
    public void initTest() {
        turnstile = createEntity(em);
    }

    @Test
    @Transactional
    public void createTurnstile() throws Exception {
        int databaseSizeBeforeCreate = turnstileRepository.findAll().size();

        // Create the Turnstile
        restTurnstileMockMvc.perform(post("/api/turnstiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turnstile)))
            .andExpect(status().isCreated());

        // Validate the Turnstile in the database
        List<Turnstile> turnstileList = turnstileRepository.findAll();
        assertThat(turnstileList).hasSize(databaseSizeBeforeCreate + 1);
        Turnstile testTurnstile = turnstileList.get(turnstileList.size() - 1);
        assertThat(testTurnstile.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testTurnstile.getTbControllerId()).isEqualTo(DEFAULT_TB_CONTROLLER_ID);
        assertThat(testTurnstile.getTbDisplayId()).isEqualTo(DEFAULT_TB_DISPLAY_ID);
        assertThat(testTurnstile.getCameraId()).isEqualTo(DEFAULT_CAMERA_ID);
        assertThat(testTurnstile.getx1()).isEqualTo(DEFAULT_X_1);
        assertThat(testTurnstile.gety1()).isEqualTo(DEFAULT_Y_1);
        assertThat(testTurnstile.getx2()).isEqualTo(DEFAULT_X_2);
        assertThat(testTurnstile.gety2()).isEqualTo(DEFAULT_Y_2);
    }

    @Test
    @Transactional
    public void createTurnstileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = turnstileRepository.findAll().size();

        // Create the Turnstile with an existing ID
        turnstile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTurnstileMockMvc.perform(post("/api/turnstiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turnstile)))
            .andExpect(status().isBadRequest());

        // Validate the Turnstile in the database
        List<Turnstile> turnstileList = turnstileRepository.findAll();
        assertThat(turnstileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTurnstiles() throws Exception {
        // Initialize the database
        turnstileRepository.saveAndFlush(turnstile);

        // Get all the turnstileList
        restTurnstileMockMvc.perform(get("/api/turnstiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(turnstile.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].tbControllerId").value(hasItem(DEFAULT_TB_CONTROLLER_ID.toString())))
            .andExpect(jsonPath("$.[*].tbDisplayId").value(hasItem(DEFAULT_TB_DISPLAY_ID.toString())))
            .andExpect(jsonPath("$.[*].cameraId").value(hasItem(DEFAULT_CAMERA_ID.toString())))
            .andExpect(jsonPath("$.[*].x1").value(hasItem(DEFAULT_X_1.doubleValue())))
            .andExpect(jsonPath("$.[*].y1").value(hasItem(DEFAULT_Y_1.doubleValue())))
            .andExpect(jsonPath("$.[*].x2").value(hasItem(DEFAULT_X_2.doubleValue())))
            .andExpect(jsonPath("$.[*].y2").value(hasItem(DEFAULT_Y_2.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTurnstile() throws Exception {
        // Initialize the database
        turnstileRepository.saveAndFlush(turnstile);

        // Get the turnstile
        restTurnstileMockMvc.perform(get("/api/turnstiles/{id}", turnstile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(turnstile.getId().intValue()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.tbControllerId").value(DEFAULT_TB_CONTROLLER_ID.toString()))
            .andExpect(jsonPath("$.tbDisplayId").value(DEFAULT_TB_DISPLAY_ID.toString()))
            .andExpect(jsonPath("$.cameraId").value(DEFAULT_CAMERA_ID.toString()))
            .andExpect(jsonPath("$.x1").value(DEFAULT_X_1.doubleValue()))
            .andExpect(jsonPath("$.y1").value(DEFAULT_Y_1.doubleValue()))
            .andExpect(jsonPath("$.x2").value(DEFAULT_X_2.doubleValue()))
            .andExpect(jsonPath("$.y2").value(DEFAULT_Y_2.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTurnstile() throws Exception {
        // Get the turnstile
        restTurnstileMockMvc.perform(get("/api/turnstiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTurnstile() throws Exception {
        // Initialize the database
        turnstileService.save(turnstile);

        int databaseSizeBeforeUpdate = turnstileRepository.findAll().size();

        // Update the turnstile
        Turnstile updatedTurnstile = turnstileRepository.findById(turnstile.getId()).get();
        // Disconnect from session so that the updates on updatedTurnstile are not directly saved in db
        em.detach(updatedTurnstile);
        updatedTurnstile
            .identifier(UPDATED_IDENTIFIER)
            .tbControllerId(UPDATED_TB_CONTROLLER_ID)
            .tbDisplayId(UPDATED_TB_DISPLAY_ID)
            .cameraId(UPDATED_CAMERA_ID)
            .x1(UPDATED_X_1)
            .y1(UPDATED_Y_1)
            .x2(UPDATED_X_2)
            .y2(UPDATED_Y_2);

        restTurnstileMockMvc.perform(put("/api/turnstiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTurnstile)))
            .andExpect(status().isOk());

        // Validate the Turnstile in the database
        List<Turnstile> turnstileList = turnstileRepository.findAll();
        assertThat(turnstileList).hasSize(databaseSizeBeforeUpdate);
        Turnstile testTurnstile = turnstileList.get(turnstileList.size() - 1);
        assertThat(testTurnstile.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testTurnstile.getTbControllerId()).isEqualTo(UPDATED_TB_CONTROLLER_ID);
        assertThat(testTurnstile.getTbDisplayId()).isEqualTo(UPDATED_TB_DISPLAY_ID);
        assertThat(testTurnstile.getCameraId()).isEqualTo(UPDATED_CAMERA_ID);
        assertThat(testTurnstile.getx1()).isEqualTo(UPDATED_X_1);
        assertThat(testTurnstile.gety1()).isEqualTo(UPDATED_Y_1);
        assertThat(testTurnstile.getx2()).isEqualTo(UPDATED_X_2);
        assertThat(testTurnstile.gety2()).isEqualTo(UPDATED_Y_2);
    }

    @Test
    @Transactional
    public void updateNonExistingTurnstile() throws Exception {
        int databaseSizeBeforeUpdate = turnstileRepository.findAll().size();

        // Create the Turnstile

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTurnstileMockMvc.perform(put("/api/turnstiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(turnstile)))
            .andExpect(status().isBadRequest());

        // Validate the Turnstile in the database
        List<Turnstile> turnstileList = turnstileRepository.findAll();
        assertThat(turnstileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTurnstile() throws Exception {
        // Initialize the database
        turnstileService.save(turnstile);

        int databaseSizeBeforeDelete = turnstileRepository.findAll().size();

        // Delete the turnstile
        restTurnstileMockMvc.perform(delete("/api/turnstiles/{id}", turnstile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Turnstile> turnstileList = turnstileRepository.findAll();
        assertThat(turnstileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Turnstile.class);
        Turnstile turnstile1 = new Turnstile();
        turnstile1.setId(1L);
        Turnstile turnstile2 = new Turnstile();
        turnstile2.setId(turnstile1.getId());
        assertThat(turnstile1).isEqualTo(turnstile2);
        turnstile2.setId(2L);
        assertThat(turnstile1).isNotEqualTo(turnstile2);
        turnstile1.setId(null);
        assertThat(turnstile1).isNotEqualTo(turnstile2);
    }
}
