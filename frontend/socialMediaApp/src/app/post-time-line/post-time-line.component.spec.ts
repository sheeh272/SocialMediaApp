import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostTimeLineComponent } from './post-time-line.component';

describe('PostTimeLineComponent', () => {
  let component: PostTimeLineComponent;
  let fixture: ComponentFixture<PostTimeLineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostTimeLineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostTimeLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
