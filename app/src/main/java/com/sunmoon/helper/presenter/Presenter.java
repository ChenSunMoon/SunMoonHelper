package com.sunmoon.helper.presenter;
import com.sunmoon.helper.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lgp on 2015/9/4.
 */
public abstract class Presenter {

    public void onDestroy(){

    }
   void  setView(View view){

   }
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void unsubcrible() {

        // TODO: 16/8/17 find when unsubcrible
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
