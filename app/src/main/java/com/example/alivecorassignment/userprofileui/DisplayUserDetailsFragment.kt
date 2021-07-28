package com.example.alivecorassignment.userprofileui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alivecorassignment.R
import com.example.alivecorassignment.userprofilemodel.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_display_user_details.*


class DisplayUserDetailsFragment : Fragment() {

    private var userProfileViewModel: UserProfileViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userProfileViewModel =
            ViewModelProviders.of(requireActivity()).get(UserProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_display_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userProfileViewModel?.getData()!!.observe(requireActivity(),
            {
                val firstName: String? = it!!["first_name"]
                val lastName: String? = it!!["last_name"]
                val userAge: String? = it!!["age"]
                if(first_name == null || last_name == null || tv_age ==null) {
                    return@observe
                }
                first_name.text = "First name: $firstName"
                last_name.text = "Last name: $lastName"
                tv_age.text = "Age: $userAge"
            })

        btn_back.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed();
        }


    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
