package com.ent.cross.platform.processors;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Class definition of client request body
 */
@Data @Component
public class FileRequestBody {
    private String path;
}
