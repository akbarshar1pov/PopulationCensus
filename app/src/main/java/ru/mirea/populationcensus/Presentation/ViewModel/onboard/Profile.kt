package ru.mirea.populationcensus.Presentation.ViewModel.onboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import ru.mirea.populationcensus.R
import kotlin.system.exitProcess

class Profile : Fragment() {

    fun exit() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.remove("login")?.apply()
        activity?.finish();
        exitProcess(0);
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val buttLogin = view.findViewById<ConstraintLayout>(R.id.loginProfile)
        buttLogin.setOnClickListener {
            findNavController().navigate(R.id.action_bottomNavigation_to_loginFragment)
        }
        val buttReg = view.findViewById<ConstraintLayout>(R.id.regProfile)
        buttReg.setOnClickListener {
            findNavController().navigate(R.id.action_bottomNavigation_to_regFragment)
        }

        val exitProfile = view.findViewById<ConstraintLayout>(R.id.exitProfile)
        exitProfile.setOnClickListener {
            exit()
        }

        return view
    }

}