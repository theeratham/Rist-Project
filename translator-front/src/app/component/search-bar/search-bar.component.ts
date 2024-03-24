import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchbarComponent implements OnInit {
  @Output() searchEvent = new EventEmitter<string>()
  searchInput:string = ''

  constructor() { }

  ngOnInit(): void {
  }

  onSearch() {
    this.searchEvent.emit(this.searchInput)
  }


}
