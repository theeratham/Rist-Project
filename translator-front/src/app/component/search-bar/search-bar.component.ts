import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchbarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  searchQuery: string = '';
  @Output() searchEvent = new EventEmitter<string>();

  search(query: string) {
    // Perform search logic here
    console.log('Search query:', query);
    // You can navigate to a search results page or update the current component's content based on the search query
  }

}
