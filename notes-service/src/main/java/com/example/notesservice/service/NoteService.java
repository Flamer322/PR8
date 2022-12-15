package com.example.notesservice.service;

import com.example.notesservice.dto.NoteDTO;
import com.example.notesservice.model.Note;
import com.example.notesservice.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;

    public List<Note> getUserNotes(int userId){
        return noteRepository.findByUserId(userId);
    }

    public void addUserNote(NoteDTO noteDTO){
        Note note = new Note();

        note.setUserId(noteDTO.getUserId());
        note.setText(noteDTO.getText());
        note.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));

        noteRepository.save(note);
    }

    public void deleteUserNote(int noteId){
        noteRepository.deleteById(noteId);
    }
}
