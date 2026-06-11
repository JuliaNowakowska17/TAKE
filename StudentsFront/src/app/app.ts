import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { StudentsComponent } from './students/students.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, StudentsComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  title = signal('Students');
}