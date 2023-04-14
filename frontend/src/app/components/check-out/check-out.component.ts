import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder } from '@angular/forms';
import { Country } from 'src/app/model/country';
import { State } from 'src/app/model/state';
import { StateCountryServiceService } from 'src/app/service/state-country-service.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css'],
})
export class CheckOutComponent implements OnInit {
  checkoutParentGroup!: FormGroup;

  countries: Country[] = [];
  states: State[] = [];

  constructor(
    private formChildGroup: FormBuilder,
    private stateCountry: StateCountryServiceService
  ) {}

  ngOnInit(): void {
    this.createForm();
    this.getAllCountries();
    this.getAllStates();
  }

  createForm() {
    this.checkoutParentGroup = this.formChildGroup.group({
      data: this.formChildGroup.group({
        fullName: [''],
        gmail: [''],
        phone: [''],
      }),
      fromPerson: this.formChildGroup.group({
        country: [''],
        state: [''],
        zipCode: [''],
      }),

      toPerson: this.formChildGroup.group({
        country: [''],
        state: [''],
        zipCode: [''],
      }),
      creditCard: this.formChildGroup.group({
        cardType: [''],
        cardNumber: [''],
        code: [''],
      }),
    });
  }

  done() {
    console.log(this.checkoutParentGroup.get('data')?.value);
  }

  similarGroup(event: Event) {
    // cast to html inpput element
    if ((<HTMLInputElement>event.target).checked) {
      this.checkoutParentGroup
        .get('toPerson')
        ?.setValue(this.checkoutParentGroup.get('fromPerson')?.value);
    } else {
      this.checkoutParentGroup.get('toPerson')?.reset();
    }
  }

  getAllCountries() {
    this.stateCountry.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
  }

  getAllStates() {
    this.stateCountry.getAllStates().subscribe((data) => {
      this.states = data;
    });
  }
}
