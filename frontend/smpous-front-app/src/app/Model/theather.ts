import { Chair } from "./chair";

export class Theater {
    constructor(
        public id : string,
        public name: string,
        public capacity:number,
        public theaterType:TheaterType,
        public chair:Chair[]
        
    ){}
    }