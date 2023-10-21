package com.itperson.spzx.manager.controller;

import com.itperson.spzx.manager.service.FileUploadService;
import com.itperson.spzx.model.vo.common.Result;
import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        String fileUrl = fileUploadService.fileUpload(file);
        return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    }

}
