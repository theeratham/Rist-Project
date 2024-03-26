import { Component, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageCroppedEvent, LoadedImage } from 'ngx-image-cropper';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent {
  @ViewChild('modal', { static: true }) modal!: ModalDirective;

  username: string = 'username';
  name: string = 'name';
  lastname: string = 'lastname';
  email: string = 'name@hotmail.com';

  imageChangedEvent: any = '';
  croppedImage: any = '';

  constructor(private sanitizer: DomSanitizer) {}

  openModal() {
    this.modal.show();
  }

  closeModal() {
    this.modal.hide();
  }

  cropFinished() {
    this.closeModal();
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.openModal();
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = this.sanitizer.bypassSecurityTrustUrl(event.objectUrl!);
    // event.blob can be used to upload the cropped image
  }
  imageLoaded(image: LoadedImage) {
    // show cropper
  }
  cropperReady() {
    // cropper ready
  }
  loadImageFailed() {
    // show message
  }
}
