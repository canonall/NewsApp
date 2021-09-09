package com.canonal.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canonal.newsapp.databinding.ActivityMainBinding
import com.canonal.newsapp.fragment.DetailFragment
import com.canonal.newsapp.fragment.TypeOneFragment
import com.canonal.newsapp.fragment.TypeThreeFragment
import com.canonal.newsapp.fragment.TypeTwoFragment
import com.canonal.newsapp.model.NewsData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvDummyText = "Hey"

        val newsData1 = NewsData("Type One","Type One Text", ResourcesCompat.getDrawable(resources,R.drawable.type_one_image,null))
        val typeOneFragment = TypeOneFragment.newInstance(newsData1)

        val newsData2 = NewsData("Type Two","Type Two Text",ResourcesCompat.getDrawable(resources,R.drawable.type_two_image,null))
        val typeTwoFragment = TypeTwoFragment.newInstance(newsData2)

        val newsData3 = NewsData("Type Three","Type Three Text",ResourcesCompat.getDrawable(resources,R.drawable.type_three_image,null))
        val typeThreeFragment = TypeThreeFragment.newInstance(newsData3)

        createFragments(typeOneFragment, typeTwoFragment, typeThreeFragment)

        //When clicked a fragment remove others and open detail fragment
        binding.frmTypeOneFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment)
            disableClick()
            bringDetailFragment(typeOneFragment)
        }
        binding.frmTypeTwoFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment)
            disableClick()
            bringDetailFragment(typeTwoFragment)
        }
        binding.frmTypeThreeFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment)
            disableClick()
            bringDetailFragment(typeThreeFragment)
        }

    }

    private fun createFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer.id, typeOneFragment)
        fragmentTransaction.add(binding.frmTypeTwoFragmentContainer.id, typeTwoFragment)
        fragmentTransaction.add(binding.frmTypeThreeFragmentContainer.id, typeThreeFragment)
        fragmentTransaction.commit()
    }

    private fun removeFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment
    ) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(typeOneFragment)
        fragmentTransaction.remove(typeTwoFragment)
        fragmentTransaction.remove(typeThreeFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun bringDetailFragment(clickedFragment: Fragment) {
        Log.e("FRAGMENT", "Clicked $clickedFragment")

        val detailFragment = DetailFragment.newInstance(
            when (clickedFragment) {
                is TypeOneFragment -> clickedFragment.newsData
                is TypeTwoFragment -> clickedFragment.newsData
                is TypeThreeFragment -> clickedFragment.newsData
                else -> null
            }!!
        )
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer.id, detailFragment)
        fragmentTransaction.commit()

    }

    private fun disableClick() {
        binding.frmTypeOneFragmentContainer.isEnabled = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.frmTypeOneFragmentContainer.isEnabled = true
    }
}