import { Component, OnInit } from '@angular/core';
import {
  EmployeeResponse,
  EmployeesService,
} from 'client/src/app/core/modules/openapi';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

export interface PeriodicElement {
  firstName: string;
  lastName: string;
  salary: number;
}

@Component({
  selector: 'app-employees-table',
  templateUrl: './employees-table.component.html',
  styleUrls: ['./employees-table.component.css'],
})
export class EmployeesTableComponent implements OnInit {
  employees$: Observable<PeriodicElement[]> = of([]);

  constructor(private employeeService: EmployeesService) {}

  ngOnInit(): void {
    this.employees$ = this.employeeService.allEmployees().pipe(
      map((employees: EmployeeResponse[]) =>
        employees.map((employee) => ({
          firstName: employee.firstName,
          lastName: employee.lastName,
          salary: employee.salary !== null ? employee.salary : 0,
        }))
      )
    );
  }

  displayedColumns: string[] = ['firstName', 'lastName', 'salary'];
}
