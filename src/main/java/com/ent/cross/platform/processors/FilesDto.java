package com.ent.cross.platform.processors;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component @Data
public class FilesDto {

    private static Logger logger = LoggerFactory.getLogger(FilesDto.class);

    private String path;
    private String name;
    private long fileSize;
    private LocalDateTime lastModified;
    private boolean canRead;
    private boolean isHidden;

}
