import { TheaterType } from "./theathertype";

export class Theater {
    constructor(
        public id : string,
        public name: string,
        public capacity:number,
        public theaterType:TheaterType,
        public chairsPerRow:number,
        public chairsPerColumn:number
        
    ){}
    }