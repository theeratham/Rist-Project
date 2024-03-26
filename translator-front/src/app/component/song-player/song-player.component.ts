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
  songCurrentTime: string = '';
  songDuration: string = '';

  constructor() {}

  ngOnInit(): void {
    this.audioPlayer = document.getElementById(
      'audio-player-player'
    )! as HTMLAudioElement;
    this.audioPlayer.load();
    this.songDuration = String(this.audioPlayer.duration);
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

  formatTime(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time % 60);
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  }

  updateTrackTime() {
    const currTime = this.audioPlayer.currentTime;
    const duration = this.audioPlayer.duration;
    const trackProgress = currTime / duration;
    document.getElementById('song-progress-progress')!.style.width = `${
      trackProgress * 100
    }%`;
    this.songCurrentTime = this.formatTime(Math.round(currTime));
    this.songDuration = this.formatTime(Math.round(duration));
  }
}
