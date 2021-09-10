package com.canonal.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canonal.newsapp.R
import com.canonal.newsapp.databinding.FragmentDetailBinding
import com.canonal.newsapp.model.NewsData

private const val NEWS_DATA = "param1"


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var newsData: NewsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsData = it.getSerializable(NEWS_DATA) as NewsData?

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.varNewsDataDetail = newsData
    }

    @BindingAdapter("android:src")
    fun setImageSource(view: View, newsData: NewsData) {
        view.background= newsData.image
    }

    companion object {
        @JvmStatic
        fun newInstance(newsData: NewsData?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(NEWS_DATA, newsData)

                }
            }
    }
}