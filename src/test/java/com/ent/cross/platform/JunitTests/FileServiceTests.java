package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.processors.FilesDto;
import com.ent.cross.platform.service.FilesService;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@Log
public class FileServiceTests {

    @Autowired
    private FilesService filesService;
    public static final String path;
    private static List<FilesDto> filesDtoList = new ArrayList<>();

    static {
        path = "/Users/lodwinmoloto/Workplace";
    }

    @Before
    public void loadAllDirectoryFiles() throws IOException {
        filesDtoList = filesService.readFileContents(path);
    }

    @Test
    public void validateFilesList(){
        log.info("Hello Mahlatse");
        Assert.assertNotNull(filesService.getFiles());
        Assert.assertTrue(filesService.getFiles().size() > 0);
    }

    @Test
    public void validateProperties(){
        Assert.assertNotNull(filesDtoList.get(0).getPath());
        Assert.assertFalse(filesDtoList.get(0).isHidden());
        Assert.assertNotNull(filesDtoList.get(0).getName());
        Assert.assertNotNull(filesDtoList.get(0).getLastModified());
        Assert.assertTrue(filesDtoList.get(0).isReadable());
        log.info("\n File Path : \t" + filesDtoList.get(0).getPath() );
        log.info("\n File Name : \t" + filesDtoList.get(0).getName());
    }
}
