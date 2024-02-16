import com.example.demo2.Demo2Application;
import com.example.demo2.controller.batch.DemoBatchConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shoulder.autoconfigure.batch.ShoulderBatchAutoConfiguration;
import org.shoulder.batch.config.DefaultExportConfigManager;
import org.shoulder.batch.config.ExportConfigManager;
import org.shoulder.batch.config.model.ExportColumnConfig;
import org.shoulder.batch.config.model.ExportFileConfig;
import org.shoulder.batch.constant.BatchConstants;
import org.shoulder.batch.service.ExportService;
import org.shoulder.core.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = Demo2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({ ShoulderBatchAutoConfiguration.class, ShoulderBatchAutoConfiguration.DefaultMemoryBatchRecordAutoConfiguration.class })
@EnableCaching
public class ExportConfigManagerTest {

    private static final String templateId = DemoBatchConstants.DATA_TYPE_TEST;

    static ExportConfigManager exportConfigManager = new DefaultExportConfigManager();

    static {
        ExportFileConfig exportFileConfig = new ExportFileConfig();
        exportFileConfig.setId(templateId);
        exportFileConfig.setHeaders(List.of("First", "Second"));
        exportFileConfig.setColumns(
                Stream.of("First", "Second")
                        .map(s -> new ExportColumnConfig(s, s))
                        .collect(Collectors.toList())
        );
        exportConfigManager.addFileConfig(exportFileConfig);
    }

    @Test
    public void templateTest() {
        ExportFileConfig exportFileConfig = exportConfigManager.getFileConfigWithLocale(templateId, AppContext.getLocaleOrDefault());
        Assertions.assertNotNull(exportFileConfig);
    }

    /**
     * 导出
     */
    @Autowired
    private ExportService exportService;

    @Test
    public void templateOutputTest() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048000);
        exportService.export(out, BatchConstants.CSV, Collections.emptyList(), templateId);
        String s = out.toString();
        System.out.println(s);
    }

}
