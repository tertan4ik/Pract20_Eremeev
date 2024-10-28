package com.bignerdranch.android.prakt20_2

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.prakt20_2.databinding.ActivityMainBinding
import com.bignerdranch.android.prakt20_2.databinding.ContentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var contentBinding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentBinding = ContentMainBinding.bind(binding.root.findViewById(R.id.content_main))

        contentBinding.button1.setOnClickListener {
            showSnackbar(it)
        }

        contentBinding.button2.setOnClickListener {
            showActionSnackbar(it)
        }

        contentBinding.button3.setOnClickListener {
            showCustomSnackbar(it)
        }

        binding.button6.setOnClickListener {
            showSpecialSnackbar(it)
        }
    }

    private fun showSnackbar(view: View) {
        Snackbar.make(view, "java|android", Snackbar.LENGTH_LONG).show()
    }

    private fun showActionSnackbar(view: View) {
        val snackbar = Snackbar.make(view, "вы изменили его", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("вернуть как было") {
            snackbar.dismiss()
            Snackbar.make(view, "все вернулось", Snackbar.LENGTH_SHORT).show()
        }
        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.show()
    }

    private fun showCustomSnackbar(view: View) {
        val snackbar = Snackbar.make(view, "повторите еще раз", Snackbar.LENGTH_LONG)
        snackbar.setAction("повторить"){
            showCustomSnackbar(it)
        }
        snackbar.setActionTextColor(Color.RED)
        snackbar.show()
    }

    private fun showSpecialSnackbar(view: View) {
        pref = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        count = pref.getInt("number", 0) + 1
        val snackbar = Snackbar.make(view, "вы нажали на кнопку $count раз", Snackbar.LENGTH_LONG)
        var edit = pref.edit()
        edit.putInt("number", count)
        edit.apply()
        snackbar.show()
    }
}
