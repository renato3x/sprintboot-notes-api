package com.notes.notes.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.notes.models.Note;
import com.notes.notes.services.NoteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/notes")
public class NoteController {
  @Autowired
  private NoteService service;

  @GetMapping
  public List<Note> index() {
    return service.getAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Note note = service.getById(id);

    if (note == null) {
      Map<String, Object> response = new HashMap<>();
      response.put("message", "Note not found");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    return ResponseEntity.ok(note);
  }

  @PostMapping
  public ResponseEntity<Note> create(@RequestBody Note data) {
    Note note = service.create(data);
    return new ResponseEntity<>(note, HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Note data) {
    Note note = service.update(id, data);

    if (note == null) {
      Map<String, Object> response = new HashMap<>();
      response.put("message", "Note no found");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    return ResponseEntity.ok(note);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
