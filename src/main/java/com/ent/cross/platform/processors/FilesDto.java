package com.ent.cross.platform.processors;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component @Data
public class FilesDto {

    private String path;
    private String name;
    private long fileSize;
    private long lastModified;
    private boolean canRead;
    private boolean isHidden;

}
