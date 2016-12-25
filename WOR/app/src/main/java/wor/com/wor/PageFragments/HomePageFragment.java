package wor.com.wor.PageFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import wor.com.wor.ContentFragments.PitStop1Frament;
import wor.com.wor.ContentFragments.PitStop2Fragment;
import wor.com.wor.R;
/**
 * Created by Santosh on 12/8/2016.
 */

public class HomePageFragment extends PageMainFragment {  public HomePageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_home_page, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.homeviewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.hometabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                final InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
                final InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new PitStop1Frament(), "PitStop1");
        adapter.addFragment(new PitStop2Fragment(), "PitStop2");
        adapter.addFragment(new PitStop2Fragment(), "PitStop3");
        viewPager.setAdapter(adapter);
    }


}