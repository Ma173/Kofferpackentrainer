package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import kotlinx.android.synthetic.main.content_main_screen.*

//import kotlinx.android.synthetic.main.main_screen.*

class MainScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //item changeUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        btn_new_exercise.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //TODO: Hier den Import der Elemente-Textdatei in die Datenstruktur (z.B. HashMap) einbauen
        //TODO: Nachdem alle Elemente importiert sind, können die in den Usersettings angezeigt werden
        var elementeInStand :  Map<String,Array<Any>> = mapOf("key" to arrayOf(1,"firstString","secondString",2,0))
        for (dict in elemente.txt){
            for
        }

        //val elementeInStand : HashMap<String,Array<Integer,String,String,Integer>> = hashMapOf<String,Array<Int,String,String,Int>()
        //val elementeAusStand: listOf<Object>()
        //dyn.whatever()
        //val elementeAusStand = Arraylist()

        btn_jump_to_user_settings.setOnClickListener {
            val intent = Intent(this, UserSettings::class.java)
            startActivity(intent)
        }

        btn_new_exercise.setOnClickListener {
            val greeting = findViewById<View>(R.id.greeting)
            greeting.visibility = View.INVISIBLE
            list_elemente_der_uebung.visibility = View.VISIBLE

        }



        }

    fun generateNewExercise() {

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //ehemals: onCreateOptionsMenu
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun hideText(view: View) {
        val greeting = findViewById<View>(R.id.greeting)
        greeting.visibility = View.INVISIBLE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.btn_new_exercise -> {



            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onNewExerciseButtonSelected(button: FloatingActionButton) {
        when(button.id){
            R.id.btn_new_exercise -> {
                //btn_new_exercise.setOnClickListener {
                val greeting = findViewById<View>(R.id.greeting)
                greeting.visibility = View.INVISIBLE

                }
            }
        }
    }



