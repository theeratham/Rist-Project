package com.example.translator.repository;

import com.example.translator.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    boolean existsByName(String name);
}
