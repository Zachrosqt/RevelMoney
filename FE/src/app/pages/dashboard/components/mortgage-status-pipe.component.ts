import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'mortgageStatusPipe',
    standalone: true
})
export class MortgageStatusPipe implements PipeTransform {
    transform(status: string): { severity: string, label: string } {
        switch (status) {
            case 'ACCEPTED':
                return { severity: 'success', label: 'Accettato' };
            case 'ON_EVALUATION':
                return { severity: 'warn', label: 'In valutazione' };
            case 'REJECTED':
                return { severity: 'danger', label: 'Rifiutato' };
            default:
                return { severity: 'info', label: 'Sconosciuto' };
        }
    }
}
