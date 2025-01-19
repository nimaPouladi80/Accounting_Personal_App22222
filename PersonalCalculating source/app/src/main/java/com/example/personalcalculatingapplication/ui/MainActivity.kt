package com.example.personalcalculatingapplication.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.personalcalculatingapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVars()
    }

    private fun initVars() {

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.bottomNavigationHomeMenuId -> {
                    nonSwipeableViewPager.currentItem = 0
                    true
                }
                R.id.bottomNavigationReportMenuId -> {
                    nonSwipeableViewPager.currentItem = 1
                    true
                }
                R.id.bottomNavigationProfileMenuId -> {
                    nonSwipeableViewPager.currentItem = 2
                    true
                }
                else -> false
            }

        }
        bottomNavigationView.selectedItemId = R.id.bottomNavigationHomeMenuId

        val viewPagerAdapter = MyPagerAdapter(supportFragmentManager)
        nonSwipeableViewPager.offscreenPageLimit = 3
        nonSwipeableViewPager.adapter = viewPagerAdapter
        nonSwipeableViewPager.currentItem = 0
    }

    class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
        fragmentManager,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                2 -> ProfileFragment.newInstance()
                1 -> ReportFragment.newInstance()
                0 -> HomeFragment.newInstance()
                else -> HomeFragment.newInstance()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return ""
        }

    }

}