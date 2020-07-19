package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @GetMapping("/delete-credential/{credId:.+}")
    public String deleteCredential(@PathVariable int credId, Model model){
        String errorMessage = null;

        int deletedRows = credentialService.deleteCredential(credId);

        if(deletedRows < 1){
            errorMessage = "Error! Can't delete the credential";
        }

        if(errorMessage == null){
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", errorMessage);
        }

        return "result";
    }

    @PostMapping("/add-credential")
    public String addCredential(@ModelAttribute("new-credential") Credential credential, Model model, Authentication authentication){
        String errorMessage = null;
        boolean toUpdate = false;
        int currentUserId = userService.getUserId(authentication.getName());

        if(credential.getCredentialId() != null){
            // Update Credential
            credential.setUserId(currentUserId);
            credential.setKey("passwordgenerkey");
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
            int credsUpdated = credentialService.updateCredential(credential);
            if(credsUpdated < 1){
                errorMessage = "There was an error updating your credential.";
            }
            toUpdate = true;
        }

        if(!toUpdate) {
            credential.setUserId(currentUserId);
            credential.setKey("passwordgenerkey");
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
            int credRows = credentialService.addCredential(credential);

            if (credRows < 1) {
                errorMessage = "There was an error uploading your Credentials";
            }
        }

        if(errorMessage == null){
            if(toUpdate){
                model.addAttribute("updateSuccess", true);
            } else {
                model.addAttribute("uploadSuccess", true);
            }
        } else {
            model.addAttribute("uploadError", errorMessage);
        }

        return "result";
    }
}
