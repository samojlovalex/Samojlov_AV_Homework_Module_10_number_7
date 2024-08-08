package com.example.samojlov_av_homework_module_10_number_7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.samojlov_av_homework_module_10_number_7.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar
    private lateinit var editTextNameET: EditText
    private lateinit var editTextAgeET: EditText
    private lateinit var saveLaunchBTN: Button
    private lateinit var listViewLW: ListView
    var userList: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        toolbarMain = binding.toolbarMain
        setSupportActionBar(toolbarMain)
        title = getString(R.string.toolbar_title)
        toolbarMain.subtitle = getString(R.string.toolbar_subtitle)
        toolbarMain.setLogo(R.drawable.toolbar_icon)

        editTextNameET = binding.editTextNameET
        editTextAgeET = binding.editTextAgeET

        listViewLW = binding.listViewLW
        val adapter = ArrayAdapter(this, R.layout.mytextview, userList)
        listViewLW.adapter = adapter

        saveLaunchBTN = binding.saveLaunchBTN
        saveLaunchBTN.setOnClickListener {
            if (editTextNameET.text.isEmpty() || editTextAgeET.text.isEmpty()) return@setOnClickListener
            val user = User(editTextNameET.text.toString(), editTextAgeET.text.toString())
            userList.add(user)
            adapter.notifyDataSetChanged()
            editTextNameET.text.clear()
            editTextAgeET.text.clear()
        }

        listViewLW.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val note = adapter.getItem(position)
                Snackbar.make(v, "Удалить пользователя?", Snackbar.LENGTH_LONG)
                    .setAction("Удалить") {
                        adapter.remove(note)
                        Snackbar.make(v, "Пользователь $note удален", Snackbar.LENGTH_LONG).show()
                    }.show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}