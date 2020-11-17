package com.cyq.jetpack.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cyq.jetpack.R
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_second, container, false)

        //接收参数方式1
        val bundle = arguments
        if (bundle != null) {
            val username = bundle.getString("username")
            val age = bundle.getInt("age")
            rootView.tvArguments.text =
                "姓名：$username+ 年龄：$age"
        }

        //接收参数方式二
        val username = arguments?.let { MainFragmentArgs.fromBundle(it).username }
        val age = arguments?.let { MainFragmentArgs.fromBundle(it).age }
        rootView.tvArguments.text = "姓名：$username+ 年龄：$age"
        return rootView
    }

}