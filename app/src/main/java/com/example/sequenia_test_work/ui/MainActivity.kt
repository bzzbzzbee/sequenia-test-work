package com.example.sequenia_test_work.ui

import android.os.Bundle
import com.example.sequenia_test_work.databinding.ActivityMainBinding
import com.example.sequenia_test_work.mvp.contracts.MainView
import com.example.sequenia_test_work.mvp.presenters.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : MainView, MvpAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}