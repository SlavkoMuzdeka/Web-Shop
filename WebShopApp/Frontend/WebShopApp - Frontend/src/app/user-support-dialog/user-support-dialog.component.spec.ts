import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSupportDialogComponent } from './user-support-dialog.component';

describe('UserSupportDialogComponent', () => {
  let component: UserSupportDialogComponent;
  let fixture: ComponentFixture<UserSupportDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserSupportDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserSupportDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
