import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserSupportService } from './services/user-support.service';

@Component({
  selector: 'app-user-support-dialog',
  templateUrl: './user-support-dialog.component.html',
  styleUrls: ['./user-support-dialog.component.css'],
})
export class UserSupportDialogComponent {
  message: any = { title: '', content: '' };

  constructor(
    private dialogRef: MatDialogRef<UserSupportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private snackBar: MatSnackBar,
    private userSupportService: UserSupportService
  ) {}

  closeDialog() {
    this.dialogRef.close();
  }

  sendMessage() {
    if (this.message.title != '' && this.message.content != '') {
      this.userSupportService
        .sendMessage(this.data.id, this.message.content, this.message.title)
        .subscribe((result: any) => {
          if (result.status_code == 200) {
            this.dialogRef.close(true);
            this.snackBar.open(
              'Message has been sent successfully!',
              undefined,
              {
                duration: 2000,
              }
            );
          }
        });
    }
  }
}
