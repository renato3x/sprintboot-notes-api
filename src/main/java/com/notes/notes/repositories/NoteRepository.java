package com.notes.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  
}
