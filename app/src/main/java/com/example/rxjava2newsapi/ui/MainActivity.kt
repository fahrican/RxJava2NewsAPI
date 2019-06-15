package com.example.rxjava2newsapi.ui

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.rxjava2newsapi.R
import com.example.rxjava2newsapi.adapter.ArticleAdapter
import com.example.rxjava2newsapi.model.Article
import com.example.rxjava2newsapi.model.TopHeadlines
import com.example.rxjava2newsapi.news_api.TopHeadlinesEndpoint
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val ENDPOINT_URL by lazy { "https://newsapi.org/v2/" }
    private lateinit var topHeadlinesEndpoint: TopHeadlinesEndpoint
    private lateinit var newsApiConfig: String
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articleList: ArrayList<Article>
    private lateinit var userKeywordInput: String
    // RxJava related fields
    private lateinit var topHeadlinesObservable: Observable<TopHeadlines>
    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Network request
        val retrofit: Retrofit = generateRetrofitBuilder()
        topHeadlinesEndpoint = retrofit.create(TopHeadlinesEndpoint::class.java)
        newsApiConfig = resources.getString(R.string.api_key)
        swipe_refresh.setOnRefreshListener(this)
        swipe_refresh.setColorSchemeResources(R.color.colorAccent)
        articleList = ArrayList()
        articleAdapter = ArticleAdapter(articleList)
        //When the app is launched of course the user input is empty.
        userKeywordInput = ""
        //CompositeDisposable is needed to avoid memory leaks
        compositeDisposable = CompositeDisposable()
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = articleAdapter
    }

    override fun onStart() {
        super.onStart()
        checkUserKeywordInput()
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    private fun generateRetrofitBuilder(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //Add RxJava2CallAdapterFactory as a Call adapter when building your Retrofit instance
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun checkUserKeywordInput() {
        if (userKeywordInput.isEmpty()) {
            queryTopHeadlines()
        } else {
            getKeyWordQuery(userKeywordInput)
        }
    }

    private fun queryTopHeadlines() {
        swipe_refresh.isRefreshing = true
        topHeadlinesObservable = topHeadlinesEndpoint.getTopHeadlines("us", newsApiConfig)
        getObservableOfArticle()
    }

    private fun getObservableOfArticle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getKeyWordQuery(userKeywordInput: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
