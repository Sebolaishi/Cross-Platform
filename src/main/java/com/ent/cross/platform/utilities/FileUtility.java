package com.ent.cross.platform.utilities;

import com.ent.cross.platform.processors.FilesInformationTransferObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class FileUtility {

    public String convertToLocalDateTime(long epoch){
        return Instant.ofEpochMilli(epoch)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().toString();
    }

    public FilesInformationTransferObject convertJson(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message, FilesInformationTransferObject.class);
    }
}
