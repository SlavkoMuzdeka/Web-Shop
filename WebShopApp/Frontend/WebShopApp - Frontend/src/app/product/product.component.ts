import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../home/home/services/product.service';
import { faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { faAngleRight } from '@fortawesome/free-solid-svg-icons';
import { faLocationDot } from '@fortawesome/free-solid-svg-icons';
import { faTag } from '@fortawesome/free-solid-svg-icons';
import { faClock } from '@fortawesome/free-solid-svg-icons';
import { faContactCard } from '@fortawesome/free-solid-svg-icons';
import { ProductAttribute } from '../model/productAttribute.model';
import { faMailForward } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../auth/services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentService } from './services/comment.service';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { MatDialog } from '@angular/material/dialog';
import { TypeOfPurchasingComponent } from '../type-of-purchasing/type-of-purchasing.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  private _product?: Product;
  private productId?: number;

  displayedColumns: string[] = ['attribute', 'value'];
  dataSource: ProductAttribute[] = [];
  visibleComments = 2;
  showAllComments = false;
  newComment: any = { text: '' };

  faAngleLeft = faAngleLeft;
  faAngleRight = faAngleRight;
  faLocationDot = faLocationDot;
  faTag = faTag;
  faClock = faClock;
  faContactCard = faContactCard;
  faMailForward = faMailForward;
  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;

  public selectedIndex: number = 0;
  @Input() indicators = true;
  @Input() controls = true;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private snackBar: MatSnackBar,
    private commentService: CommentService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.productId = Number(params.get('id'));
      if (isNaN(this.productId)) {
        this.router.navigate(['/']);
      }
    });
    if (this.productId != undefined) {
      this.productService.getProduct(this.productId).subscribe(
        (product: any) => {
          this._product = product;
          this.dataSource = this._product?.productAttributes ?? [];
        },
        (error) => {
          this.router.navigate(['/']);
        }
      );
    }
  }

  get logedIn() {
    return this.loginService.signedIn;
  }

  getUserId() {
    return this.loginService.id;
  }

  get product() {
    return this._product;
  }

  selectImage(index: number): void {
    this.selectedIndex = index;
  }

  onPrevClick(): void {
    if (this.selectedIndex === 0) {
      this.selectedIndex = this._product?.images?.length
        ? this._product?.images.length - 1
        : 0;
    } else {
      this.selectedIndex--;
    }
  }

  onNextClick(): void {
    if (this.selectedIndex === (this._product?.images?.length ?? 0) - 1) {
      this.selectedIndex = 0;
    } else {
      this.selectedIndex++;
    }
  }

  postComment() {
    const user = {
      id: this.loginService.id,
    };
    const comment = {
      text: this.newComment.text,
      creationDate: new Date(),
      user: user,
    };
    if (this.newComment.text != '') {
      this.commentService
        .postComment(comment, this._product?.id)
        .then((response: any) => {
          if (response) {
            this.snackBar.open('Comment posted successfully!', undefined, {
              duration: 2000,
            });
            this.newComment.text = '';
            this._product?.comments?.push(response);
          } else {
            this.snackBar.open(
              'Failed to post comment. Please try again.',
              undefined,
              {
                duration: 2000,
              }
            );
            this.newComment.text = '';
          }
        })
        .catch((error: any) => {
          this.snackBar.open(
            'Failed to post comment. Please try again.',
            undefined,
            {
              duration: 2000,
            }
          );
          this.newComment.text = '';
        });
    }
  }

  buyProduct() {
    this.dialog
      .open(TypeOfPurchasingComponent, {
        width: '300px',
        data: { id: this.getUserId(), product_id: this.product?.id, product: this._product },
      })
      .afterClosed()
      .subscribe((result) => {
        if (result && this._product) {
          this._product.isSold = true;
        }
      });
  }
}
