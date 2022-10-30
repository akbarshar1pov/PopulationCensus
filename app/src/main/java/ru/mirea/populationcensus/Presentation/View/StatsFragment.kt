package ru.mirea.populationcensus.Presentation.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.mirea.populationcensus.R

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var cityList: List<String> = mutableListOf<String>()
        var ageList: List<Int> = mutableListOf<Int>()
        var familyList: List<String> = mutableListOf<String>()
        var sexList: List<String> = mutableListOf<String>()

        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val countText = view.findViewById<TextView>(R.id.count)
        val countAge = view.findViewById<TextView>(R.id.count2)
        val countCity = view.findViewById<TextView>(R.id.count3)
        val countFamily = view.findViewById<TextView>(R.id.count4)
        val countSexM = view.findViewById<TextView>(R.id.count5)
        val countSexW = view.findViewById<TextView>(R.id.count6)

        val exitStats = view.findViewById<ImageView>(R.id.exitStats)

        exitStats.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment2_to_bottomNavigation)
        }

        val database = Firebase.database.reference

        database.child("person")
            .get().addOnSuccessListener{ it ->
                countText.text = it.childrenCount.toString()
                for (i in it.children){
                    val cityData = i.child("city")
                    if(cityData.value != null) {
                        cityList = cityList + cityData.value.toString()
                    }
                    val ageData = i.child("age")
                    if(ageData.value != null) {
                        ageList = ageList + ageData.value.toString().toInt()
                    }
                    val familyData = i.child("family")
                    if(familyData.value != null) {
                        familyList = familyList + familyData.value.toString()
                    }
                    val sexData = i.child("sex")
                    if(sexData.value != null) {
                        sexList = sexList + sexData.value.toString()
                    }
                }
                countSexM.text = sexList.filter { it == "М" }.size.toString()
                countSexW.text = sexList.filter { it == "Ж" }.size.toString()
                countFamily.text = familyList.filter { it -> it == "Разведен" }.size.toString()
                countCity.text = cityList.distinct().size.toString()
                countAge.text = ageList.filter { it >= 65 }.size.toString()
            }

        return view
    }
}