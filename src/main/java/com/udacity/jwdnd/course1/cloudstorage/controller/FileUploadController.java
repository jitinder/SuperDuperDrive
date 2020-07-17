package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file-upload")
public class FileUploadController {

    @PostMapping()
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file, Model model) throws IOException {
        InputStream fis = file.getInputStream();
        String filename=file.getOriginalFilename();
        byte[] fileData = fis.get
    }

}
