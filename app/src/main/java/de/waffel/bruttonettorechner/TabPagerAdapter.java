package de.waffel.bruttonettorechner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentStateAdapter {

    List<Fragment> fragments = new ArrayList<>();

    public TabPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

        fragments.add(new BruttoNettoFragment());
        fragments.add(new ReduceFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position >= fragments.size()) {
            throw new IndexOutOfBoundsException("No tab with position " + position + "found.");
        }

        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
