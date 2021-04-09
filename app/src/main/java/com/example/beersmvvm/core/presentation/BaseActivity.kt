package com.example.beersmvvm.core.presentation


import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.beersmvvm.R
import com.example.beersmvvm.core.domain.RequestFailure

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    lateinit var binding: V
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = getViewBinding()
        setLayout(binding)
    }

    private fun setLayout(binding: V) {
        setContentView(binding.root)
    }

    protected abstract fun getViewBinding(): V

    open fun manageError(requestFailure: RequestFailure) {
        val message = when (requestFailure) {
            is RequestFailure.ApiError -> requestFailure.message
            is RequestFailure.NoConnectionError -> getString(R.string.connection_error_message)
            is RequestFailure.UnknownError -> getString(R.string.default_error_message)
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}