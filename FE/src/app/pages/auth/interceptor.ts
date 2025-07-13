import { HttpInterceptorFn } from '@angular/common/http';


export const authInterceptor : HttpInterceptorFn = (req, next) => {

    if (req.url.includes("login")) {
        return next(req);
    }
    const token = localStorage.getItem("tokenKey");
    if (token) {
        const cloned = req.clone({
            setHeaders: { Authorization: `Bearer ${token}` },
        });
        return next(cloned);
    }
    return next(req);
}
