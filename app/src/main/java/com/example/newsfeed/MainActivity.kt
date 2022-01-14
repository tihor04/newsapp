package com.example.newsfeed

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import com.example.newsfeed.newslistadapter
import com.android.volley.AuthFailureError




class MainActivity : AppCompatActivity(), Newsitemclicked {
    private  lateinit var madapter: newslistadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylcerview.layoutManager= LinearLayoutManager(this)
         fetchdata()
        madapter= newslistadapter(this)
        recylcerview.adapter=madapter

    }

    private fun fetchdata(){
        val url= "https://saurav.tech/NewsAPI/everything/cnn.json"
      val jsonobjectreq=JsonObjectRequest(
          Request.Method.GET,
          url,
            null,
          {
             val newsjsonarray=it.getJSONArray("articles")
              val newsarray=ArrayList<News>()
              for(i in 0 until newsjsonarray.length())
              {
                  val newsjsonobject=newsjsonarray.getJSONObject(i)
                  val currnews=News(
                      newsjsonobject.getString("title"),
                      newsjsonobject.getString("author"),
                      newsjsonobject.getString("url"),
                      newsjsonobject.getString("urlToImage")
                  )
                  newsarray.add(currnews)
              }
              madapter.updatenews(newsarray)
         },
          {

          }
      )
        MySingleton.getInstance(this).addToRequestQueue(jsonobjectreq)



    }

    @Throws(AuthFailureError::class)
    fun getHeaders(): Map<String, String>? {
        val headers = HashMap<String, String>()
        headers.put("Content-Type", "application/json");
        headers["key"] = "Value"
        return headers
    }

    override fun onItemClickec(item: News) {

       val url=item.url
       val builder =  CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}