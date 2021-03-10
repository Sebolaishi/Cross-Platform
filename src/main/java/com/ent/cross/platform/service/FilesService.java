package com.ent.cross.platform.service;

import com.ent.cross.platform.processors.FilesDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service @Getter @Setter
public class FilesService {

    private List<File> files = new ArrayList<>();

    /**
     *
     * @param path
     * @return
     */
    public List<File> fetchAllDirectoryFiles(String path){

        try {
            files = Files.list(Paths.get(path))
                    .map(Path::toFile)
                    .filter(file -> !file.isHidden())
                    .collect(Collectors.toList());
            files.forEach(System.out::println);
        } catch (IOException e) {
            //TODO Error while reading the directory
        }

        return files;
    }

    /**
     *
     * @param files
     * @return
     */
    public List<FilesDto> readFileContents(List<File> files){

        return null;
    }
}
