import { Component, Input, numberAttribute, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    standalone: true,
    selector: 'app-bank-account-widget',
    imports: [CommonModule, FormsModule],

    template: `
        <div class="col-span-12 lg:col-span-6 xl:col-span-6">
            <div class="card mb-0">
                <div class="flex justify-between mb-4">
                    <div>
                        <span class="block font-medium mb-4 header-color">IBAN</span>
                        <div class="text-surface-900 dark:text-surface-0 font-medium text-xl">{{ iban }}</div>
                    </div>
                    <div class="flex items-center justify-center bg-blue-100 dark:bg-blue-400/10 rounded-border"
                         style="width: 2.5rem; height: 2.5rem">
                        <i class="pi pi-wallet text-blue-500 !text-xl"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-span-12 lg:col-span-6 xl:col-span-6">
            <div class="card mb-0">
                <div class="flex justify-between mb-4">
                    <div>
                        <span class="block header-color font-medium mb-4">Saldo</span>
                        <div class="text-surface-900 dark:text-surface-0 font-medium text-xl">{{ balance | currency: this.currency}}</div>
                    </div>
                    <div class="flex items-center justify-center bg-orange-100 dark:bg-orange-400/10 rounded-border"
                         style="width: 2.5rem; height: 2.5rem">
                        <i class="pi pi-dollar text-orange-500 !text-xl"></i>
                    </div>
                </div>
            </div>
        </div>
    `
})
export class BankAccountWidget {
    @Input() iban: any = '';

    @Input() currency: any = 'EUR';

    @Input({ transform: numberAttribute }) balance: number = 0.0;
}
