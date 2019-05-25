import { Injectable } from '@angular/core';

/**
 * @author
 */
@Injectable()
export class Token {
    public static id : any = 0;

    public static setToken(id: any) {
        this.id = id;
    }

    public static getToken() {
        return this.id;
    }
}