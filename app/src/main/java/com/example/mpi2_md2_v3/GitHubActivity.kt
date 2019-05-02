package com.example.mpi2_md2_v3

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_git_hub.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


class GitHubActivity : AppCompatActivity() {
    var list: MutableList<Repository> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub)
        linearLayoutManager = LinearLayoutManager(this)
        recView.layoutManager = linearLayoutManager
        setSupportActionBar(toolbar)

        val service = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubService::class.java)

        service.retrieveRepositories("andristolmanis")
            .enqueue(object : Callback<List<Repository>> {
                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    response.body()?.forEach {
                        println(it)
                        list.add(it)
                    }
                    adapter = ListAdapter(list)
                    recView.adapter = adapter
                }
                override fun onFailure(call: Call<List<Repository>>, t: Throwable) = t.printStackTrace()
            })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_map -> {
                val intent = Intent(this, MapsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

                true
            }
            R.id.action_github -> {
                Toast.makeText(this, "Already here.", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    interface GitHubService {
        @GET("/users/{user}/repos")
        fun retrieveRepositories(@Path("user") user: String): Call<List<Repository>>
    }

}

data class Repository(val name: String, val owner: Ow)

data class Ow(val login: String)







