import { Component, Input } from '@angular/core';
import { LoginService } from '../auth/services/login.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { UserSupportDialogComponent } from '../user-support-dialog/user-support-dialog.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  @Input() public title?: string;
  
  constructor(
    private loginService: LoginService,
    private router: Router,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  isLogedIn(): boolean {
    return this.loginService.signedIn;
  }

  getAvatar() {
    return this.loginService.getImage();
  }

  showPurchasedProducts() {
    this.router.navigate([
      '/purchasedProducts',
      this.loginService.activeUser?.id,
    ]);
  }

  showActiveProducts() {
    this.router.navigate([
      '/activeProducts',
      this.loginService.activeUser?.id,
    ]);
  }

  showFinishedProducts() {
    this.router.navigate([
      '/finishedProducts',
      this.loginService.activeUser?.id,
    ]);
  }

  sendMessageToUserSupport() {
    const dialogRef = this.dialog
      .open(UserSupportDialogComponent, {
        width: '400px',
        data: { id: this.loginService.id },
      })
  }

  changeAccountData() {
    this.router.navigate(['/accountData']);
  }

  logout() {
    this.loginService.logout();
    this.snackBar.open('You have been logged out', undefined, {
      duration: 2000,
    });
  }
}
