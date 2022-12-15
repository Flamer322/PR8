package com.example.usersservice.client;

import com.example.usersservice.dto.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "notes-service")
public interface NoteClient {
    @GetMapping("/{userId}")
    List<NoteDTO> getUserNotes(@PathVariable("userId") int userId);

    @PostMapping("/")
    ResponseEntity<Object> addUserNote(@RequestBody NoteDTO noteDTO);

    @DeleteMapping("/{noteId}")
    ResponseEntity<Object> deleteUserNote(@PathVariable("noteId") int noteId);
}
