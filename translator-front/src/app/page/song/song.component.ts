import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
  @ViewChild('modal', { static: true }) modal!: ModalDirective


  selectedFile: File | null = null;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  addSong() {
    if (!this.selectedFile) {
      console.log('No file selected.')
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name)

    this.http.post('http://localhost:8080/admin/upload', formData)
      .subscribe((response) => {
        console.log('File uploaded successfully.', response)
        // Add logic to save file details to database or perform other actions
        this.closeModal();
      }, (error) => {
        console.error('Error uploading file.', error)
      })
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0]
  }

  openModal() {
    this.modal.show()
  }

  closeModal() {
    this.modal.hide()
  }
}
