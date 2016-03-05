package id.derohimat.baseapp.ui.adapter.viewholder;

import android.os.Bundle;
import android.view.View;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseHeaderViewHolder extends BaseItemViewHolder {
    protected Bundle bundle;

    public BaseHeaderViewHolder(View itemView, Bundle bundle) {
        super(itemView, null, null);
        this.bundle = bundle;
    }

    @Override
    public void bind(Object o) {

    }

    public abstract void show();

    public void saveState(Bundle bundle) {
        this.bundle = bundle;
    }
}
