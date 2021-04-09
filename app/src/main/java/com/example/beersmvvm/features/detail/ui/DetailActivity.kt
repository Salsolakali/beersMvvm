package com.example.beersmvvm.features.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beersmvvm.core.extensions.loadFromUrl
import com.example.beersmvvm.databinding.ActivityDetailBinding
import com.example.beersmvvm.features.home.domain.model.Beer

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val BEER_DETAIL_KEY = "beer_key"
        fun buildIntent(context: Context, detailBundle: Bundle): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtras(detailBundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI(getDetails())
    }

    private fun getDetails() = intent.extras?.getSerializable(BEER_DETAIL_KEY) as Beer?

    private fun initUI(beer: Beer?) {
        binding.imageDetail.loadFromUrl(beer?.image ?: "")
        binding.titleDetail.text = beer?.name
        binding.authorDetail.text = beer?.description
        binding.contentDetail.text = beer?.tagline
    }
}