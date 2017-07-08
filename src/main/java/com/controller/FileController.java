package com.controller;

import com.service.inface.FileServiceInface;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/6/20.
 */
@Controller
@RequestMapping("/keda")
public class FileController {
//    @Resource
//    private FileServiceInface inface;
    /**
     * 上传文件（多文件上传把参数转化成数组就可以了）
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload1.do",method = RequestMethod.GET)
    public String login(@RequestParam("file") MultipartFile file){
        //分别获取的是变量名file---文件类型---文件名
        System.out.println(file.getName()+"---"+file.getContentType()+"---"+file.getOriginalFilename());
        try {
            if (!file.isEmpty()){
                //使用StreamsAPI方式拷贝文件
                Streams.copy(file.getInputStream(),new FileOutputStream("E:/temp/"+file.getOriginalFilename()),true);
            }
        } catch (IOException e) {
            System.out.println("文件上传失败");
            e.printStackTrace();
        }
        return "user/login";
    }


    /**
     * 文件上传功能
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload2.do",method= RequestMethod.GET)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException{
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        return "ok!";
    }
    /**
     * 文件下载过程
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/apk1.do", produces = "application/octet-stream;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<byte[]> getdownload() throws IOException {
        System.out.println("请求下载链接");
        //指定文件,必须是绝对路径
        File file = new File("F:/shenzhenDemo/yiluwo/app/奇正2.6.9.apk");
        //下载浏览器响应的那个文件名
        String dfileName = "奇正2.6.9.apk";
        //下面开始设置HttpHeaders,使得浏览器响应下载
        HttpHeaders headers = new HttpHeaders();
        //设置响应方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置响应文件
        headers.setContentDispositionFormData("attachment", dfileName);
        //把文件以二进制形式写回
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/download/apk2.do")
    @ResponseBody
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //模拟文件，myfile.txt为需要下载的文件
        System.out.println("请求下载接口2");
        String fileName = "F:/shenzhenDemo/yiluwo/app/奇正2.6.9.apk";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        //假如以中文名下载的话
        String filename = "奇正2.6.9.apk";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }
}
