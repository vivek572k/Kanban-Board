import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalStageComponent } from './additional-stage.component';

describe('AdditionalStageComponent', () => {
  let component: AdditionalStageComponent;
  let fixture: ComponentFixture<AdditionalStageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdditionalStageComponent]
    });
    fixture = TestBed.createComponent(AdditionalStageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
