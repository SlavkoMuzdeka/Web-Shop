import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinishedProductsComponent } from './finished-products.component';

describe('FinishedProductsComponent', () => {
  let component: FinishedProductsComponent;
  let fixture: ComponentFixture<FinishedProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinishedProductsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FinishedProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
