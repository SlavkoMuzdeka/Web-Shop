import { Component, EventEmitter, Output } from '@angular/core';
import { CategoryService } from './services/category.service';
import { Category } from '../model/category.model';
import { FlatTreeControl } from '@angular/cdk/tree';
import {
  MatTreeFlatDataSource,
  MatTreeFlattener,
} from '@angular/material/tree';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

interface ExampleFlatNode {
  expandable: boolean;
  name: string | undefined;
  level: number;
  id: number | undefined;
  clicked?: boolean;
}


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent {
  private _transformer = (node: Category, level: number) => {
    return {
      expandable: !!node.categories && node.categories.length > 0,
      name: node.name,
      level: level,
      id: node.id,
    };
  };

  treeControl = new FlatTreeControl<ExampleFlatNode>(
    (node) => node.level,
    (node) => node.expandable
  );

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    (node) => node.level,
    (node) => node.expandable,
    (node) => node.categories
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  @Output() categoryClicked = new EventEmitter<number>();
  selectedNode: any;

  constructor(private _service: CategoryService) {
    this.dataSource.data = [];
    this._service.getCategoryData().subscribe((categories: any) => {
      this.dataSource.data = categories;
    });
  }

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

  onNodeClicked(node: ExampleFlatNode) {
    this.categoryClicked.emit(node.id);
    if (this.selectedNode) {
      if (this.selectedNode === node) {
        node.clicked = false;
        this.selectedNode = null;
        return;
      }
      this.selectedNode.clicked = false;
    }
    node.clicked = true;
    this.selectedNode = node;
  }
}
