package uz.ultragroup.mod38_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import uz.ultragroup.mod38_kotlin.adapter.ViewPagerAdapter
import uz.ultragroup.mod38_kotlin.databinding.ActivityMainBinding

class MainActivity1 : AppCompatActivity() {

    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

        setViewState()

    }


    private fun setViewState() {

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.pageList.adapter = viewPagerAdapter
    }

    private fun setListeners() {


        binding.allRadio.setOnClickListener {
            binding.pageList.setCurrentItem(0, false)
        }

        binding.favRadio.setOnClickListener {
            binding.pageList.setCurrentItem(1, false)
        }

        binding.pageList.registerOnPageChangeCallback(pageListener)

    }

    val pageListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> binding.allRadio.performClick()
                else -> binding.favRadio.performClick()
            }
        }
    }

    override fun onDestroy() {
        binding.pageList.unregisterOnPageChangeCallback(pageListener)
        super.onDestroy()
    }

}