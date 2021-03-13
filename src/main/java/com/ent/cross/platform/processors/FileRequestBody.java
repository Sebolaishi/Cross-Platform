package com.ent.cross.platform.processors;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data @Component
public class FileRequestBody {
    private String path;
}
