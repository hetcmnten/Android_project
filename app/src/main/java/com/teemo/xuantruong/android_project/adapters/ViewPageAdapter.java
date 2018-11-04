package com.teemo.xuantruong.android_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teemo.xuantruong.android_project.entity.FlagCategorySource;
import com.teemo.xuantruong.android_project.fragments.FragmentCategories;
import com.teemo.xuantruong.android_project.fragments.FragmentListNews;
import com.teemo.xuantruong.android_project.fragments.FragmentSources;

public class ViewPageAdapter extends FragmentPagerAdapter {
    public  FragmentSources fragmentSource = new FragmentSources();
    public  FragmentCategories fragmentCategories = new FragmentCategories();
    public  FragmentListNews fragmentListNews = new FragmentListNews();

    public   String[] FraListTitles = {"TIN HOT", "THỂ LOẠI", "NGUỒN BÁO"};
    private static int flagSource = 0;
    private static int flagCategory = 0;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int flagCurrentSource = FlagCategorySource.flagSource;
        int flagCurentCategory = FlagCategorySource.flaCategory;
        //the first time load data;
        if (flagCurrentSource == 0 && flagCurentCategory == 0) {
            fragmentCategories.listCat = fragmentSource.listSource.get(0).getSource_categories();
            fragmentListNews.listNews = fragmentCategories.listCat.get(0).getListPosters();
        }
        // when change source -> change category
        else if (flagSource != flagCurrentSource) {
            fragmentCategories.listCat = fragmentSource.listSource.get(flagCurrentSource).getSource_categories();
            fragmentListNews.listNews = fragmentCategories.listCat.get(0).getListPosters();
            flagSource = flagCurrentSource;
            flagCategory = flagCurentCategory;
        }
        //when change only category
        else {
            if (flagCategory != flagCurentCategory) {
                flagCategory = flagCurentCategory;
            }
            fragmentCategories.listCat = fragmentSource.listSource.get(flagCurrentSource).getSource_categories();
            fragmentListNews.listNews = fragmentCategories.listCat.get(flagCurentCategory).getListPosters();
        }
        switch (position) {
            case 0:
                return fragmentListNews;
            case 1:
                return fragmentCategories;
            case 2:
                return fragmentSource;
        }
        return null;
    }

    @Override
    public int getCount() {
        return FraListTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FraListTitles[position];
    }

}
