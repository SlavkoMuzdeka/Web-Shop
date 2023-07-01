import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeOfPurchasingComponent } from './type-of-purchasing.component';

describe('TypeOfPurchasingComponent', () => {
  let component: TypeOfPurchasingComponent;
  let fixture: ComponentFixture<TypeOfPurchasingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeOfPurchasingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TypeOfPurchasingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
