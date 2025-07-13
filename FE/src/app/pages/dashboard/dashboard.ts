import { Component, OnInit } from '@angular/core';
import { BankAccountWidget } from './components/bank-account-widget.component';
import { TransactionsWidget } from './components/transactions-widget';
import { IncomeOutcomeWidget } from './components/income-outcome-widget.component';
import { Account, AccountService } from '../service/account.service';
import { TransactionService } from '../service/transaction.service';
import { AuthService } from '../service/auth.service';
import { UserMortgageWidget } from './components/user-mortgage-widget.component';

@Component({
    selector: 'app-dashboard',
    imports: [BankAccountWidget, TransactionsWidget, IncomeOutcomeWidget, BankAccountWidget, BankAccountWidget, UserMortgageWidget],
    template: `
        <div class="grid grid-cols-12 gap-8">
            <app-bank-account-widget class="contents" [iban]="bankAccount.iban" [balance]="bankAccount.balance" [currency]="bankAccount.currency" />
            <div class="col-span-12 xl:col-span-6">
                <app-transactions-widget [transactions]="bankAccount.transactions" />
            </div>
            <div class="col-span-12 xl:col-span-6">
                <app-user-mortgage-widget [mortgageApplications]="bankAccount.mortgageApplications" />
            </div>
            <div class="self-center col-span-10 xl:col-span-6">
                <app-income-outcome-widget [transactions]="bankAccount.transactions" />
            </div>
        </div>
    `,
    providers: [AccountService, TransactionService]
})
export class Dashboard implements OnInit {
    bankAccount: Account = { iban: '', balance: 0.0, currency: 'EUR', transactions: [], mortgageApplications: [] };

    constructor(
        private accountService: AccountService,
        private authService: AuthService
    ) {}

    ngOnInit(): void {
        this.accountService.getInfo(this.authService.getToken()).subscribe((info) => {
            this.bankAccount = info;
        });
    }
}
