package com.example.notesservice;

import com.example.notesservice.dto.NoteDTO;
import com.example.notesservice.model.Note;
import com.example.notesservice.repository.NoteRepository;
import com.example.notesservice.service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NotesServiceApplicationTests {
    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    @Test
    void addNote() {
        long count = noteRepository.count();

        NoteDTO newNoteDTO = new NoteDTO();

        newNoteDTO.setText("new note");
        newNoteDTO.setUserId(52);

        noteService.addUserNote(newNoteDTO);

        Assertions.assertEquals(count + 1, noteRepository.count());
    }

    @Test
    void getNote() {
        List<Note> notes = noteService.getUserNotes(52);

        Assertions.assertEquals("Новая заметка", notes.get(0).getText());
    }

    @Test
    void deleteNote() {
        long count = noteRepository.count();

        noteService.deleteUserNote(607);

        Assertions.assertEquals(count - 1, noteRepository.count());
    }

}
