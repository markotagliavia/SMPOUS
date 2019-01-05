export class AppUser {
    constructor(
			public Id : number,
      public Name: string,
      public Surname: string,
	  	public Username: string,
	  	public Contact: string,
	  	public BirthDate : string,
			public Adresa: string,
        public Latitude: number,
        public Longitude: number,
			public Approved: boolean,
			public LoggedIn: boolean,
      public Path: string,
			public Role : string 
  ){}
}
