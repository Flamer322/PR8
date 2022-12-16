package com.example.usersservice.controller;

import com.example.usersservice.service.UserService;
import com.example.usersservice.dto.EmailDTO;
import com.example.usersservice.dto.NoteDTO;
import com.example.usersservice.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String index(OAuth2AuthenticationToken token, Model model) {
        String userName = token.getName();

        model.addAttribute("userName", userName);

        userService.saveUser(userName);

        int userId = userService.getUserId(userName);

        String userEmail = userService.getUserEmail(userId);

        model.addAttribute("userEmail", userEmail);

        List<NoteDTO> notes = userService.getUserNotes(userId);
        model.addAttribute("notes", notes);

        List<NotificationDTO> notifications = userService.getUserNotifications(userId);
        model.addAttribute("notifications", notifications);

        NoteDTO newNoteDTO = new NoteDTO();
        model.addAttribute("newNote", newNoteDTO);

        NotificationDTO newNotificationDTO = new NotificationDTO();
        model.addAttribute("newNotification", newNotificationDTO);

        EmailDTO newEmailDTO = new EmailDTO();
        model.addAttribute("newEmail", newEmailDTO);

        return "index";
    }

    @PostMapping("/notes")
    public String addUserNote(OAuth2AuthenticationToken token, @ModelAttribute NoteDTO newNoteDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        newNoteDTO.setUserId(userId);

        userService.addUserNote(newNoteDTO);

        return "redirect:/";
    }

    @GetMapping("/notes/{noteId}/delete")
    public String deleteUserNote(@PathVariable("noteId") int noteId) {
        userService.deleteUserNote(noteId);

        return "redirect:/";
    }

    @PostMapping("/notifications")
    public String addUserNotification(OAuth2AuthenticationToken token, @ModelAttribute NotificationDTO newNotificationDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        newNotificationDTO.setUserId(userId);

        userService.addUserNotification(newNotificationDTO);

        return "redirect:/";
    }

    @PostMapping("/email")
    public String setUserEmail(OAuth2AuthenticationToken token, @ModelAttribute EmailDTO emailDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        emailDTO.setUserId(userId);

        userService.setUserEmail(emailDTO);

        return "redirect:/";
    }
}
