package ru.mirea.populationcensus.Presentation.ViewModel.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import ru.mirea.populationcensus.Domain.Model.Person
import ru.mirea.populationcensus.Presentation.Repository.CustomRecyclerAdapter
import ru.mirea.populationcensus.R
import java.util.*
import kotlin.collections.ArrayList

class ListOfForm : Fragment() {
private lateinit var dbref : DatabaseReference
private lateinit var recyclerView : RecyclerView
private lateinit var newArrayList : ArrayList<Person>
private lateinit var personArrayList : ArrayList<Person>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_form, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                newArrayList.clear()
                val searchText = p0?.toLowerCase(Locale.getDefault())
                if (searchText!!.isNotEmpty()){
                    personArrayList.forEach {
                        if (it.city!!.toLowerCase(Locale.getDefault()).contains(searchText)){
                            newArrayList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                else{
                    newArrayList.clear()
                    newArrayList.addAll(personArrayList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                newArrayList.clear()
                val searchText = p0?.toLowerCase(Locale.getDefault())
                if (searchText!!.isNotEmpty()){
                    personArrayList.forEach {
                        if (it.city!!.toLowerCase(Locale.getDefault()).contains(searchText)){
                            newArrayList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                else{
                    newArrayList.clear()
                    newArrayList.addAll(personArrayList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        personArrayList = arrayListOf()
        newArrayList = arrayListOf()
        getUserData()

        return view
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("person")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val person = userSnapshot.getValue(Person::class.java)
                        personArrayList.add(person!!)
                    }
                    newArrayList.addAll(personArrayList)
                    recyclerView.adapter = CustomRecyclerAdapter(newArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Ошибка подключения к базе данных!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}