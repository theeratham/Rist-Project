import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  @ViewChild('modal', { static: true }) modal!:ModalDirective;
  playlists: { name: string, description: string }[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  openModal() {
    this.modal.show()
  }

  closeModal() {
    this.modal.hide()
  }

  addPlaylist() {
    const playlistName = document.getElementById('playlistName') as HTMLInputElement;
    const playlistDescription = document.getElementById('playlistDescription') as HTMLInputElement;
    if (playlistName && playlistDescription) {
      this.playlists.push({ name: playlistName.value, description: playlistDescription.value });
      playlistName.value = '';
      playlistDescription.value = '';
    }
    this.closeModal()
  }


}
