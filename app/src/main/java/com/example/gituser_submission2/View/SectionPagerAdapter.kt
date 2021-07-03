package com.example.gituser_submission2.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity:AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int{
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? =null
        when(position){
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowerFragment()
        }
        return fragment as Fragment
    }
}