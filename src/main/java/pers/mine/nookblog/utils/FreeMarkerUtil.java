package pers.mine.nookblog.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * @author yebukong
 * @description FreeMarker工具类, 同时包含部分文件操作
 * @since 2018-11-04 5:35
 */
@Component
@Slf4j
public class FreeMarkerUtil {
    private static Configuration fmCfg;

    @Autowired
    public void setConfiguration(Configuration fmCfg) {
        FreeMarkerUtil.fmCfg = fmCfg;
    }

    public static void processFileWithTemplate(String templateName, String targetFilePath, Map<String, Object> dataModel) {
        Writer writer = null;
        try {
            Template temp = fmCfg.getTemplate(templateName);
            File f = new File(targetFilePath);
            checkAndMkdirs(f.getParent());
            writer = new FileWriter(f);
            temp.process(dataModel, writer);
            log.info(String.format("静态化摸板[ %s ]到路径[ %s ]成功", templateName, targetFilePath));
        } catch (Exception e) {
            log.warn(String.format("静态化摸板[ %s ]到路径[ %s ]发生异常:", templateName, targetFilePath), e);
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (Exception e) {
                log.warn(String.format("静态化摸板[ %s ]到路径[ %s ]发生异常:", templateName, targetFilePath), e);
            }
        }
    }

    public static void checkAndMkdirs(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 复制指定spring资源到指定路径
     *
     * @param resource
     * @param targetPath       目标路径
     * @param relativeRootPath 资源所在相对路径，如果填写了relativeRootPath，会连同相对relativeRootPath路径后的目录一起建立，反之直接在targetPath复制资源
     * @throws IOException
     */
    public static void copyResourceToFile(Resource resource, String targetPath, String relativeRootPath) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            log.warn(String.format("[%s]获取输入流发生异常!", resource.getURL()));
            throw new RuntimeException(String.format("[%s]获取输入流发生异常!", resource.getURL()));
        }
        String filename = resource.getFilename();

        //分析相对目录
        String appenPath = "";
        if (!StringX.isEmpty(relativeRootPath)) {
            String sUrl = resource.getURL().toString();
            if (sUrl.startsWith("jar:file:")) {
                String[] tempArr = sUrl.split("!");
                appenPath = tempArr[tempArr.length - 1];
            } else if (sUrl.startsWith("file:")) {
                appenPath = sUrl.substring("file:".length() - 1);
            }
            int rootPathIndex = appenPath.indexOf(relativeRootPath);
            if (rootPathIndex < 0) {
                throw new RuntimeException("relativeRootPath有误：无法分析相对目录");
            }
            appenPath = appenPath.substring(rootPathIndex + relativeRootPath.length(), appenPath.length() - filename.length());
            if (StringX.isEmpty(appenPath)) {
                appenPath = File.separator;
            }
        }
        String filePath = targetPath + File.separator + appenPath;
        checkAndMkdirs(filePath);
        String fileName = filePath + filename;
        File file = new File(fileName);
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    /**
     * 清空指定目录
     *
     * @param path
     * @return 操作结果
     */
    public static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {//判断是否待删除目录是否存在
            log.warn("The dir[" + path + "] are not exists!");
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            } else {
                if (!temp.delete()) {//直接删除文件
                    log.warn("Failed to delete " + name);
                }
            }
        }
        return true;
    }
}
