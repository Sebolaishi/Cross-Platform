package com.ent.cross.platform.resources;

import com.ent.cross.platform.processors.FilesDto;
import com.ent.cross.platform.service.FilesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * ReadFile endpoint, does all application requests and responses - to and from clients
 */
@RestController
@RequestMapping("/files")
public class FileResource {

    @Autowired
    private FilesDto filesDto;
    @Autowired
    private FilesService filesService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<FilesDto> savePerson(@Valid @RequestBody String path) throws IOException {
        filesService.readFileContents(path);
        return filesService.getFilesDtoList();
    }

}
