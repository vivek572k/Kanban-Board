import { CanDeactivateFn } from '@angular/router';
import { AddBoardComponent } from './add-board/add-board.component';

export const canDeactivateGuard: CanDeactivateFn<AddBoardComponent> = (component) => {
  return component.canDeactivate();
};
