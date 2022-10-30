package ru.mirea.populationcensus.Presentation.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.mirea.populationcensus.*
import ru.mirea.populationcensus.Presentation.ViewModel.onboard.*

class BottomNavigation : Fragment() {
    private var flag = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
        val menu = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        when(flag){
            1 ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.containerFragment, HomeFragment())
                    ?.commit()
                menu.selectedItemId = R.id.homeFragment
            }
            2 ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.containerFragment, ListOfForm())
                    ?.commit()
                menu.selectedItemId = R.id.listOfForm
            }
            3 ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.containerFragment, InfoOfApp())
                    ?.commit()
                menu.selectedItemId = R.id.infoOfApp
            }
            4 ->{
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val login = sharedPref?.getString("login", "Пользователь")
                if (login != "Пользователь"){
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment, UserProfile())
                        ?.commit()
                }else{
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment, Profile())
                        ?.commit()
                }
                menu.selectedItemId = R.id.profileUser
            }
            else ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.containerFragment, HomeFragment())
                    ?.commit()
                menu.selectedItemId = R.id.homeFragment
            }
        }

        menu.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.homeFragment -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment, HomeFragment())
                        ?.commit()
                    flag = 1
                }
                R.id.listOfForm -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment, ListOfForm())
                        ?.commit()
                    flag = 2
                }
                R.id.infoOfApp -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.containerFragment, InfoOfApp())
                        ?.commit()
                    flag = 3
                }
                R.id.profileUser -> {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    val login = sharedPref?.getString("login", "Пользователь")
                    if (login != "Пользователь"){
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.containerFragment, UserProfile())
                            ?.commit()
                    }else{
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.containerFragment, Profile())
                            ?.commit()
                    }
                    flag = 4
                }
            }
            true
        }

        return view
    }


}