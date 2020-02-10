import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITurnstile, Turnstile } from 'app/shared/model/turnstile.model';
import { TurnstileService } from './turnstile.service';

@Component({
  selector: 'jhi-turnstile-update',
  templateUrl: './turnstile-update.component.html'
})
export class TurnstileUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    identifier: [],
    tbControllerId: [],
    tbDisplayId: [],
    cameraId: [],
    x1: [],
    y1: [],
    x2: [],
    y2: []
  });

  constructor(protected turnstileService: TurnstileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ turnstile }) => {
      this.updateForm(turnstile);
    });
  }

  updateForm(turnstile: ITurnstile) {
    this.editForm.patchValue({
      id: turnstile.id,
      identifier: turnstile.identifier,
      tbControllerId: turnstile.tbControllerId,
      tbDisplayId: turnstile.tbDisplayId,
      cameraId: turnstile.cameraId,
      x1: turnstile.x1,
      y1: turnstile.y1,
      x2: turnstile.x2,
      y2: turnstile.y2
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const turnstile = this.createFromForm();
    if (turnstile.id !== undefined) {
      this.subscribeToSaveResponse(this.turnstileService.update(turnstile));
    } else {
      this.subscribeToSaveResponse(this.turnstileService.create(turnstile));
    }
  }

  private createFromForm(): ITurnstile {
    return {
      ...new Turnstile(),
      id: this.editForm.get(['id']).value,
      identifier: this.editForm.get(['identifier']).value,
      tbControllerId: this.editForm.get(['tbControllerId']).value,
      tbDisplayId: this.editForm.get(['tbDisplayId']).value,
      cameraId: this.editForm.get(['cameraId']).value,
      x1: this.editForm.get(['x1']).value,
      y1: this.editForm.get(['y1']).value,
      x2: this.editForm.get(['x2']).value,
      y2: this.editForm.get(['y2']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITurnstile>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
