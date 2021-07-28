package com.example.alivecorassignment.userprofileui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alivecorassignment.R
import com.example.alivecorassignment.userprofilemodel.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*
import java.util.*
import kotlin.collections.HashMap


class UserProfileFragment : Fragment() {

    private var userProfileViewModel: UserProfileViewModel? = null
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        userProfileViewModel = ViewModelProviders.of(requireActivity()).get(UserProfileViewModel::class.java)

        val mCurrentTime = Calendar.getInstance()
        val year = mCurrentTime.get(Calendar.YEAR)
        val month = mCurrentTime.get(Calendar.MONTH)
        val day = mCurrentTime.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            activity!!,
            { view, year, month, dayOfMonth -> tv_date_picker.setText(
                String.format("%d / %d / %d", year, month + 1,dayOfMonth)
            )

            }, year, month, day)

        tv_date_picker.setOnClickListener {
            datePicker.show()
        }

        btn_next.setOnClickListener {
            if(tv_first_name.text.isNullOrEmpty() || tv_last_name.text.isNullOrEmpty()
                || tv_date_picker.text.isNullOrEmpty()){
                    if(tv_first_name.text.isNullOrEmpty()){
                        Toast.makeText(activity,"First Name can not be empty",Toast.LENGTH_SHORT).show()
                    }else  if(tv_last_name.text.isNullOrEmpty()){
                        Toast.makeText(activity,"Last Name can not be empty",Toast.LENGTH_SHORT).show()
                    }else  if(tv_date_picker.text.isNullOrEmpty()){
                        Toast.makeText(activity,"Date Of Birth can not be empty",Toast.LENGTH_SHORT).show()
                    } else if (year < datePicker.datePicker.year){
                        Toast.makeText(activity,"Enter correct year!",Toast.LENGTH_SHORT).show()
                    }
                return@setOnClickListener
            }
            val hashMap:HashMap<String,String> = HashMap()
            hashMap["first_name"] = tv_first_name.text.toString()
            hashMap["last_name"] = tv_last_name.text.toString()
            hashMap["date_of_birth"] = tv_date_picker.text.toString()
            var actualAge = StringBuffer("${year - datePicker.datePicker.year}" + "years")
            if(month - datePicker.datePicker.month < 0){
               val monthDiff = "${(month - datePicker.datePicker.month)*-1}"
                actualAge.append(", $monthDiff month")
            }else{
                val monthDiff = "${(month - datePicker.datePicker.month)}"
                actualAge.append(", $monthDiff month")
            }
            if(day - datePicker.datePicker.dayOfMonth < 0){
                val dayDiff = "${(day - datePicker.datePicker.dayOfMonth)*-1}"
                actualAge.append(", $dayDiff day")
            }else{
                val dayDiff = "${(day - datePicker.datePicker.dayOfMonth)}"
                actualAge.append(", $dayDiff day")
            }
            hashMap["age"] = actualAge.toString()
            userProfileViewModel!!.setData(hashMap)
            navController!!.navigate(R.id.action_userProfileFragment_to_viewUserDetailsFragment)
        }
    }


    }