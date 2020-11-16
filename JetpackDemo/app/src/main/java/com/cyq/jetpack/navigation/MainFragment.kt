package com.cyq.jetpack.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cyq.jetpack.R
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView.btnSkip.setOnClickListener {
            //方式1
            val bundle = Bundle()
            bundle.putString("username", "张三")
            bundle.putInt("age", 23)

            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_mainFragment_to_secondFragment, bundle)

            //方式2：使用safe args插件

        }
        return rootView
    }
}