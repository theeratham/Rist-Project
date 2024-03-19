import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  onSearch(query: string) {
    // Perform search logic here
    console.log('Search query:', query)
  }

  
}
