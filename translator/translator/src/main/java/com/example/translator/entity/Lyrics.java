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
public class Lyrics {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] file;

    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song;
}
