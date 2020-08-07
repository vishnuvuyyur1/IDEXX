import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SearchService } from 'src/app/service/search.service';
import { first } from 'rxjs/operators';
@Component({
  selector: 'app-search-element',
  templateUrl: './search-element.component.html',
  styleUrls: ['./search-element.component.css']
})
export class SearchElementComponent implements OnInit {
  termFrom: FormGroup;
  submitted = false;
  loading: boolean;
  searchResults: any;
  error: boolean;
  constructor(private formBuilder: FormBuilder,
    private searchService: SearchService) { }

  ngOnInit(): void {
    this.termFrom = this.formBuilder.group({
      term: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9.-_$@*!]{3,30}$")]],
    });
  }

  get f() { return this.termFrom.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.termFrom.invalid) {
      return;
    }
    this.loading = true;
    this.searchService.getBookAndAlbum(this.f.term.value)
      .pipe(first())
      .subscribe(
        data => {
          this.loading = false;
          this.searchService.setResults(data);
        },
        error => {
          this.loading = false;
          this.error = true;
        });
  }

  close() {
    this.error = false;
  }

}