import { GeoJson } from "./geo-json";
import { Theater } from "./theather";

export class Cinema {
    constructor(
        public id : string,
        public name: string,
        public street: string,
        public number: string,
        public location: GeoJson,
        public rates:Map<String, Rate>,
        public ranking:number,
        public theaters:Theater[]
        
    ){}
    }