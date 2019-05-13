import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class AccountProvider {
    constructor(private api: ApiProvider) { }

    public getApiUrl() {
        return this.api.apiUrl;
    }
    /**
     * Create by
     * @param info
     */
    public create(info: any) {
        return this.api.post('account/create', info);
    }

    /**
     * Search by
     * @param info
     */
    public search(info: any) {
        return this.api.post('account/search', info);
    }

}