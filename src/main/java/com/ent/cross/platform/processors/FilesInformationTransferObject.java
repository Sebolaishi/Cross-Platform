package com.ent.cross.platform.processors;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Class responsible data transfer to clients
 */
@Data
public class FilesInformationTransferObject {

    private String name;
    private long fileSize;
    private LocalDateTime lastModified;
    private boolean readable;
    private boolean isHidden;
    private String path;

}
