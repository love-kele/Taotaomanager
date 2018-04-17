package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGES_BASE_PATH}")
    private String IMAGES_BASE_PATH;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultmap = new HashMap();
        try {
            //获取上传的文件名
            String filename = uploadFile.getOriginalFilename();
            //生成新的文件名
            String newfilename = IDUtils.genImageName() + filename.substring(filename.lastIndexOf('.'));
            //生成图片路径
            String imagepath = new DateTime().toString("/yyyy/MM/dd");
            //图片上传
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    imagepath, newfilename, uploadFile.getInputStream());
            if (result == true) {
                resultmap.put("error", 0);
                resultmap.put("url", IMAGES_BASE_PATH + imagepath + "/" + newfilename);
                return resultmap;
            } else {
                resultmap.put("error", 1);
                resultmap.put("message", "图片上传出错");
                return resultmap;
            }
        } catch (IOException e) {
            resultmap.put("error", 1);
            resultmap.put("message", "图片上传出错");
            return resultmap;
        }


    }
}
