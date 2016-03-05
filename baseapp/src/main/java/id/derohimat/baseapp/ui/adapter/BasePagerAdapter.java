package id.derohimat.baseapp.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import id.derohimat.baseapp.ui.fragment.BaseFragment;
import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BasePagerAdapter<Fragment extends BaseFragment> extends
        FragmentStatePagerAdapter
{
    protected List<Fragment> fragments;
    protected List<String> titles;

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
        Timber.tag(getClass().getSimpleName());
    }

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public abstract Fragment getItem(int position);

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    public List<Fragment> getFragments()
    {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments)
    {
        this.fragments = fragments;
    }

    public List<String> getTitles()
    {
        return titles;
    }

    public void setTitles(List<String> titles)
    {
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles.size() == fragments.size() ? titles.get(position) : super.getPageTitle(position);
    }
}
