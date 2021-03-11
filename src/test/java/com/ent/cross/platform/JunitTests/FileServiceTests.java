package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.processors.FilesDto;
import com.ent.cross.platform.service.FilesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FileServiceTests {

    private static Logger logger = LoggerFactory.getLogger(FileServiceTests.class);

    @Autowired
    private FilesService filesService;
    public static final String path;

    static {
        path = "{\"path\":\"/Users/lodwinmoloto/Workplace/MockFiles\"}";
    }

    @Test
    public void loadAllDirectoryFiles() throws IOException {

        List<FilesDto> filesDtoList = filesService.readFileContents(path);
        Assert.assertNotNull(filesService.getFiles());
        Assert.assertTrue(filesService.getFiles().size() > 0);

        Assert.assertNotNull(filesDtoList.get(0).getPath());
        Assert.assertNotNull(filesDtoList.get(0).getFileSize());
        Assert.assertNotNull(filesDtoList.get(0).getInformation());
        logger.info(filesDtoList.get(0).getPath());
        logger.info(filesDtoList.get(0).getName());
        logger.info(filesDtoList.get(0).getInformation());
    }
}
