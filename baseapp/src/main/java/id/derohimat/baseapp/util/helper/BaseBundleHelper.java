package id.derohimat.baseapp.util.helper;

import android.content.Intent;
import android.os.Bundle;

import id.derohimat.baseapp.ui.adapter.BaseListAdapter;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BaseBundleHelper {

    public static Intent createEmptyIntentWithBundle(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        return intent;
    }

    public static Bundle createPageBundle(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListAdapter.PAGE, page);
        return bundle;
    }
}
