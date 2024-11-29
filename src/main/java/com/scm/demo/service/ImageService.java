package com.scm.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadFile(MultipartFile file, String fileName);

    String getUrlFromPublicId(String publicId);

}
