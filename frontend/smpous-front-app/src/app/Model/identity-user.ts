export class IdentityUser {
    constructor(
        public name: string,
        public surname: string,
        public username: string,
        public password: string,
        public contact: string,
        public birth : string,
        public adresa: string,
        public confirmPassword: string,
		public latitude : number,
		public longitude : number
    ){}
}
