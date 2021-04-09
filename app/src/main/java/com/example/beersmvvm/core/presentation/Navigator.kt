package com.example.beersmvvm.core.presentation

import android.content.Context
import android.content.Intent
import javax.inject.Inject

class Navigator
@Inject constructor() {

    private fun navigate(context: Context, intent: Intent?) {
        context.startActivity(intent)
    }

    fun navigateToLogin(context: Context) {
        //navigate(context, LoginActivity.buildIntent(context))
    }

    /*fun navigateToDetail(context: Context, detailBundle: DetailBundle) {
        //navigate(context, DetailActivity.buildIntent(context, detailBundle))
    }*/

    fun navigateToHome(context: Context) {
        //navigate(context, HomeDataActivity.buildIntent(context))
    }
}