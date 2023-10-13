package com.klein.novel.service;

import com.klein.novel.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    @Override
    public byte[] getImageBytes(String imageName) {
        byte[] imgBytes = imageRepository.findByName(imageName).getPicByte();
        return imgBytes;
    }
}
