import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { HttpClientModule } from '@angular/common/http';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

import { AccountProvider, ApiProvider, ProjectProvider, TaskProvider } from './providers';
import { FormsModule } from '@angular/forms';
import { Utils } from './utilities';

@NgModule({
    declarations: [
        AppComponent,
        LayoutComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule
    ],
    providers: [
        Utils,
        AccountProvider,
        ApiProvider, 
        ProjectProvider,
        TaskProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule { 
    public Editor = ClassicEditor;
}
