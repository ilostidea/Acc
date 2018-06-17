package com.bit.acc.controller;

import com.bit.common.model.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/file")
public class FileUploadRestController {

    private final String IMG = File.separator + "upload" + File.separator + "img";
    private final String OTHER = File.separator + "upload" + File.separator + "other";

    @RequestMapping(value = "/uploadImg", consumes = "multipart/form-data", method = RequestMethod.POST)
    public Response picture(@RequestParam("fileUpload") CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if ( !file.isEmpty() ) {
            Map originalNameAndSavedUrl = saveFile(IMG, file, request, response);
            return new Response().success( originalNameAndSavedUrl );
        }
        return new Response().failure("请选择上传的文件！");
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public Response upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Map> savedPath = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext() );
        //判断request是否有文件需要上传
        if( multipartResolver.isMultipart( request ) ){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> filenames = multiRequest.getFileNames();
            while( filenames.hasNext() ){
                MultipartFile file = multiRequest.getFile( filenames.next() );
                if( file != null ) {
                    String fileName = file.getOriginalFilename();
                    //如果名称不为""，说明该文件存在，否则说明文件不存在。
                    if( fileName.trim().length() != 0 ) {
                        Map originalNameAndSavedUrl = saveFile(OTHER, file, request, response);
                        savedPath.add( originalNameAndSavedUrl );
                    }
                }
            }
            return new Response().success(savedPath);
        }
        return new Response().failure("请选择上传的文件！");
    }

    private Map saveFile( String rootDir, MultipartFile file, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        //重命名上传后的文件
        String path = rootDir + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date() ) + File.separator;
        path = request.getServletContext().getRealPath("/") + path;
        File dir = new File( path );
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String originalName = file.getOriginalFilename();
        String savedName = String.valueOf(System.currentTimeMillis());
        path += File.separator + savedName;
        int dot = originalName.lastIndexOf('.');
        if ( (dot > -1) && (dot < (originalName.length() - 1)) ) {//获取文件扩展名
            path += "." + originalName.substring(dot + 1);
        }
        File localFile = new File(path);
        file.transferTo(localFile);
        Map originalNameAndSavedUrl = new HashMap<String, String>();
        originalNameAndSavedUrl.put("originalName", originalName);
        originalNameAndSavedUrl.put("savedUrl", path);
        return originalNameAndSavedUrl;
    }

}