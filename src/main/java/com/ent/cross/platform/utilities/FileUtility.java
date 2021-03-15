package com.ent.cross.platform.utilities;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class FileUtility {

    /**
     * converts EpochMilliseconds to LocalDateTime
     * @param epoch
     * @return
     */
    public LocalDateTime convertToLocalDateTime(long epoch){
        return Instant.ofEpochMilli(epoch)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
