package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.processors.FilesInformationTransferObject;
import com.ent.cross.platform.services.FileService;
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
    private FileService fileService;
    public static final String path;
    private static List<FilesInformationTransferObject> filesInformationTransferObjectList = new ArrayList<>();

    static {
        path = "/Users/lodwinmoloto/Workplace";
    }

    @Before
    public void loadDirectoryFiles() throws IOException {
        filesInformationTransferObjectList = fileService.processFileAttributeInformation(path);
    }

    @Test
    public void validateFilesList(){
        Assert.assertNotNull(fileService.getFiles());
        Assert.assertTrue(fileService.getFiles().size() > 0);
    }

    @Test
    public void validateProperties(){
        Assert.assertNotNull(filesInformationTransferObjectList.get(0).getPath());
        Assert.assertFalse(filesInformationTransferObjectList.get(0).isHidden());
        Assert.assertNotNull(filesInformationTransferObjectList.get(0).getName());
        Assert.assertNotNull(filesInformationTransferObjectList.get(0).getLastModified());
        Assert.assertTrue(filesInformationTransferObjectList.get(0).isReadable());
    }
}
