package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.contants.ExceptionMessages;
import com.ent.cross.platform.exceptions.NoContentExceptionHandler;
import com.ent.cross.platform.processors.FilesInformationTransferObject;
import com.ent.cross.platform.services.FileService;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Log
public class FileServiceTests implements ExceptionMessages {

    @Autowired
    private FileService fileService;
    public static final String path;
    public static final String wrongpath;
    public static final String emptypath;
    private static List<FilesInformationTransferObject> filesInformationTransferObjectList = new ArrayList<>();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    static {
        path = "/Users/lodwinmoloto/Workplace";
        wrongpath = "/Users/lodwinmoloto/Workplacet";
        emptypath = "/Users/lodwinmoloto/Workplace/MockFiles";

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
    public void notFoundException() throws IOException {
        exceptionRule.expect(NotDirectoryException.class);
        exceptionRule.expectMessage(DIRECTORY_NOT_FOUND);
        fileService.processFileAttributeInformation(wrongpath);
    }

    @Test(expected = NoContentExceptionHandler.class)
    public void noContentResponse() throws IOException {
        fileService.processFileAttributeInformation(emptypath);
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
