package wor.com.wor.PageFragments;

/**
 * Created by Santosh on 14-Jan-17.
 */


        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

        import wor.com.wor.ContentFragments.MTPitStopFragment;
        import wor.com.wor.ContentFragments.MechPitStopFragment;
        import wor.com.wor.ContentFragments.WesternPitStopFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MechPitStopFragment tab1 = new MechPitStopFragment();
                return tab1;
            case 1:
                MTPitStopFragment tab2 = new MTPitStopFragment();
                return tab2;
            case 2:
                WesternPitStopFragment tab3 = new WesternPitStopFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}