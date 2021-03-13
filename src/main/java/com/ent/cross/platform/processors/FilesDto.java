package com.ent.cross.platform.processors;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component @Data @Log
public class FilesDto {

    private String name;
    private long fileSize;
    private String lastModified;
    private boolean readable;
    private boolean isHidden;
    private String path;

}
