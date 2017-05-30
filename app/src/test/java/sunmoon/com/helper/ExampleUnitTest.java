package sunmoon.com.helper;

import android.provider.Settings;
import android.util.Log;
import android.util.LogPrinter;

import com.sunmoon.helper.api.ApiManage;
import com.sunmoon.helper.model.TuLing;
import com.sunmoon.helper.presenter.RemindPresenter;

import org.junit.Test;

import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        RemindPresenter dd =new RemindPresenter();
    }
}