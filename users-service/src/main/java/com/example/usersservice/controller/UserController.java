package com.example.usersservice.controller;

import com.example.usersservice.service.UserService;
import com.example.usersservice.dto.EmailDTO;
import com.example.usersservice.dto.NoteDTO;
import com.example.usersservice.dto.NotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String index(Principal token, Model model) {
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
    public String addUserNote(Principal token, @ModelAttribute NoteDTO newNoteDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        newNoteDTO.setUserId(userId);

        userService.addUserNote(newNoteDTO);

        return "redirect:/users";
    }

    @GetMapping("/notes/{noteId}/delete")
    public String deleteUserNote(@PathVariable("noteId") int noteId) {
        userService.deleteUserNote(noteId);

        return "redirect:/users";
    }

    @PostMapping("/notifications")
    public String addUserNotification(Principal token, @ModelAttribute NotificationDTO newNotificationDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        newNotificationDTO.setUserId(userId);

        userService.addUserNotification(newNotificationDTO);

        return "redirect:/users";
    }

    @PostMapping("/email")
    public String setUserEmail(Principal token, @ModelAttribute EmailDTO emailDTO) {
        String userName = token.getName();

        int userId = userService.getUserId(userName);

        emailDTO.setUserId(userId);

        userService.setUserEmail(emailDTO);

        return "redirect:/users";
    }

//    @GetMapping("/users")
//    public String redirectBack() {
//        return "redirect:http://localhost:8080/users";
//    }
}
