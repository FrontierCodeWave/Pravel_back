package com.tour.prevel.file.service.impl;

import com.tour.prevel.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final String PROFILE_PATH = "profile/";

    @Override
    public String uploadProfile(MultipartFile mfile) {
        try {
            File file = getUniqueFile(PROFILE_PATH, mfile.getOriginalFilename());
            mfile.transferTo(file);
            return file.getName();
        } catch (IOException e) {
            log.error("File not saved", e);
            return null;
        }
    }

    public static String generateUniqueFileName(String originalFileName) {
        String baseName = getBaseName(originalFileName);
        String extension = getExtension(originalFileName);
        String uniqueName = baseName + "_" + getTimestamp() + "_" + getUUID() + extension;
        return uniqueName;
    }

    private static String getBaseName(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex > 0) ? fileName.substring(0, lastDotIndex) : fileName;
    }

    private static String getExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex > 0) ? fileName.substring(lastDotIndex) : "";
    }

    private static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static File getUniqueFile(String directory, String fileName) throws IOException {
        File uploadedDir = new ClassPathResource("static/").getFile().getAbsoluteFile();
        Path newDirectory = new File(uploadedDir, directory).toPath();

        if (!Files.exists(newDirectory)) {
            Files.createDirectories(newDirectory);
        }

        return newDirectory.resolve(generateUniqueFileName(fileName)).toFile();
    }
}
