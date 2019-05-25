import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class ProjectProvider {

    constructor(private api: ApiProvider) { }

    public getApiUrl() {
        return this.api.apiUrl;
    }

    /**
     * Search by
     * @param info
     */
    public searchProject(info: any) {
        return this.api.post('project/search', info);
    }
}