export class CurrentUser {
    constructor(
        public login: boolean,
        public username: string,
        public name: string,
        public surname: string,
        public role: string,
        public registrationDay: string,
        public birth : string,
        public street: string,
        public number: number,
        public password: string,
        public approved: boolean,
		public latitude: number,
		public longitude: number,
        public id: string,
        public gender: boolean
    ){}
}
