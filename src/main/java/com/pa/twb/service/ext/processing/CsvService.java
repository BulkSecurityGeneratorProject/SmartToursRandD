package com.pa.twb.service.ext.processing;

import com.pa.twb.service.ext.processing.dto.csv.CsvDataDTO;
import org.h2.store.fs.FileUtils;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class CsvService {
    private static final String FOLDER_NAME = "/Users/thomasbigger/Desktop/projects/backend/smart-tours/src/main/python/app/data";
    private static final String FILE_NAME = "trx_data.csv";

    public void write(List<CsvDataDTO> list) throws Exception {
        FileUtils.createDirectories(FOLDER_NAME);

        String filePath = FOLDER_NAME + File.separator + FILE_NAME;
        FileUtils.createFile(filePath);

        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter(filePath),
                CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[]{"userDistance", "actionTaken"};
            final CellProcessor[] processors = new CellProcessor[]{
                new NotNull(), new NotNull(),
            };
            // write the header
            beanWriter.writeHeader(header);

            // write the beans
            for (final CsvDataDTO csvDataDTO : list) {
                beanWriter.write(csvDataDTO, header, processors);
            }

        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }
}
