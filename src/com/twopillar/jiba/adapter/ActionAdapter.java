
package com.twopillar.jiba.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.twopillar.jiba.fragment.ActionCategoreyFragment;

/**
 * @author GuoJian'an
 * @date 2015-7-14
 *
 */
public class ActionAdapter extends FragmentPagerAdapter
{
    public static String[] TITLES = new String[]
    { "肩部", "胸部", "二头", "三头", "背部", "腹部", "腿部", "有氧" };

    public ActionAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0)
    {
        ActionCategoreyFragment fragment = new ActionCategoreyFragment(arg0);
        return fragment;
    }

    @Override
    public int getCount()
    {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return TITLES[position];
    }

}
