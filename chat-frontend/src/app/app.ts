import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { LucideAngularModule, LayoutDashboard, Users, ShoppingBag, Receipt, MessageSquare, Menu, X } from 'lucide-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive, LucideAngularModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  isSidebarCollapsed = false;

  readonly DashboardIcon = LayoutDashboard;
  readonly UsersIcon = Users;
  readonly ProductsIcon = ShoppingBag;
  readonly BillsIcon = Receipt;
  readonly ChatIcon = MessageSquare;
  readonly MenuIcon = Menu;
  readonly CloseIcon = X;

  toggleSidebar() {
    this.isSidebarCollapsed = !this.isSidebarCollapsed;
  }
}
