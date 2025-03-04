import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  baseUrl:string='http://localhost:8080/api/'
  constructor(private http:HttpClient) { }
  
  registerData(obj:any){
    return (this.http.post(`${this.baseUrl}user/save`,obj,{
      responseType:"text"
    }));
  }
  login(obj:any){
    return (this.http.post(`${this.baseUrl}user/login`,obj))

  }
  getAllEmpRecors(){
   return (this.http.get(`${this.baseUrl}emp/findall`))
  }
  getParticularRecordById(id:any){
    return (this.http.get(`${this.baseUrl}emp/findbyid/${id}`))
  }
  getAllCountry(){
   return (this.http.get(`${this.baseUrl}country/findall`))
  }
  postEmpData(obj:any){
    return (this.http.post(`${this.baseUrl}emp/save`,obj,{
      responseType:"text" 
      }));
  }
  updateEmpData(obj:any){
    return (this.http.put(`${this.baseUrl}emp/update`,obj,{
      responseType:"text"
    }));
  }
  deleteEmpData(id:any){
    return (this.http.delete(`${this.baseUrl}emp/deletebyid/${id}`,{
      responseType:"text"
    }));
  }
}  
