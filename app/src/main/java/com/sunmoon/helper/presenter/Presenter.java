package com.sunmoon.helper.presenter;
import android.content.Intent;
import android.os.Bundle;

import com.sunmoon.helper.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lgp on 2015/9/4.
 */
public abstract class Presenter <T extends View> {
    public T v;
    public void  setView(T v){
       this.v = v;
    }
    private CompositeSubscription mCompositeSubscription;
    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    public void unsubscribe() {

        // TODO: 16/8/17 find when unsubscribe
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
    
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        
    }
   
    public  void onDestroy(){

    }
    public  void onResume(){

    }

    public void onCreate(Bundle savedInstanceState) {
    }

    public void onPause() {
    }

    public void onStop() {
    }


    public void onStart() {
    }
}
