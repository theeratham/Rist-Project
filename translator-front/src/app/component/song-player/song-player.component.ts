import { Component, OnInit, ViewChild } from '@angular/core';
import { AudioService } from 'src/app/service/audio.service';

@Component({
  selector: 'app-song-player',
  templateUrl: './song-player.component.html',
  styleUrls: ['./song-player.component.css']
})
export class SongPlayerComponent implements OnInit {
  @ViewChild('audioPlayer') audioPlayer: any;
  audioSrc = 'assets/audio/hbd_song.mp3'; // Path to your audio file
  isPlaying = false;

  constructor(private audioService: AudioService) { }

  ngOnInit(): void {
    // this.audioService.getSong().subscribe(
    //   (data:string) => {
    //     this.audioSrc = data
    //   }, 
    //   (error) => {
    //     console.error('Failed to fetch audio source', error)
    //   }
    // )
  }

  play() {
    if (this.audioPlayer.nativeElement.readyState === 4) { // Check if audio is ready to play
      this.audioPlayer.nativeElement.play();
      this.isPlaying = true;
    } else {
      this.audioPlayer.nativeElement.addEventListener('canplay', () => {
        this.audioPlayer.nativeElement.play();
        this.isPlaying = true;
      });
    }
  }
  
  pause() {
    if (!this.audioPlayer.nativeElement.paused) { // Check if audio is currently playing
      this.audioPlayer.nativeElement.pause();
      this.isPlaying = false;
    }
  }
  
  stop() {
    this.audioPlayer.nativeElement.pause();
    this.audioPlayer.nativeElement.currentTime = 0;
    this.isPlaying = false;
  }

  togglePlayPause() {
    if (this.isPlaying) {
      this.pause();
    } else {
      this.play();
    }
  }
  

}
