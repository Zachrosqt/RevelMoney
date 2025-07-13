import { AfterViewInit, Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ChartModule, UIChart } from 'primeng/chart';
import { debounceTime, Subscription } from 'rxjs';
import { LayoutService } from '../../../layout/service/layout.service';
import { Transaction } from '../../service/account.service';

@Component({
    standalone: true,
    selector: 'app-income-outcome-widget',
    imports: [ChartModule],
    template: ` <div class="card !mb-8">
        <div class="font-semibold header-color text-xl mb-4">Uscite - Entrate</div>
        <p-chart #incomeOutcomeChart type="doughnut" [data]="pieData" [options]="pieOptions"></p-chart>
    </div>`
})
export class IncomeOutcomeWidget implements OnInit, OnDestroy, AfterViewInit {
    pieData: any;

    pieOptions: any;

    subscription!: Subscription;

    @Input() transactions: Transaction[] | undefined;

    @ViewChild('incomeOutcomeChart') chartComponent: UIChart | undefined;

    ngAfterViewInit() {
        setTimeout(() => {
            this.initChart()
        }, 100);
    }

    constructor(public layoutService: LayoutService) {
        this.subscription = this.layoutService.configUpdate$.pipe(debounceTime(1000)).subscribe(() => {
            this.initChart();
        });
    }

    ngOnInit() {
        this.initChart();
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    initChart() {
        const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');

        this.pieData = {
            labels: ['IN', 'OUT'],
            datasets: [
                {
                    data: [
                        this.computeTransactionsValue(
                            this.transactions?.filter((transaction) => {
                                return 'IN' === transaction.type;
                            })
                        ),
                        this.computeTransactionsValue(
                            this.transactions?.filter((transaction) => {
                                return 'OUT' === transaction.type;
                            })
                        )
                    ],
                    backgroundColor: [documentStyle.getPropertyValue('--p-emerald-500'), documentStyle.getPropertyValue('--p-pink-500')],
                    hoverBackgroundColor: [documentStyle.getPropertyValue('--p-emerald-400'), documentStyle.getPropertyValue('--p-purple-400')]
                }
            ]
        };

        this.pieOptions = {
            plugins: {
                legend: {
                    labels: {
                        usePointStyle: true,
                        color: textColor
                    }
                }
            }
        };
    }

    computeTransactionsValue(filteredTransactions: Transaction[] | undefined): number {
        return filteredTransactions ? filteredTransactions.reduce((sum, currentTransaction) => sum + (currentTransaction.amount ? currentTransaction.amount : 0), 0) : 0.0;
    }
}
