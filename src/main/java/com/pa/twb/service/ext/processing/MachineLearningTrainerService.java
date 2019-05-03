package com.pa.twb.service.ext.processing;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.TrainingLockRepository;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.service.ext.processing.dto.csv.CsvDataDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionPurchaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class MachineLearningTrainerService {
    private final Logger log = LoggerFactory.getLogger(MachineLearningTrainerService.class);

    private final CsvService csvService;

    private final ExtAttractionPurchaseRepository extAttractionPurchaseRepository;

    private final ExtAttractionPurchaseMapper extAttractionPurchaseMapper;

    private final TrainingLockRepository trainingLockRepository;

    public MachineLearningTrainerService(CsvService csvService,
                                         ExtAttractionPurchaseRepository extAttractionPurchaseRepository,
                                         ExtAttractionPurchaseMapper extAttractionPurchaseMapper,
                                         TrainingLockRepository trainingLockRepository) {
        this.csvService = csvService;
        this.extAttractionPurchaseRepository = extAttractionPurchaseRepository;
        this.extAttractionPurchaseMapper = extAttractionPurchaseMapper;
        this.trainingLockRepository = trainingLockRepository;
    }

    public void train() {
        List<AttractionPurchase> result = extAttractionPurchaseRepository.findAll();
        List<CsvDataDTO> csvList = extAttractionPurchaseMapper.entityListToCsv(result);

        CsvDataDTO initialTrueCsv = new CsvDataDTO();
        initialTrueCsv.setUserDistance(0d);
        initialTrueCsv.setActionTaken(true);

        CsvDataDTO initialFalseCsv = new CsvDataDTO();
        initialFalseCsv.setUserDistance(10000d);
        initialFalseCsv.setActionTaken(false);

        csvList.add(initialTrueCsv);
        csvList.add(initialFalseCsv);

        try {
            csvService.write(csvList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://127.0.0.1:8000/logistic-regression/");
        ResponseEntity<Void> responseEntity =
            restTemplate.exchange(uri, HttpMethod.PUT, null, Void.class);

    }
}
