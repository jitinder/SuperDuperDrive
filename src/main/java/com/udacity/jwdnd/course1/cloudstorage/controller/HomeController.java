package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private StorageService storageService;
    private UserService userService;

    public List<File> fileList;

    public HomeController(UserService userService, StorageService storageService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @PostConstruct
    public void postConstruct(){
        fileList = new ArrayList<>();
    }

    @GetMapping
    public String getHome(Model model, Authentication authentication){
        int userId = userService.getUserId(authentication.getName());
        fileList = storageService.getFilesForUser(userId);
        model.addAttribute("fileList", fileList);
        return "home";
    }
}
