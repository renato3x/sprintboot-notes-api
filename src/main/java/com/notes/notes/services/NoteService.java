package com.notes.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notes.notes.models.Note;
import com.notes.notes.repositories.NoteRepository;

@Service
public class NoteService {
  @Autowired
  private NoteRepository repository;

  public List<Note> getAll() {
    return repository.findAll();
  }

  public Note getById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public Note create(Note note) {
    return repository.save(note);
  }

  public Note update(Long id, Note data) {
    Note note = repository.findById(id).orElse(null);

    if (note != null) {
      note.setTitle(data.getTitle());
      note.setContent(data.getContent());
      return repository.save(note);
    }

    return null;
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}