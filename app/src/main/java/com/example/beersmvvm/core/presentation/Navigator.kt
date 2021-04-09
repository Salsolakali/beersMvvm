package com.example.beersmvvm.core.presentation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Bundle
import com.example.beersmvvm.features.detail.ui.DetailActivity
import javax.inject.Inject

class Navigator
@Inject constructor() {

    fun navigateToDetail(context: Context, detailBundle: Bundle, activity: Activity) {
        context.startActivity(
            DetailActivity.buildIntent(context, detailBundle),
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
        )
    }


}