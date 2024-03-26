import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { AlbumRequest } from 'src/app/component/request/album-request';
import { ArtistRequest } from 'src/app/component/request/artist-request';
import { SongRequest } from 'src/app/component/request/song-request';
import { AlbumService } from 'src/app/service/album/album.service';
import { ArtistService } from 'src/app/service/artist/artist.service';
import { LyricsService } from 'src/app/service/lyrics/lyrics.service';
import { SongService } from 'src/app/service/song/song.service';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css'],
})
export class SongComponent implements OnInit {
  @ViewChild('SongModal', { static: true }) songModal!: ModalDirective;
  @ViewChild('ArtistModal', { static: true }) artistModal!: ModalDirective;
  @ViewChild('AlbumModal', { static: true }) albumModal!: ModalDirective;
  @ViewChild('LyricsModal', { static: true }) lyricsModal!: ModalDirective;
  sRequest: SongRequest = {};
  arRequest: ArtistRequest = {};
  alRequest: AlbumRequest = {};

  lyFile!: File;

  songs: any[] = [];
  constructor(
    private songService: SongService,
    private artistService: ArtistService,
    private albumService: AlbumService,
    private lyricsService: LyricsService
  ) {}

  ngOnInit(): void {
    this.getAllSong();
  }

  getAllSong() {
    this.songService.getAllSong().subscribe((response) => {
      this.songs = response;
    });
  }

  createSong() {
    this.songService.createSong(this.sRequest).subscribe(
      (data) => {
        console.log('add song successful', data);
        alert('add song successful');
        this.getAllSong();
        this.closeModal('song');
      },
      (error) => {
        console.log('add failed', error);
        alert('add failed');
        this.closeModal('song');
      }
    );
  }

  deleteSong(song_id: number) {
    if (confirm('Are you sure you want to delete?')) {
      this.songService.deleteSong(song_id).subscribe(
        (data) => {
          console.log('delete song successful', data);
          this.getAllSong();
        },
        (error) => {
          console.log('delete failed', error);
        }
      );
    }
  }

  createArtist() {
    this.artistService.createArtist(this.arRequest).subscribe(
      (data) => {
        console.log('add artist successful', data);
        alert('add artist successful');
        this.closeModal('artist');
      },
      (error) => {
        console.log('add failed', error);
        alert('add failed');
        this.closeModal('artist');
      }
    );
  }

  createAlbum() {
    this.albumService.createAlbum(this.alRequest).subscribe(
      (data) => {
        console.log('add album successful', data);
        alert('add album successful');
        this.closeModal('album');
      },
      (error) => {
        console.log('add failed', error);
        alert('add failed');
        this.closeModal('album');
      }
    );
  }

  createLyrics() {
    const formData = new FormData()
    formData.append ("file",this.lyFile)
    this.lyricsService.createLyrics(formData).subscribe(
      (data) => {
        console.log('add lyrics successful', data);
        alert('add lyrics successful');
        this.closeModal('lyrics');
      },
      (error) => {
        console.log('add failed', error);
        alert('add failed');
        this.closeModal('lyrics');
      }
    );
  }

  openModal(modalName: string) {
    switch (modalName) {
      case 'song':
        this.songModal.show();
        break;
      case 'artist':
        this.artistModal.show();
        break;
      case 'album':
        this.albumModal.show();
        break;
      case 'lyrics':
        this.lyricsModal.show();
        break;
      default:
        break;
    }
  }

  closeModal(modalName: string) {
    switch (modalName) {
      case 'song':
        this.songModal.hide();
        break;
      case 'artist':
        this.artistModal.hide();
        break;
      case 'album':
        this.albumModal.hide();
        break;
      case 'lyrics':
        this.lyricsModal.hide();
        break;
      default:
        break;
    }
  }
}
