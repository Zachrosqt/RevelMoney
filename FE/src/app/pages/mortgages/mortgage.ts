import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FluidModule } from 'primeng/fluid';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { Select, SelectModule } from 'primeng/select';
import { FormsModule } from '@angular/forms';
import { TextareaModule } from 'primeng/textarea';
import { CurrencyPipe, DatePipe, NgIf, PercentPipe } from '@angular/common';
import { TableModule } from 'primeng/table';
import { Mortgage, MortgageService } from '../service/mortgage.service';
import { Listbox } from 'primeng/listbox';
import { Password } from 'primeng/password';
import { Router, RouterLink } from '@angular/router';
import { Slider } from 'primeng/slider';
import { MortgageApplication, MortgageApplicationRequest } from '../service/account.service';

@Component({
    selector: 'app-mortgages-form-page',
    standalone: true,
    imports: [InputTextModule, FluidModule, ButtonModule, SelectModule, FormsModule, TextareaModule, CurrencyPipe, DatePipe, PercentPipe, TableModule, RouterLink, Slider],
    template: ` <p-fluid>
        <div class="card !mb-8">
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
                        <td style="width: 35%; min-width: 8rem;">{{ mortgage.interestAmount }}%</td>
                        <td style="width: 35%; min-width: 7rem;">{{ mortgage.maxLtvRatio | percent }}</td>
                        <td style="width: 35%; min-width: 8rem;">{{ mortgage.validFrom | date }}</td>
                        <td style="width: 35%; min-width: 8rem;">{{ mortgage.validTo | date }}</td>
                    </tr>
                </ng-template>
            </p-table>
        </div>
        <div class="flex md:w-1/2 mt-8 justify-self-center">
            <div class="card flex flex-col gap-4 w-full">
                <div class="font-semibold text-xl">Applica per un mutuo!</div>
                <div class="card flex flex-col gap-4">
                    <label for="mutui">Mutui</label>
                    <p-select #mutui [(ngModel)]="chosenMortgage" [options]="mortgagesOptions" optionLabel="name" optionValue="id"
                    (onClick)="changeMaxSliderValue()"/>
                </div>
                <div class="card flex flex-col gap-4">
                    <label for="tasso">Tasso</label>
                    <p-select #tasso [(ngModel)]="interestType" [options]="interestTypes" optionLabel="name" optionValue="type" placeholder="Select" />
                </div>

                <div class="card flex flex-col gap-4">
                    <div class="font-semibold text-xl">Ammontare da richiedere (max {{maxValue | currency: 'EUR'}})</div>
                    <input pInputText [(ngModel)]="sliderValue" type="number" />
                    <p-slider [min]="0" [max]="maxValue" [(ngModel)]="sliderValue" />
                </div>
                <p-button label="RICHIEDI MUTUO" styleClass="w-full" routerLink="/" (onClick)="applyForMortgage()"></p-button>
            </div>
        </div>
    </p-fluid>`
})
export class MortgagesFormPage implements OnInit {
    mortgages: Mortgage[] = [];

    mortgagesOptions: any[] = [];

    chosenMortgage: any = null;

    interestType: any = null;

    sliderValue: number | undefined = 0;

    maxValue: number | undefined = 0;

    interestTypes = [
        {
            name: 'FISSO',
            type: 'FIXED'
        },
        {
            name: 'VARIABILE',
            type: 'VARIABLE'
        }
    ];

    constructor(private mortgageService: MortgageService, private router: Router) {}

    ngOnInit() {
        this.mortgageService.getList().subscribe((value) => {
            this.mortgages = value;
            this.mortgagesOptions = this.mortgages.map((mortgage) => {
                return {
                    name: mortgage.name,
                    id: mortgage.id,
                    maxAmount: mortgage.maxAmount
                };
            });
        });
    }

    changeMaxSliderValue() {
        this.maxValue = this.mortgages.find(value => value.id === this.chosenMortgage)?.maxAmount
        this.sliderValue = this.maxValue;
    }

    applyForMortgage() {
        const applRequest : MortgageApplicationRequest = {
            appliedAmount: this.sliderValue,
            interestType: this.interestType,
            mortgageStatus: "ON_EVALUATION",
            mortgageId: this.chosenMortgage,
        }
        this.mortgageService.applyForMortgage(applRequest).subscribe({
            next: (result) => {
                this.router.navigate(['/dashboard']);
            }
        })
    }
}
