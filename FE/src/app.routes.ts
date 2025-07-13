import { Routes } from '@angular/router';
import { AppLayout } from './app/layout/component/app.layout';
import { Dashboard } from './app/pages/dashboard/dashboard';
import { AuthGuard } from './app/pages/auth/authguard';
import { MortgagesFormPage } from './app/pages/mortgages/mortgage';

export const appRoutes: Routes = [
    {
        path: '',
        component: AppLayout,
        canActivate: [AuthGuard],
        children: [
            { path: 'dashboard', component: Dashboard },
            { path: 'mortgage', component: MortgagesFormPage },
        ]
    },
    { path: 'auth', loadChildren: () => import('./app/pages/auth/auth.routes') }
];
