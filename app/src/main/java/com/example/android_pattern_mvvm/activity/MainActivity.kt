package com.example.android_pattern_mvvm.activity



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_pattern_mvvm.R
import com.example.android_pattern_mvvm.adapter.PostAdapter
import com.example.android_pattern_mvvm.model.Post
import com.example.android_pattern_mvvm.viewmodel.MainViewModel


//https://github.com/umangburman/MVVM-Retrofit-Kotlin-Example

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))

        viewModel.apiPostList().observe(this, Observer {
            refreshAdapter(it)
        })
    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        recyclerView.setAdapter(adapter)
    }
}