package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private StorageService storageService;
    private NoteService noteService;
    private UserService userService;

    private List<File> fileList;
    private List<Note> noteList;

    public HomeController(UserService userService, StorageService storageService, NoteService noteService){
        this.userService = userService;
        this.storageService = storageService;
        this.noteService = noteService;
    }

    @PostConstruct
    public void postConstruct(){
        fileList = new ArrayList<>();
        noteList = new ArrayList<>();
    }

    @GetMapping
    public String getHome(Model model, Authentication authentication){
        int userId = userService.getUserId(authentication.getName());

        fileList = storageService.getFilesForUser(userId);
        noteList = noteService.getNotes(userId);

        model.addAttribute("fileList", fileList);
        model.addAttribute("noteList", noteList);

        return "home";
    }
}
