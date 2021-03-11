package com.ent.cross.platform.service;

import com.ent.cross.platform.processors.FilesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
@Service @Getter @Setter
public class FilesService {

    private static Logger logger = LoggerFactory.getLogger(FilesService.class);

    @Autowired
    private FilesDto filesDto;

    private List<FilesDto> filesDtoList = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    /**
     *
     * @param path received file path
     * @return files
     */
    public List<File> fetchAllDirectoryFiles(String path){
        try {
            files = Files.list(Paths.get(path))
                    .map(Path::toFile)
                    .filter(file -> !file.isDirectory())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Supplied path is a directory and not a file path");
        }

        return files;
    }


    public List<FilesDto> readFileContents(String path) throws IOException {
        String filePath = convertJson(path).getPath();
        List<File> files = fetchAllDirectoryFiles(filePath);

        files.forEach(file -> {
            FilesDto filesDto = new FilesDto();
            if (file.exists()){
                filesDto.setPath(file.getPath());
                filesDto.setFileSize(file.length());
                filesDto.setName(file.getName());
                filesDto.setLastModified(Instant.ofEpochMilli(file.lastModified())
                        .atZone(ZoneId.systemDefault()).toLocalDateTime());
                filesDto.setCanRead(file.canRead());
                filesDto.setHidden(file.isHidden());
            }else {
                logger.info("File is invalid");
            }

            filesDtoList.add(filesDto);
        });

        return filesDtoList;
    }

    /**
     * Convert json string received from client or endpoint to a java bean.
     * @param message
     * @return
     * @throws JsonProcessingException
     */
    public FilesDto convertJson(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message, FilesDto.class);
    }
}
