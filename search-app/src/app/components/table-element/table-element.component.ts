import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
@Component({
  selector: 'app-table-element',
  templateUrl: './table-element.component.html',
  styleUrls: ['./table-element.component.css']
})
export class TableElementComponent implements OnInit {
  searchResults: any;
  constructor(private searchService: SearchService) { }

  ngOnInit(): void {
    this.searchResults = this.searchService.getResults();
  }

}
