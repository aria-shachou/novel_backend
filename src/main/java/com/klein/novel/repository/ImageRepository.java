package com.klein.novel.repository;

import com.klein.novel.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel,Long> {
    ImageModel findByName(String name);
}
