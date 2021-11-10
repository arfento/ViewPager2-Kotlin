package com.ims.viewpager2_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.ims.viewpager2_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Image 1",
                "Description image 1 : lorem ipsum lorem ipsum lorem ipsum",
                R.drawable.ic_launcher_background

            ),
            IntroSlide(
                "Image 2",
                "Description image 2 : lorem ipsum lorem ipsum lorem ipsum",
                R.drawable.ic_launcher_foreground

            ),
            IntroSlide(
                "Image 3",
                "Description image 3 : lorem ipsum lorem ipsum lorem ipsum",
                R.mipmap.ic_launcher
            ),
        )
    )
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.introSliderViewPager.adapter = introSliderAdapter
        setUpIndicators()
        setCurrentIndicators(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })

        binding .btnNext.setOnClickListener {
            if (binding.introSliderViewPager.currentItem + 1  < introSliderAdapter.itemCount){
                binding.introSliderViewPager.currentItem += 1
            }else{
                Intent(applicationContext,AnotherActivity::class.java ).also {
                    startActivity(it)}
            }
        }
        binding.txtSkip.setOnClickListener {
            Intent(this@MainActivity, AnotherActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun setUpIndicators() {
        val indicator = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }

            binding.indicatorContainer.addView(indicator[i])

        }

    }

    private fun setCurrentIndicators(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }
}