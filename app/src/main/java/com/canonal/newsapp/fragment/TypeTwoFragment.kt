package com.canonal.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.canonal.newsapp.R
import com.canonal.newsapp.databinding.FragmentTypeTwoBinding
import com.canonal.newsapp.model.NewsData

private const val NEWS_DATA = "newsData"


class TypeTwoFragment : Fragment() {
    var newsData: NewsData? = null
    private lateinit var binding: FragmentTypeTwoBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_type_two, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.varNewsData = newsData
    }

    companion object {
        @JvmStatic
        fun newInstance(newsData: NewsData) =
            TypeTwoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(NEWS_DATA, newsData)
                }
            }
    }
}