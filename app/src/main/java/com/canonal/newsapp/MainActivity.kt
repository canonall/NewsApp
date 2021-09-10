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
    private var detailFragment = DetailFragment()
    private var typeOneFragment = TypeOneFragment()
    private var typeOneFragment2 = TypeOneFragment()
    private var typeOneFragment3 = TypeOneFragment()
    private var typeTwoFragment = TypeTwoFragment()
    private var typeTwoFragment2 = TypeTwoFragment()
    private var typeTwoFragment3 = TypeTwoFragment()
    private var typeThreeFragment = TypeThreeFragment()
    private var typeThreeFragment2 = TypeThreeFragment()
    private var typeThreeFragment3 = TypeThreeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvDummyText = resources.getString(R.string.headline)

        val newsData1 = NewsData(
            resources.getString(R.string.type_one_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_one_image, null),
            resources.getString(R.string.date1)
        )
         typeOneFragment = TypeOneFragment.newInstance(newsData1)
         typeOneFragment2= TypeOneFragment.newInstance(newsData1)
         typeOneFragment3= TypeOneFragment.newInstance(newsData1)

        val newsData2 = NewsData(
            resources.getString(R.string.type_two_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_two_image, null),
            resources.getString(R.string.date2)

        )
         typeTwoFragment = TypeTwoFragment.newInstance(newsData2)
         typeTwoFragment2 = TypeTwoFragment.newInstance(newsData2)
         typeTwoFragment3 = TypeTwoFragment.newInstance(newsData2)

        val newsData3 = NewsData(
            resources.getString(R.string.type_three_title),
            resources.getString(R.string.lorem),
            ResourcesCompat.getDrawable(resources, R.drawable.type_three_image, null),
            resources.getString(R.string.date3)
        )
         typeThreeFragment = TypeThreeFragment.newInstance(newsData3)
         typeThreeFragment2 = TypeThreeFragment.newInstance(newsData3)
         typeThreeFragment3 = TypeThreeFragment.newInstance(newsData3)

        createFragments(
            typeOneFragment,
            typeTwoFragment,
            typeThreeFragment,
            typeOneFragment2,
            typeTwoFragment2,
            typeThreeFragment2,
            typeOneFragment3,
            typeTwoFragment3,
            typeThreeFragment3,
        )

        //When clicked a fragment remove others and open detail fragment
        binding.frmTypeOneFragmentContainer.setOnClickListener {
            handleClick(typeOneFragment)

        }
        binding.frmTypeTwoFragmentContainer.setOnClickListener {
            handleClick(typeTwoFragment)
        }
        binding.frmTypeThreeFragmentContainer.setOnClickListener {
           handleClick(typeThreeFragment)
        }
        binding.frmTypeOneFragmentContainer2.setOnClickListener {
           handleClick(typeOneFragment2)
        }
        binding.frmTypeTwoFragmentContainer2.setOnClickListener {
            handleClick(typeTwoFragment2)
        }
        binding.frmTypeThreeFragmentContainer2.setOnClickListener {
            handleClick(typeThreeFragment2)
        }
        binding.frmTypeOneFragmentContainer3.setOnClickListener {
            handleClick(typeOneFragment3)
        }
        binding.frmTypeTwoFragmentContainer3.setOnClickListener {
            handleClick(typeTwoFragment3)
        }
        binding.frmTypeThreeFragmentContainer3.setOnClickListener {
            handleClick(typeThreeFragment3)
        }

    }

    private fun handleClick(clickedFragment: Fragment) {
        removeFragments(
            typeOneFragment,
            typeTwoFragment,
            typeThreeFragment,
            typeOneFragment2,
            typeTwoFragment2,
            typeThreeFragment2,
            typeOneFragment3,
            typeTwoFragment3,
            typeThreeFragment3
        )
        disableClick()
        bringDetailFragment(clickedFragment)

    }

    private fun createFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment,
        typeOneFragment2: TypeOneFragment,
        typeTwoFragment2: TypeTwoFragment,
        typeThreeFragment2: TypeThreeFragment,
        typeOneFragment3: TypeOneFragment,
        typeTwoFragment3: TypeTwoFragment,
        typeThreeFragment3: TypeThreeFragment
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer.id, typeOneFragment)
        fragmentTransaction.add(binding.frmTypeTwoFragmentContainer.id, typeTwoFragment)
        fragmentTransaction.add(binding.frmTypeThreeFragmentContainer.id, typeThreeFragment)
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer2.id, typeOneFragment2)
        fragmentTransaction.add(binding.frmTypeTwoFragmentContainer2.id, typeTwoFragment2)
        fragmentTransaction.add(binding.frmTypeThreeFragmentContainer2.id, typeThreeFragment2)
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer3.id, typeOneFragment3)
        fragmentTransaction.add(binding.frmTypeTwoFragmentContainer3.id, typeTwoFragment3)
        fragmentTransaction.add(binding.frmTypeThreeFragmentContainer3.id, typeThreeFragment3)
        fragmentTransaction.commit()
    }

    private fun removeFragments(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment,
        typeOneFragment2: TypeOneFragment,
        typeTwoFragment2: TypeTwoFragment,
        typeThreeFragment2: TypeThreeFragment,
        typeOneFragment3: TypeOneFragment,
        typeTwoFragment3: TypeTwoFragment,
        typeThreeFragment3: TypeThreeFragment
    ) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(typeOneFragment)
        fragmentTransaction.remove(typeTwoFragment)
        fragmentTransaction.remove(typeThreeFragment)
        fragmentTransaction.remove(typeOneFragment2)
        fragmentTransaction.remove(typeTwoFragment2)
        fragmentTransaction.remove(typeThreeFragment2)
        fragmentTransaction.remove(typeOneFragment3)
        fragmentTransaction.remove(typeTwoFragment3)
        fragmentTransaction.remove(typeThreeFragment3)
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
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.slide_in)
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