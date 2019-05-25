import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class TaskProvider {
    constructor(private api: ApiProvider) { }

    public getApiUrl() {
        return this.api.apiUrl;
    }

    /**
     * Search by
     * @param info
     */
    public searchTask(info: any) {
        return this.api.post('task/search', info);
    }
}