import { Component, OnInit } from '@angular/core';

import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { Address } from 'src/app/model/address';
import { CartOrder } from 'src/app/model/cart-order';
import { Client } from 'src/app/model/client';
import { Country } from 'src/app/model/country';
import { Item } from 'src/app/model/item';
import { PurchaseRequest } from 'src/app/model/purchase-request';
import { RequestOrder } from 'src/app/model/request-order';
import { State } from 'src/app/model/state';
import { CardServiceService } from 'src/app/service/card-service.service';
import { PurchaseServiceService } from 'src/app/service/purchase-service.service';
import { StateCountryServiceService } from 'src/app/service/state-country-service.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css'],
})
export class CheckOutComponent implements OnInit {
  checkoutParentGroup!: FormGroup;

  countries: Country[] = [];
  statesFromPerson: State[] = [];
  statesToPerson: State[] = [];
  totalOrders: number = 0;
  totalPrices: number = 0;

  constructor(
    private formChildGroup: FormBuilder,
    private stateCountry: StateCountryServiceService,
    private cardService: CardServiceService,
    private ps: PurchaseServiceService
  ) {}

  ngOnInit(): void {
    this.createForm();
    this.getAllCountries();
    this.getTotals();

    //  this.getStatesByCode();
  }

  createForm() {
    this.checkoutParentGroup = this.formChildGroup.group({
      data: this.formChildGroup.group({
        fullName: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
        gmail: new FormControl('', [
          Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        ]),
        phone: new FormControl('', [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(8),
          Validators.pattern('^[0-9]*$'),
        ]),
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

  get fullName() {
    return this.checkoutParentGroup.get('data.fullName');
  }
  get gmail() {
    return this.checkoutParentGroup.get('data.gmail');
  }
  get phone() {
    return this.checkoutParentGroup.get('data.phone');
  }

  done() {
    // console.log(this.checkoutParentGroup.get('data')?.value);
    // console.log(this.checkoutParentGroup.get('data.fullName')?.value);
    console.log(this.checkoutParentGroup.controls['data'].value);

    if (this.checkoutParentGroup.invalid) {
      this.checkoutParentGroup.markAllAsTouched();
    } else {
      let client: Client = new Client(
        this.checkoutParentGroup.controls['data'].value.fullName,
        this.checkoutParentGroup.controls['data'].value.gmail,
        this.checkoutParentGroup.controls['data'].value.phone
      );

      let fromAddress = new Address(
        this.checkoutParentGroup.controls['fromPerson'].value.country,
        this.checkoutParentGroup.controls['fromPerson'].value.state['name'],
        this.checkoutParentGroup.controls['fromPerson'].value.zipCode,
        this.checkoutParentGroup.controls['toPerson'].value.country,
        this.checkoutParentGroup.controls['toPerson'].value.state['name'],
        this.checkoutParentGroup.controls['toPerson'].value.zipCode
      );
      // fromAddress.state = fromAddress.state['name'];

      // let toAddress = this.checkoutParentGroup.controls['toPerson'].value;
      let requestOrder = new RequestOrder(0, 0);
      requestOrder.totalPrice = this.totalPrices;
      requestOrder.totalQuantity = this.totalOrders;
      let items: Item[] = [];
      let orders: CartOrder[] = this.cardService.orders;
      for (let i = 0; i < orders.length; i++) {
        items[i] = new Item(orders[i]);
      }
      let purchaseRequest = new PurchaseRequest(
        client,
        fromAddress,
        requestOrder,
        items
      );
      console.log(purchaseRequest.client);
      console.log(purchaseRequest.fromAddress);
      console.log(purchaseRequest.requestOrder);
      console.log(purchaseRequest.items);
      this.ps.getOrder(purchaseRequest).subscribe({
        next: (response) => {
          alert('ok');
        },
        error: (error) => {},
      });
    }
  }

  similarGroup(event: Event) {
    // cast to html inpput element
    if ((<HTMLInputElement>event.target).checked) {
      this.checkoutParentGroup
        .get('toPerson')
        ?.setValue(this.checkoutParentGroup.get('fromPerson')?.value);
      this.statesToPerson = this.statesFromPerson;
    } else {
      this.checkoutParentGroup.get('toPerson')?.reset();
    }
  }

  getAllCountries() {
    this.stateCountry.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
  }

  getTotals() {
    this.cardService.totalOrders.subscribe((data) => {
      this.totalOrders = data;
    });
    this.cardService.totalprice.subscribe((data) => {
      this.totalPrices = data;
    });
  }

  /* getAllStates() {
    this.stateCountry.getAllStates().subscribe((data) => {
      this.states = data;
    });
  }  */

  getStatesByCode(typeForm: any) {
    const code = this.checkoutParentGroup.get(`${typeForm}.country`)?.value;
    this.stateCountry.getStatesByCode(code).subscribe((data) => {
      if (typeForm === 'fromPerson') {
        this.statesFromPerson = data;
      } else {
        this.statesToPerson = data;
      }
      this.checkoutParentGroup.get(`${typeForm}.state`)?.setValue(data[0]);
    });
  }
}
