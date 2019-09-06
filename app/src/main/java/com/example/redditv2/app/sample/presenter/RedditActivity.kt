package com.example.redditv2.app.sample.presenter

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditv2.R
import com.example.redditv2.app.sample.domain.ChildrenData
import com.example.redditv2.app.sample.presenter.ui.RecyclerAdapter
import com.example.redditv2.databinding.ActivityRedditBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit.*
import javax.inject.Inject

class RedditActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RedditActivityViewModel
    private lateinit var binding: ActivityRedditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(RedditActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reddit)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.conection.observe(this, Observer {
            if (it == false) {
                AlertDialog.Builder(this)
                    .setMessage(getString(R.string.app_name))
                    .setPositiveButton(R.string.app_name, DialogInterface.OnClickListener { _, _ ->
                        retry()
                    })
            } else {
                val recycler = viewModel.list
                configureList(recycler)
            }
        })
    }

    private fun retry() {
        viewModel.loading()
    }

    private fun configureList(news: List<ChildrenData>) {
        val recyclerView = reclyclerActivity
        recyclerView.adapter = RecyclerAdapter(this, news)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }
}
