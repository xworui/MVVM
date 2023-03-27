package com.example.approomdb.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer.ListListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.approomdb.R
import com.example.approomdb.data.UserViewModel
import com.example.approomdb.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var bind: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentListBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel ::class.java)

        val adapter = ListAdapter()
        var resycleView = bind.resycleview
        resycleView.adapter = adapter
        resycleView.layoutManager = LinearLayoutManager(requireContext())


        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {listuser ->
            adapter.setData(listuser)

        })

        bind.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return bind.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_list, container, false)
    }


}