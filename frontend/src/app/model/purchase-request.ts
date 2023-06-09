import { Address } from './address';
import { Client } from './client';
import { Item } from './item';
import { RequestOrder } from './request-order';

export class PurchaseRequest {
  client: Client;
  fromAddress: Address;
  requestOrder: RequestOrder;
  items: Item[];

  constructor(
    client: Client,
    fromAddress: Address,
    requestOrder: RequestOrder,
    items: Item[]
  ) {
    this.client = client;
    this.fromAddress = fromAddress;
    this.requestOrder = requestOrder;
    this.items = items;
  }
}
