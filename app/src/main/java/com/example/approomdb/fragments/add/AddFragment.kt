package com.example.approomdb.fragments.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.approomdb.R
import com.example.approomdb.data.User
import com.example.approomdb.data.UserViewModel
import com.example.approomdb.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var bind: FragmentAddBinding
    private lateinit var mUserModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentAddBinding.inflate(inflater, container, false)
        mUserModel = ViewModelProvider(this).get(UserViewModel::class.java)
        bind.btnSave.setOnClickListener{
            insertDataToDatabase()
        }
        return bind.root //inflater.inflate(R.layout.fragment_add, container, false)
    }

    private fun insertDataToDatabase() {
        val firstName = bind.addFistNameEt.text.toString()
        val lastName = bind.addLastNameEt.text.toString()
        val age = bind.addAgeEt.text

        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, age.toString().toInt())
            mUserModel.addUse(user)
            Toast.makeText(requireContext(), "Запись $firstName добавлена в базу", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Заполните все полня", Toast.LENGTH_SHORT).show()

        }
    }


    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }


}