import { Component, Input, numberAttribute, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MortgageApplication, Transaction } from '../../service/account.service';
import { TableModule } from 'primeng/table';
import { MortgageStatusPipe } from './mortgage-status-pipe.component';
import { Tag } from 'primeng/tag';

@Component({
    standalone: true,
    selector: 'app-user-mortgage-widget',
    imports: [CommonModule, FormsModule, TableModule, MortgageStatusPipe, Tag],

    template: `
        <div class="card !mb-8">
            <div class="font-semibold header-color text-xl mb-4">Mutui</div>
            <p-table [value]="mortgageApplications ? mortgageApplications : []" [paginator]="true" [rows]="5" responsiveLayout="scroll">
                <ng-template #header>
                    <tr>
                        <th pSortableColumn="name">
                            Nome mutuo
                            <p-sortIcon field="name"></p-sortIcon>
                        </th>
                        <th pSortableColumn="appliedAmount">
                            Ammontare Accreditato
                            <p-sortIcon field="appliedAmount"></p-sortIcon>
                        </th>
                        <th pSortableColumn="interestType">
                            Tipo interessi
                            <p-sortIcon field="interestType"></p-sortIcon>
                        </th>
                        <th pSortableColumn="interestAmount">
                            Percentuale Interessi
                            <p-sortIcon field="interestAmount"></p-sortIcon>
                        </th>
                        <th pSortableColumn="mortgageStatus">Stato mutuo
                            <p-sortIcon field="mortgageStatus"></p-sortIcon>
                        </th>
                    </tr>
                </ng-template>
                <ng-template #body let-mortgage_application>
                    <tr>
                        <td style="width: 35%; min-width: 7rem;">{{ mortgage_application.name  }}</td>
                        <td style="width: 35%; min-width: 7rem;">{{ mortgage_application.appliedAmount | currency: 'EUR' }}</td>
                        <td style="width: 35%; min-width: 8rem;">{{ mortgage_application.interestType }}</td>
                        <td style="width: 35%; min-width: 8rem;">{{ mortgage_application.interestAmount }}%</td>
                        <td style="width: 35%; min-width: 8rem;">
                            <p-tag *ngIf="mortgage_application.mortgageStatus | mortgageStatusPipe as mortgageStatusTag"
                                   [severity]="mortgageStatusTag.severity" [value]="mortgageStatusTag.label"></p-tag>
                        </td>
                    </tr>
                </ng-template>
            </p-table>
        </div>
    `
})
export class UserMortgageWidget {
    @Input() mortgageApplications: MortgageApplication[] | undefined;

    @Input({ transform: numberAttribute }) balance: number = 0.0;
}
