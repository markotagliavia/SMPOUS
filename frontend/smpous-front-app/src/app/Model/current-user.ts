export class CurrentUser {
    constructor(
        public login: boolean,
        public username: string,
        public name: string,
        public surname: string,
        public role: string,
        public token: string,
        public contact: string,
        public birth : string,
        public adresa: string,
        public password: string,
        public approved: boolean,
		public latitude: number,
		public longitude: number,
        public Path: string,
        public id: number
    ){}
}
