import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Employee } from '../model/employee';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  employees:Employee[]=[];

  constructor(private service:HttpService){}
  ngOnInit(): void {
      this.getDataFromBackend();
  }
  getDataFromBackend(){
    this.service.getAllEmpRecors()
    .subscribe((response:any)=>{
      console.log(response);
      this.employees=response;
    })
  }
  onDelete(id:any){
    if(confirm("Do you want to Delet?")){
      // console.log("====")
      //delete Api called
      this.service.deleteEmpData(id)
      .subscribe((response)=>{
        console.log(response);
        this.getDataFromBackend();
      })
    }
  }
}
