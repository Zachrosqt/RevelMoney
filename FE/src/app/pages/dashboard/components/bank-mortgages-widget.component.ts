import { Component, OnInit } from '@angular/core';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { Mortgage, MortgageService } from '../../service/mortgage.service';

@Component({
    standalone: true,
    selector: 'app-bank-mortgages-widget',
    imports: [CommonModule, TableModule, ButtonModule, RippleModule],
    template: ` <div class="card !mb-8">
        <div class="font-semibold header-color text-xl mb-4">Mutui Disponibili</div>
        <p-table [value]="mortgages ? mortgages : []" [paginator]="true" [rows]="5" responsiveLayout="scroll">
            <ng-template #header>
                <tr>
                    <th pSortableColumn="name">
                        Nome
                        <p-sortIcon field="name"></p-sortIcon>
                    </th>
                    <th pSortableColumn="description">
                        Descrizione
                        <p-sortIcon field="description"></p-sortIcon>
                    </th>
                    <th pSortableColumn="maxAmount">
                        Ammontare Massimo
                        <p-sortIcon field="maxAmount"></p-sortIcon>
                    </th>
                    <th pSortableColumn="interestAmount">
                        Percentuale Interessi
                        <p-sortIcon field="interestAmount"></p-sortIcon>
                    </th>
                    <th pSortableColumn="maxLtvRatio">
                        Percentuale LTV massima
                        <p-sortIcon field="maxLtvRatio"></p-sortIcon>
                    </th>
                    <th pSortableColumn="validFrom">
                        Valido da
                        <p-sortIcon field="validFrom"></p-sortIcon>
                    </th>
                    <th pSortableColumn="validTo">
                        Valido fino a
                        <p-sortIcon field="validTo"></p-sortIcon>
                    </th>
                </tr>
            </ng-template>
            <ng-template #body let-mortgage>
                <tr>
                    <td style="width: 35%; min-width: 7rem;">{{ mortgage.name }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ mortgage.description }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ mortgage.maxAmount | currency: 'EUR' }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ mortgage.interestAmount | percent }}</td>
                    <td style="width: 35%; min-width: 7rem;">{{ mortgage.maxLtvRatio | percent }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ mortgage.validFrom | date }}</td>
                    <td style="width: 35%; min-width: 8rem;">{{ mortgage.validTo | date }}</td>
                </tr>
            </ng-template>
        </p-table>
    </div>`,
    providers: [MortgageService]
})
export class BankMortgagesWidget implements OnInit {
    mortgages: Mortgage[] = [];

    constructor(private mortgageService: MortgageService) {}

    ngOnInit() {
        this.mortgageService.getList().subscribe((value) => {
            this.mortgages = value;
        });
    }
}
