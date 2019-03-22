package com.taotao.fdfs;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.UUID;

public class TestFastDfs {

    @Test
    public void testUpload() throws Exception {
        // 1.先创建一个配置文件——fast_dfs.conf，配置文件的内容就是指定TrackerServer的地址

        // 2.使用全局方法加载配置文件
        ClientGlobal.init("D:/workspace/ceshi/src/main/resources/fast_dfs.conf");
        // 3.创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        // 4.通过TrackerClient对象获得TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5.创建StorageServer的引用，null就可以了
        StorageServer storageServer = null;
        // 6.创建一个StorageClient对象，其需要两个参数，一个是TrackerServer，一个是StorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7.使用StorageClient对象上传文件(图片)
        // 参数1：文件名，参数名：扩展名，不能包含"."，参数3：文件的元数据，保存文件的原始名、大小、尺寸等，如果没有可为null
        String[] strings = storageClient.upload_file("D:/iamges/2.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    @Test
    public void testDownload() {
        try {
            ClientGlobal.init("D:/workspace/ceshi/src/main/resources/fast_dfs.conf");

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            byte[] b = storageClient.download_file("group1",
                    "M00/00/00/rBLYElyUOE-AVO6eAACPZ3RurUY375.jpg");
            System.out.println("byte's length:"+b.length);
            IOUtils.write(b, new FileOutputStream("D:/"
                    + UUID.randomUUID().toString() + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}