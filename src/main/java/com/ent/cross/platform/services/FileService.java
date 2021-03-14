package com.ent.cross.platform.services;

import com.ent.cross.platform.contants.ExceptionMessages;
import com.ent.cross.platform.processors.FilesInformationTransferObject;
import com.ent.cross.platform.utilities.FileUtility;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @Getter
@Setter @Log
public class FileService implements ExceptionMessages {

    @Autowired
    private FilesInformationTransferObject filesInformationTransferObject;
    @Autowired
    private FileUtility fileUtility;
    private List<FilesInformationTransferObject> filesInformationTransferObjectList = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    /**
     * load files from directory for processing
     * @param path received file path
     * @return files
     */
    public List<File> loadDirectoryFiles(String path) throws NotDirectoryException {
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
            throw new NotFoundException(HttpStatus.NO_CONTENT.name());
        }

        return files;
    }

    /**
     * process each file and map file attributes to data transfer object
     * @param path client request directory path
     * @return filesDtoList
     * @throws IOException
     */
    public List<FilesInformationTransferObject> processFileAttributeInformation(String path) throws IOException {
        List<File> files = loadDirectoryFiles(path);
        files.forEach(file -> {
            FilesInformationTransferObject filesInformationTransferObject = new FilesInformationTransferObject();
            if (file.exists()){
                filesInformationTransferObject.setName(file.getName());
                filesInformationTransferObject.setFileSize(file.length());
                filesInformationTransferObject.setLastModified(fileUtility
                        .convertToLocalDateTime(file.lastModified()));
                filesInformationTransferObject.setReadable(file.canRead());
                filesInformationTransferObject.setHidden(file.isHidden());
                filesInformationTransferObject.setPath(file.getPath());
            }

            filesInformationTransferObjectList.add(filesInformationTransferObject);
        });

        return filesInformationTransferObjectList;
    }

}
