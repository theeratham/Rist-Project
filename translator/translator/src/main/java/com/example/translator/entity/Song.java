package com.example.translator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToOne
    @JoinColumn(name = "lyrics_id")
    private Lyrics lyrics;
}
