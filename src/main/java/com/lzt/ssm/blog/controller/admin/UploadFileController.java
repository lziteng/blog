package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

/**
 * @author lzt
 * RestController注解:
 * 1、自动添加@Controller和@ResponseBody
 * 2、不再需要将@ResponseBody添加到所有请求映射方法中。@ResponseBody注解默认处于活动状态
 * @date 2020/1/9 9:57
 */
@Slf4j
@RestController
@RequestMapping("/admin/upload")
public class UploadFileController {

    /**
     * 文件保存目录，电脑硬盘中的路径
     *
     */
    /*public final String rootPath =
            "D:" + File.separator + "myFile" + File.separator + "project" + File.separator + "blog" + File.separator +
                    "uploads";*/
    public final String ROOTPATH =
            "E:" + File.separator + "project" + File.separator + "blog" + File.separator + "uploads";

    public final String ALLOWSUFFIX = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file) {

        //1、文件后缀过滤
        String fileAllName = file.getOriginalFilename();
        String fileName = fileAllName.substring(0, fileAllName.indexOf("."));
        String suffix = fileAllName.substring(fileAllName.lastIndexOf("."));

        if (ALLOWSUFFIX.indexOf(suffix) == -1) {
            return new JsonResult().fail("文件格式错误，请重新上传!");
        }

        //2、为便于维护，使用年月来创建文件目录
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

        //目标文件
        File descFile = new File(ROOTPATH + File.separator + dateDirs + File.separator + fileAllName);
        int i = 1;

        //检查是否重名
        String newFileName = fileAllName;
        while (descFile.exists()) {
            newFileName = fileName + i + suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + newFileName);
            i++;
        }

        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            descFile.getParentFile().mkdirs();
        }

        //3、存储文件
        try {
            //将内存中的数据写入到磁盘
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件上传失败，cause:{}", e);
        }

        //完整的文件url
        String fileUrl = File.separator + "uploads" + File.separator + dateDirs + File.separator + newFileName;

        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(fileAllName);
        uploadFileVO.setSrc(fileUrl);
        return new JsonResult().ok(uploadFileVO);
    }
}
