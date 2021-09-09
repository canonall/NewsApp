package com.canonal.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.canonal.newsapp.databinding.ActivityMainBinding
import com.canonal.newsapp.fragment.DetailFragment
import com.canonal.newsapp.fragment.TypeOneFragment
import com.canonal.newsapp.fragment.TypeThreeFragment
import com.canonal.newsapp.fragment.TypeTwoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tvDummyText = "Hey"


        val typeOneFragment = TypeOneFragment.newInstance("TypeOne", "Fragment")
        val typeTwoFragment = TypeTwoFragment.newInstance("TypeTwo", "Fragment")
        val typeThreeFragment = TypeThreeFragment.newInstance("TypeThree", "Fragment")

        createFragments(typeOneFragment, typeTwoFragment, typeThreeFragment)

        //When clicked a fragment remove others and open detail fragment
        binding.frmTypeOneFragmentContainer.setOnClickListener {
            onFragmentClicked(typeOneFragment, typeTwoFragment, typeThreeFragment)
        }
        binding.frmTypeTwoFragmentContainer.setOnClickListener {
            onFragmentClicked(typeOneFragment, typeTwoFragment, typeThreeFragment)
        }
        binding.frmTypeThreeFragmentContainer.setOnClickListener {
            onFragmentClicked(typeOneFragment, typeTwoFragment, typeThreeFragment)
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

    private fun onFragmentClicked(
        typeOneFragment: TypeOneFragment,
        typeTwoFragment: TypeTwoFragment,
        typeThreeFragment: TypeThreeFragment
    ) {
        val detailFragment = DetailFragment.newInstance("Detail", "Fragment")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.remove(typeOneFragment)
        fragmentTransaction.remove(typeTwoFragment)
        fragmentTransaction.remove(typeThreeFragment)
        fragmentTransaction.add(binding.frmTypeOneFragmentContainer.id, detailFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        binding.frmTypeOneFragmentContainer.isEnabled = false

        Log.e("FRAGMENT", "Clicked")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.frmTypeOneFragmentContainer.isEnabled = true
    }
}