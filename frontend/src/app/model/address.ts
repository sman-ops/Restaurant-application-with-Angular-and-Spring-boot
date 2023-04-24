export class Address {
  country: string;
  state: string;
  zipCode: string;
  tocountry: string;
  tostate: string;
  toZipCode: string;

  constructor(
    country: string,
    state: string,
    zipCode: string,
    tocountry: string,
    tostate: string,
    toZipCode: string
  ) {
    this.country = country;
    this.state = state;
    this.zipCode = zipCode;
    this.tocountry = tocountry;
    this.tostate = tostate;
    this.toZipCode = toZipCode;
  }
}
