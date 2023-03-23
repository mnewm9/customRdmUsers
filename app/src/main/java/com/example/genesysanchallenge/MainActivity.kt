package com.example.genesysanchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genesysanchallenge.adapter.UsersListAdapter
import com.example.genesysanchallenge.databinding.ActivityMainBinding
import com.example.genesysanchallenge.model.results
import com.example.genesysanchallenge.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import java.util.Collections

class MainActivity : AppCompatActivity() {

    lateinit var  recyclerAdapter: UsersListAdapter
    private lateinit var viewModel : UsersViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        recyclerAdapter = UsersListAdapter(this, viewModel)
        binding.rvMain.adapter = recyclerAdapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        searchView =binding.searchView

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                filterList(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                filterList(p0)
                return true
            }

        })

        initRecyclerView()
        initViewModel()

    }

    private fun filterList(text:String?) {
        val filteredList:  MutableList<results> = mutableListOf<results>()
      viewModel.getLiveDatatObserver().observe(this, Observer {
          for (result in it)
              if (text == "all countries".toLowerCase()){
                  filteredList.add(result)
                  Collections.frequency(it, result.location?.country)
                Toast.makeText(
                    this,
                    "The are ${
                    Collections.frequency(
                        it,
                        result.location?.country
                             )
                         } users from ${result.location?.country}",
                    Toast.LENGTH_LONG
                ).show()
            } else if( result.location?.country?.toLowerCase()!!.contains(text!!.toLowerCase())) {
                filteredList.add(result)
            } else if(text =="ascending ".toLowerCase()) { filteredList.add(result)
                  filteredList.sortBy { it.name?.lastName }
            } else {
                filteredList.add(result)
                filteredList.sortByDescending { it.name?.lastName}
            }


      })
        if (filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show()
        }else{
            recyclerAdapter.setFilterdList(filteredList)
            recyclerAdapter.notifyDataSetChanged()
        }

    }

    private fun initRecyclerView(){
       rvMain.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerAdapter = UsersListAdapter(this,viewModel)
            rvMain.adapter = recyclerAdapter


    }
    private fun initViewModel(){

        viewModel.getLiveDatatObserver().observe(this, Observer {
            if (it!=null){
                recyclerAdapter.setUsersLists(it)
                recyclerAdapter.notifyDataSetChanged()

            }else{
                Toast.makeText(this, "Error in getting the users",Toast.LENGTH_SHORT).show()

            }

        })

        runBlocking {viewModel.run() { makeApiCall() }  }


    }
}