import { Routes } from '@angular/router';
import { Login } from './login';
import { Error } from './error';

export default [
    { path: 'error', component: Error },
    { path: 'login', component: Login }
] as Routes;
