package com.scm.demo.service.impl;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.demo.Utilities.AppConstants;
import com.scm.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {

        // generate unique name

        // upload file on cloudinary
        try {
            byte[] data = new byte[file.getInputStream().available()];
            file.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", fileName
            ));

            // return the url
            return this.getUrlFromPublicId(fileName);
        } catch (IOException e) {
            // Throw exception
            log.info("Error : "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
            .url()
            .transformation(
                new Transformation<>()
                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                .crop(AppConstants.CONTACT_IMAGE_CROP))
            .generate(publicId);

    }


}
