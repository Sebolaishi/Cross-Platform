package com.ent.cross.platform.service;

import com.ent.cross.platform.exceptions.EmptyListException;
import com.ent.cross.platform.contants.ExceptionMessages;
import com.ent.cross.platform.processors.FilesDto;
import com.ent.cross.platform.utilities.FileUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service @Getter
@Setter @Log
public class FilesService implements ExceptionMessages {

    @Autowired
    private FilesDto filesDto;
    @Autowired
    private FileUtility fileUtility;
    private List<FilesDto> filesDtoList = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    /**
     *
     * @param path received file path
     * @return files
     */
    public List<File> fetchAllDirectoryFiles(String path) throws NotDirectoryException {

        try {
            files = Files.list(Paths.get(path))
                    .map(Path::toFile)
                    .filter(file -> !file.isDirectory())
                    .filter(file -> !file.isHidden())
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.severe(DIRECTORY_NOT_FOUND);
            throw new NotDirectoryException(HttpStatus.NOT_FOUND.name());
        }

        if (files.size() == 0){
            log.warning(NO_CONTENT);
            throw new EmptyListException();
        }

        return files;
    }


    public List<FilesDto> readFileContents(String path) throws IOException {
        List<File> files = fetchAllDirectoryFiles(path);
        files.forEach(file -> {
            FilesDto filesDto = new FilesDto();
            if (file.exists()){
                filesDto.setName(file.getName());
                filesDto.setFileSize(file.length());
                filesDto.setLastModified(fileUtility.convertToLocalDateTime(file.lastModified()));
                filesDto.setReadable(file.canRead());
                filesDto.setHidden(file.isHidden());
                filesDto.setPath(file.getPath());

            }else {
                log.info("File is invalid");
            }

            filesDtoList.add(filesDto);
        });

        return filesDtoList;
    }

}
