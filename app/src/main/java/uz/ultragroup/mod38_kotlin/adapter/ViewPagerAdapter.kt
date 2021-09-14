package uz.ultragroup.mod38_kotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.ultragroup.mod38_kotlin.FavouriteFragment1
import uz.ultragroup.mod38_kotlin.HomeFragment1

class ViewPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment1()
            else -> FavouriteFragment1()
        }

    }
}