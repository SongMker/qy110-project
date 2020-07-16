package com.aaa.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Joy
 * @Date: 2020/7/10 15:36
 * @Description:
 */
public class FtpUtils {
    private FtpUtils() {
    }

    public static Boolean upload(String host, Integer port, String username, String password, String basePath, String filePath
            , String fileName, InputStream inputStream) {
        //创建临时路径
        String tempPath = "";
        //连接
        FTPClient ftpClient = new FTPClient();
        try {
            int replyCode;
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            //连接成功返回230，失败返回503
            replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                //连接失败
                ftpClient.disconnect();
                return false;
            }
            //检测上传目录是否存在
            if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {

                //文件夹不存在 开始创建
                String[] dirs = filePath.split("/");
                tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || StringUtils.isEmpty(dir)) {
                        //没有数据
                        continue;
                    }
                    //拼接临时路径
                    tempPath += "/" + dir;
                    //再次判断
                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
                        if (!ftpClient.makeDirectory(tempPath)) {
                            return false;
                        } else {
                            //判断创建出来的目录确实存在
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }

            //上传 把文件改成二进制
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpClient.storeFile(fileName, inputStream)) {
                //上传失败
                return false;
            }
            inputStream.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                //说明还在连接中
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
