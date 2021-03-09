package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.service.FilesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FileServiceTests {

    @Autowired
    private FilesService filesService;

    @Test
    public void loadAllDirectoryFiles(){
        List<File> files = filesService.fetchAllDirectoryFiles("/Users/lodwinmoloto/Workplace/MockFiles");
        Assert.assertNotNull(files);
    }
}
