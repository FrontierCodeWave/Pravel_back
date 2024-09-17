package com.tour.prevel.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadProfile(MultipartFile file);
}
