import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { PlaylistRequest } from 'src/app/component/request/playlist-request';
import { PlaylistService } from 'src/app/service/playlist/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css'],
})
export class PlaylistComponent implements OnInit {
  @ViewChild('modal', { static: true }) modal!: ModalDirective;
  playlists: any[] = [];
  request: PlaylistRequest = {};

  constructor(private playlistService: PlaylistService) {}

  ngOnInit(): void {
    this.getAllPlaylist();
  }

  openModal() {
    this.modal.show();
  }

  closeModal() {
    this.modal.hide();
  }

  getAllPlaylist() {
    this.playlistService.getAllPlaylist().subscribe((response) => {
      this.playlists = response;
    });
  }

  createPlaylist() {
    this.playlistService.createPlaylist(this.request).subscribe(
      (data) => {
        console.log('add playlist successfully', data);
        alert('add playlist successful');
        this.getAllPlaylist();
        this.request = {};
      },
      (error) => {
        console.log('add failed', error);
        alert('add failed');
      }
    );
  }

  deletePlaylist(playlist_id: number) {
    if (confirm('Are yuo sure you want to delete?')) {
      this.playlistService.deletePlaylist(playlist_id).subscribe(
        (data) => {
          console.log('delete song successful', data);
          this.getAllPlaylist();
        },
        (error) => {
          console.log('delete failed', error);
        }
      );
    }
  }
}
