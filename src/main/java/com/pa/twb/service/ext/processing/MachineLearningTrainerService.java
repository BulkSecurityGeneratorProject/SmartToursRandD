package com.pa.twb.service.ext.processing;

import com.pa.twb.domain.AttractionPurchase;
import com.pa.twb.repository.TrainingLockRepository;
import com.pa.twb.repository.ext.ExtAttractionPurchaseRepository;
import com.pa.twb.service.ext.processing.dto.csv.CsvDataDTO;
import com.pa.twb.service.mapper.ext.ExtAttractionPurchaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        try {
            csvService.write(csvList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
