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

    /**
     * Get by
     * @param info
     */
    public getTaskDetail(info: any) {
        return this.api.post('task/get-task-detail', info);
    }

    public createTask(info: any) {
        return this.api.post('task/create', info);
    }

    public upload(info: any) {
        return this.api.post('task/upload', info);
    }
    
}