package com.aaa.service;

import com.aaa.properties.FtpProperties;
import com.aaa.utils.FtpUtils;
import com.aaa.utils.FileNameUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.staticproperties.TimeFormatProperties.DATE_FORMAT;

/**
 * @Author: Joy
 * @Date: 2020/7/10 15:51
 * @Description:
 */
@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;

    /**
     * @param [multipartFile]
     * @return java.lang.Boolean
     * @author Joy
     * @description: 文件上传
     * @Date 2020/7/10
     **/
    public Boolean upload(MultipartFile file) {
        String oldFileName = file.getOriginalFilename();
        String newFileName = FileNameUtils.getFileName();
        //获取后缀
        newFileName += oldFileName.substring(oldFileName.indexOf(POINT));
        String filePath = DateUtil.formatDate(new Date(), DATE_FORMAT);
        try {
            return FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
