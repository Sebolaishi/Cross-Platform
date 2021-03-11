package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.processors.FilesDto;
import com.ent.cross.platform.service.FilesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FileServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceTests.class);
    @Autowired
    private FilesService filesService;
    public static final String path;
    private static List<FilesDto> filesDtoList = new ArrayList<>();

    static {
        path = "{\"path\":\"/Users/lodwinmoloto/Workplace/MockFiles\"}";
    }

    @Before
    public void loadAllDirectoryFiles() throws IOException {
        filesDtoList = filesService.readFileContents(path);
    }

    @Test
    public void validateFilesList(){
        Assert.assertNotNull(filesService.getFiles());
        Assert.assertTrue(filesService.getFiles().size() > 0);
    }

    @Test
    public void validateProperties(){
        Assert.assertNotNull(filesDtoList.get(0).getPath());
        Assert.assertNotNull(filesDtoList.get(0).getFileSize() );
        Assert.assertNotNull(filesDtoList.get(0).getName());
        Assert.assertNotNull(filesDtoList.get(0).isHidden());
        logger.info("\n File Path : \t" + filesDtoList.get(0).getPath() );
        logger.info("\n File Name : \t" + filesDtoList.get(0).getName());
    }
}
