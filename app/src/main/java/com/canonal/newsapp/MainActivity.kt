package com.canonal.newsapp

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
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
    private var detailFragment = DetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvDummyText = "Latest NBA Headlines"

        val newsData1 = NewsData(
            resources.getString(R.string.type_one_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_one_image, null)
        )
        val typeOneFragment = TypeOneFragment.newInstance(newsData1)
        val typeOneFragment2 = TypeOneFragment.newInstance(newsData1)

        val newsData2 = NewsData(
            resources.getString(R.string.type_two_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_two_image, null)
        )
        val typeTwoFragment = TypeTwoFragment.newInstance(newsData2)

        val newsData3 = NewsData(
            resources.getString(R.string.type_three_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_three_image, null)
        )
        val typeThreeFragment = TypeThreeFragment.newInstance(newsData3)

        createFragments(typeOneFragment, typeTwoFragment, typeThreeFragment,typeOneFragment2)

        //When clicked a fragment remove others and open detail fragment
        binding.frmTypeOneFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment,typeOneFragment2)
            disableClick()
            bringDetailFragment(typeOneFragment)
        }
        binding.frmTypeTwoFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment,typeOneFragment2)
            disableClick()
            bringDetailFragment(typeTwoFragment)
        }
        binding.frmTypeThreeFragmentContainer.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment,typeOneFragment2)
            disableClick()
            bringDetailFragment(typeThreeFragment)
        }
        binding.frmTypeOneFragmentContainer2.setOnClickListener {
            removeFragments(typeOneFragment, typeTwoFragment, typeThreeFragment,typeOneFragment2)
            disableClick()
            bringDetailFragment(typeOneFragment2)
        }

    }

    private fun createFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment,
        typeOneFragment2: TypeOneFragment
        ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer.id, typeOneFragment)
        fragmentTransaction.add(binding.frmTypeTwoFragmentContainer.id, typeTwoFragment)
        fragmentTransaction.add(binding.frmTypeThreeFragmentContainer.id, typeThreeFragment)
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer2.id,typeOneFragment2)
        fragmentTransaction.commit()
    }

    private fun removeFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment,
        typeOneFragment2: TypeOneFragment,
        ) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(typeOneFragment)
        fragmentTransaction.remove(typeTwoFragment)
        fragmentTransaction.remove(typeThreeFragment)
        fragmentTransaction.remove(typeOneFragment2)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun bringDetailFragment(clickedFragment: Fragment) {
        Log.e("FRAGMENT", "Clicked $clickedFragment")

        val fragmentType = when (clickedFragment) {
            is TypeOneFragment -> clickedFragment.newsData
            is TypeTwoFragment -> clickedFragment.newsData
            is TypeThreeFragment -> clickedFragment.newsData
            else -> return
        }

        detailFragment = DetailFragment.newInstance(fragmentType)
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
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(detailFragment)
        fragmentTransaction.commit()
    }
}