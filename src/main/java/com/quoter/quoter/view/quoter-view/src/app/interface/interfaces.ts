export interface IResponse {
  line:string
  book:IBook
}

export interface IBook{
  line:string
  id:string
  title:string
  authors:Array<IAuthor>
  subjects:Array<ISubject>
  urlFormat:string
}

export interface IAuthor{
  name:string
  birth_year:string
  death_year:string
}

export interface ISubject{
  name:string
}

