export interface IGeometry {
    type: string;
    coordinates: number[];
}

export interface IGeoJson {
    type: string;
    coordinates: number[];
    bbox?: number[];
    properties?: any;
}


export class GeoJson implements IGeoJson {
  constructor(public type, public coordinates, properties?, bbox?) {}
}

export class Geometry implements IGeometry {
    constructor(public type, public coordinates) {}
  }