import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared';
import {
  TurnstileComponent,
  TurnstileDetailComponent,
  TurnstileUpdateComponent,
  TurnstileDeletePopupComponent,
  TurnstileDeleteDialogComponent,
  turnstileRoute,
  turnstilePopupRoute
} from './';

const ENTITY_STATES = [...turnstileRoute, ...turnstilePopupRoute];

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TurnstileComponent,
    TurnstileDetailComponent,
    TurnstileUpdateComponent,
    TurnstileDeleteDialogComponent,
    TurnstileDeletePopupComponent
  ],
  entryComponents: [TurnstileComponent, TurnstileUpdateComponent, TurnstileDeleteDialogComponent, TurnstileDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterTurnstileModule {}
