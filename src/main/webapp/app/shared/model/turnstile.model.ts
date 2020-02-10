export interface ITurnstile {
  id?: number;
  identifier?: string;
  tbControllerId?: string;
  tbDisplayId?: string;
  cameraId?: string;
  x1?: number;
  y1?: number;
  x2?: number;
  y2?: number;
}

export class Turnstile implements ITurnstile {
  constructor(
    public id?: number,
    public identifier?: string,
    public tbControllerId?: string,
    public tbDisplayId?: string,
    public cameraId?: string,
    public x1?: number,
    public y1?: number,
    public x2?: number,
    public y2?: number
  ) {}
}
