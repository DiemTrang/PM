import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { HttpClientModule } from '@angular/common/http';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { FileSelectDirective } from 'ng2-file-upload';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

import { AccountProvider, ApiProvider, ProjectProvider, TaskProvider , FileProvider} from './providers';
import { FormsModule } from '@angular/forms';
import { Utils } from './utilities';

@NgModule({
    declarations: [
        AppComponent,
        LayoutComponent,
        FileSelectDirective
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
        TaskProvider,
        FileProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule { 
    public Editor = ClassicEditor;
}
