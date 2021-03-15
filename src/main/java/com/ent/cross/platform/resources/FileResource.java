package com.ent.cross.platform.resources;

import com.ent.cross.platform.contants.ExceptionMessages;
import com.ent.cross.platform.exceptions.BaseExceptionsResponse;
import com.ent.cross.platform.exceptions.NoContentExceptionHandler;
import com.ent.cross.platform.processors.FileRequestBody;
import com.ent.cross.platform.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * File requests endpoint, does all service requests and responses - to and from clients
 */
@RestController
@RequestMapping("/cross-app")
public class FileResource implements ExceptionMessages {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> fetchDirectoryInfo(@Valid @RequestBody FileRequestBody path) throws IOException {
        try {
            fileService.processFileAttributeInformation(path.getPath());
        }catch (NoContentExceptionHandler exception){
            BaseExceptionsResponse error = BaseExceptionsResponse.of(HttpStatus.NO_CONTENT.name(), NO_CONTENT);
            return getResponseEntity(error, HttpStatus.NO_CONTENT);
        }
        return getResponseEntity(fileService.getFilesInformationTransferObjectList(), HttpStatus.OK);
    }

    private ResponseEntity<Object> getResponseEntity(Object object, HttpStatus httpStatus) {
        return new ResponseEntity<>(object, getHttpHeaders(), httpStatus);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
