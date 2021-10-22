package com.mindorks.example.parallax

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var ivCollapsibleImg: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var searchView:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabs)
        ivCollapsibleImg = findViewById(R.id.ivCollapsibleImg)
        searchView=findViewById(R.id.search_view)
        tabLayout.setupWithViewPager(mPager)
        // toolbar = findViewById(R.id.collapsing_toolbar)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

        mPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        ivCollapsibleImg.setImageDrawable(resources.getDrawable(R.drawable.books))

                    }
                    1 -> {
                        ivCollapsibleImg.setImageDrawable(resources.getDrawable(R.drawable.books2))
                    }
                    2 -> {
                        ivCollapsibleImg.setImageDrawable(resources.getDrawable(R.drawable.books2017))

                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE;
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //if (mPager.currentItem == 0) {
                    val frag1: BooksFragment = (mPager.adapter as ScreenSlidePagerAdapter).instantiateItem(mPager, mPager.getCurrentItem()) as BooksFragment
                    frag1.recyclerAdapter.filter.filter(newText)
                //}
                return false
            }

        })
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
       {
        override fun getCount(): Int = 3
        override fun getItem(position: Int): Fragment = BooksFragment().newInstance()
        override fun getPageTitle(position: Int): CharSequence? {
            var title = ""
            when (position) {
                0 -> {
                   // title = "Tech"
                }
                1 -> {
                   // title = "Novels"
                }
                2 -> {
                    //title = "Motivational"
                }
            }
            return title
        }

    }

}
