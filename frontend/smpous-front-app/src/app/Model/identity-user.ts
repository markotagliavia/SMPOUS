export class IdentityUser {
    constructor(
        public name: string,
        public surname: string,
        public username: string,
        public password: string,
        public street: string,
        public number: number,
        public birth : string,
        public confirmPassword: string,
		public latitude : number,
		public longitude : number
    ){}
}
