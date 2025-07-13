import { Component, Input } from '@angular/core';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { TransactionService } from '../../service/transaction.service';
import { Transaction } from '../../service/account.service';

@Component({
    standalone: true,
    selector: 'app-transactions-widget',
    imports: [CommonModule, TableModule, ButtonModule, RippleModule],
    template: ` <div class="card !mb-8">
        <div class="font-semibold header-color text-xl mb-4">Transazioni</div>
        <p-table [value]="transactions? transactions : []" [paginator]="true" [rows]="5" responsiveLayout="scroll">
            <ng-template #header>
                <tr>
                    <th pSortableColumn="timestamp">
                        Data
                        <p-sortIcon field="timestamp"></p-sortIcon>
                    </th>
                    <th pSortableColumn="amount">
                        Ammontare
                        <p-sortIcon field="amount"></p-sortIcon>
                    </th>
                    <th pSortableColumn="description">
                        Descrizione
                        <p-sortIcon field="description"></p-sortIcon>
                    </th>
                    <th pSortableColumn="type">
                        Tipo
                        <p-sortIcon field="type"></p-sortIcon>
                    </th>
                </tr>
            </ng-template>
            <ng-template #body let-transaction>
                <tr>
                    <td style="width: 35%; min-width: 7rem;">{{ transaction.timestamp | date: 'medium'}}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ transaction.amount | currency: 'EUR' }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ transaction.description }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ transaction.type }}</td>
                </tr>
            </ng-template>
        </p-table>
    </div>`,
    providers: [TransactionService]
})
export class TransactionsWidget {
    @Input() transactions: Transaction[] | undefined;

    constructor(private productService: TransactionService) {}
}
