package com.mtf.admin.common.dto;

import com.mtf.admin.entity.Bulletin;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class BulletinDTO implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String desc;
    private String image;

    private MultipartFile file;

    public Bulletin getBulletin() {
        Bulletin b = new Bulletin();
        b.setId(id);
        b.setTitle(title);
        b.setContent(content);
        b.setDesc(desc);
        b.setImage(image);
        return b;
    }
}
