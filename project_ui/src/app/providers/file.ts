import { Injectable } from '@angular/core';
import { ApiProvider } from './api';

@Injectable()
export class FileProvider {
    constructor(private api: ApiProvider) { }

    /**
     * Upload
     * @param info
     * @param data
     */
    public upload(info: File, data: any) {
        let f: FormData = new FormData();
        f.append('file', info);
        f.append('req', data);
        return this.api.upload('file/upload', f);
    }

    /**
     * Download
     * @param info
     */
    public download(info: any) {
        return this.api.download('file/download', info);
    }

    /**
     * Call
     * @param info
     * @param data
     */
    public call(info: File, data: any) {
        let f: FormData = new FormData();
        f.append('file', info);
        f.append('req', data);
        return this.api.upload('file/call', f);
    }

    /**
     * Upload
     * @param info
     * @param data
     */
    public uploadMulti(info: File[], data: any) {
        let f: FormData = new FormData();
        info.forEach(i => {
            f.append('files', i);
        });
        f.append('req', data);
        return this.api.upload('attachment/upload', f);
    }

    /**
     * Delete SO
     * @param info
     */
    public deleteSo(info: any) {
        return this.api.post('attachment/delete-so', info);
    }

    /**
     * Delete FR
     * @param info
     */
    public deleteFr(info: any) {
        return this.api.post('attachment/delete-fr', info);
    }
}