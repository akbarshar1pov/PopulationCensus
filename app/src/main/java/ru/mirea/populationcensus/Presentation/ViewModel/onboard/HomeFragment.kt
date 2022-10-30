package ru.mirea.populationcensus.Presentation.ViewModel.onboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.mirea.populationcensus.R

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val login = sharedPref?.getString("login", "Пользователь")

        val service = view.findViewById<ImageView>(R.id.image_service1)
        val service2 = view.findViewById<ImageView>(R.id.image_service2)

        service.setOnClickListener {
            if (login != "Пользователь"){
                findNavController().navigate(R.id.action_bottomNavigation_to_createForm2)
            }else{
                Toast.makeText(context, "Вы не вошли в аккаунт!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        service2.setOnClickListener {
            if (login != "Пользователь"){
                findNavController().navigate(R.id.action_bottomNavigation_to_statsFragment2)
            }else{
                Toast.makeText(context, "Вы не вошли в аккаунт!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }
}