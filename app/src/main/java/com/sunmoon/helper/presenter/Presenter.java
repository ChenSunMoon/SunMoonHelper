package com.sunmoon.helper.presenter;

import android.os.Bundle;

import com.hwangjr.rxbus.RxBus;
import com.sunmoon.helper.view.View;

/**
 * Created by lgp on 2015/9/4.
 */
public abstract class Presenter {
   public void onCreate (Bundle savedInstanceState){

   }

    void onResume(){

    }

    void onStart(){

    }

    void onPause(){

    }

    void onStop(){

    }

    public void onDestroy(){

    }
   void  setView(View view){

   }
}
