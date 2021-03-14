package com.ent.cross.platform.JunitTests;

import com.ent.cross.platform.utilities.FileUtility;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Log
public class FileUtilityTests {

    @Autowired
    private FileUtility fileUtility;
    private static final long Epoch_Milliseconds = 233333333999L;

    @Test
    public void epochToLocalTimeConverter(){
        LocalDateTime localTime = fileUtility.convertToLocalDateTime(Epoch_Milliseconds);
        log.info(localTime.toString());
        Assert.assertNotNull(localTime);
    }
}
