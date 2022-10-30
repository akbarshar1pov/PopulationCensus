package ru.mirea.populationcensus.Presentation.ViewModel.onboard

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ru.mirea.populationcensus.R

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val welcomeUser = view.findViewById<TextView>(R.id.welcomeUser)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var login = sharedPref?.getString("login", "Пользователь")
        if (login != "Пользователь"){
            welcomeUser.text = login
        }else{
            welcomeUser.text = login
        }

        Handler().postDelayed({
            findNavController().navigate(R.id.action_welcomeFragment_to_bottomNavigation)
        }, 3000)

        return view
    }

}