export interface IResponse {
  line:string
  book:IBook
}

export interface IBook{
  id: number
  name: string
  description: string
  pictureUrl: string
}
