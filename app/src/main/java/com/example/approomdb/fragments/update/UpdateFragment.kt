package com.example.approomdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.approomdb.R
import com.example.approomdb.data.User
import com.example.approomdb.data.UserViewModel
import com.example.approomdb.databinding.FragmentAddBinding
import com.example.approomdb.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    lateinit var bind: FragmentUpdateBinding
    private lateinit var mUserModel: UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserModel = ViewModelProvider(this).get(UserViewModel::class.java)
        bind.UpdateFistNameEt.setText(args.curentUser.firstName)
        bind.UpdateLastNameEt.setText(args.curentUser.lastName)
        bind.UpdateAgeEt.setText(args.curentUser.age.toString())

        bind.UpdatebtnSave.setOnClickListener{
            updateItem()
        }

        setHasOptionsMenu(true)

        return bind.root    //inflate(R.layout.fragment_update, container, false)
    }

    private fun updateItem() {
        val firstName = bind.UpdateFistNameEt.text.toString()
        val lastName = bind.UpdateLastNameEt.text.toString()
        val age = bind.UpdateAgeEt.text
        if(inputCheck(firstName, lastName, age)){
            val user = User(args.curentUser.id, firstName, lastName, age.toString().toInt())
            mUserModel.updateUse(user)


            Toast.makeText(requireContext(), "Запись $firstName успешно изменена", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Заполните все полня", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Да"){ _, _ ->
            mUserModel.deleteUse(args.curentUser)
            Toast.makeText(requireContext(), "Запись успешно удалена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)


        }
        builder.setNegativeButton("Нет"){ _, _ ->}
        builder.setTitle("Удалить запись?")
        builder.setMessage("Вы действительно хотите удалить запись: ${args.curentUser.firstName}?")
        builder.create().show()
    }

}