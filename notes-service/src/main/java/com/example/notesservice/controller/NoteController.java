package com.example.notesservice.controller;

import com.example.notesservice.service.NoteService;
import com.example.notesservice.dto.NoteDTO;
import com.example.notesservice.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class NoteController {
    private NoteService noteService;

    @GetMapping("/{userId}")
    public List<Note> getUserNotes(@PathVariable("userId") int userId) {
        return noteService.getUserNotes(userId);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUserNote(@RequestBody NoteDTO noteDTO) {
        noteService.addUserNote(noteDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Object> deleteUserNote(@PathVariable("noteId") int noteId) {
        noteService.deleteUserNote(noteId);

        return ResponseEntity.noContent().build();
    }
}
