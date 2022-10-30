package ru.mirea.populationcensus

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.mindrot.jbcrypt.BCrypt

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val buttReg = view.findViewById<TextView>(R.id.logReg)
        buttReg.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regFragment)
        }

        val logUser = view.findViewById<EditText>(R.id.logUser)
        val logPass = view.findViewById<EditText>(R.id.logPass)
        val buttLogin = view.findViewById<Button>(R.id.buttLogin)

        buttLogin.setOnClickListener {
            val database = Firebase.database.reference

            database.child("users").child(logUser.text.toString()).child("password").get().addOnSuccessListener {
                if (it.value != null) {
                    if (BCrypt.checkpw(logPass.text.toString(), it.value.toString())){
                        val sharedPref = activity?.getPreferences(MODE_PRIVATE)
                        if (sharedPref != null) {
                            with (sharedPref.edit()) {
                                putString("login", logUser.text.toString())
                                apply()
                            }
                            Toast.makeText(context, "Успешно!", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)

                        }

                    }else{
                        Toast.makeText(context, "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }else{
                    Toast.makeText(context, "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                        .show()
                }
            }.addOnFailureListener{
                Toast.makeText(context, "Ошибка подключения к БД!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }

}