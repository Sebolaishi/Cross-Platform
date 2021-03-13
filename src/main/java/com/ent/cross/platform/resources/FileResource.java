package com.ent.cross.platform.resources;

import com.ent.cross.platform.processors.FileRequestBody;
import com.ent.cross.platform.processors.FilesInformationTransferObject;
import com.ent.cross.platform.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * File requests endpoint, does all service requests and responses - to and from clients
 */
@RestController
@RequestMapping("/cross-app")
public class FileResource {

    @Autowired
    private FilesService filesService;

    @PostMapping(value = "/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<FilesInformationTransferObject> savePerson(@Valid @RequestBody FileRequestBody path) throws IOException {
        filesService.processFileAttributeInformation(path.getPath());
        return filesService.getFilesInformationTransferObjectList();
    }

}
