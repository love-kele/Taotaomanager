package com.taotao.controller;

import com.taotao.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureContrller {

    @Autowired
    private PictureServiceImpl pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map pictureUpload(MultipartFile multipartFile) {

        Map result = pictureService.uploadPicture(multipartFile);

        return result;
    }


    public void setPictureService(PictureServiceImpl pictureService) {
        this.pictureService = pictureService;
    }
}
