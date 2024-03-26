import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-song-player',
  templateUrl: './song-player.component.html',
  styleUrls: ['./song-player.component.css'],
})
export class SongPlayerComponent implements OnInit {
  audioPlayer!: HTMLAudioElement;
  songUrl: string = 'https://samplelib.com/lib/preview/mp3/sample-12s.mp3';
  volume: number = 1;
  isPlaying: boolean = false;
  songCurrentTime: number = 0;
  songDuration: number = 0;

  constructor() {}

  ngOnInit(): void {
    this.audioPlayer = document.getElementById(
      'audio-player-player'
    )! as HTMLAudioElement;
    this.audioPlayer.load();
    this.songDuration = this.audioPlayer.duration;
  }

  setVolume(volumeChangeEvent: any) {
    this.audioPlayer.volume = volumeChangeEvent.target.value;
  }

  togglePlayPause() {
    if (this.audioPlayer.paused) {
      this.audioPlayer.play();
    } else {
      this.audioPlayer.pause();
    }
  }

  updateTrackTime() {
    const currTime = this.audioPlayer.currentTime;
    const duration = this.audioPlayer.duration;
    const trackProgress = currTime / duration;
    document.getElementById('song-progress-progress')!.style.width = `${
      trackProgress * 100
    }%`;
    console.log(document.getElementById('song-progress-progress')!);
  }
}
