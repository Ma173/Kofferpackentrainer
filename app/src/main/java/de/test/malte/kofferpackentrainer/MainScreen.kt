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
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import kotlinx.android.synthetic.main.content_main_screen.*
import java.util.Random

//import kotlinx.android.synthetic.main.main_screen.*

class MainScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //item changeUser

    var elementeAusStand :  Map<String,Array<Any>> = mapOf(
            "Hocke" to arrayOf(0, "Stand", "Stand", 0),
            "Bücke" to arrayOf(0, "Stand", "Stand", 0),
            "Grätsche" to arrayOf(0, "Stand", "Stand", 0),
            "halbe Schraube" to arrayOf(0.1, "Stand", "Stand", 0),
            "halbe Hocke" to arrayOf(0.1, "Stand", "Stand", 1),
            "halbe Bücke" to arrayOf(0.1, "Stand", "Stand", 1),
            "halbe Grätsche" to arrayOf(0.1, "Stand", "Stand", 1),
            "ganze Schraube" to arrayOf(0.2, "Stand", "Stand", 0),
            "Sitz" to arrayOf(0, "Stand", "Sitz", 0),
            "halbe Sitz" to arrayOf(0.1, "Stand", "Sitz", 0),
            "ganze Sitz" to arrayOf(0.2, "Stand", "Sitz", 1),
            "Hocke in den Sitz" to arrayOf(0, "Stand", "Sitz", 2),
            "Grätsche in den Sitz" to arrayOf(0, "Stand", "Sitz", 2),
            "Rücken" to arrayOf(0.1,"Stand", "Rücken", 0),
            "Halbe Rücken" to arrayOf(0.2, "Stand", "Rücken", 0),
            "Bauch" to arrayOf(0.1, "Stand","Bauch", 0)
    )
    var elementeAusSitz :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0, "Sitz", "Stand", 0),
            "halbe Stand" to arrayOf(0.1, "Sitz", "Stand", 0),
            "ganze Stand" to arrayOf(0.2, "Sitz", "Stand", 1),
            "Sitz" to arrayOf(0, "Sitz", "Sitz", 0),
            "halbe Sitz" to arrayOf(0.1, "Sitz", "Sitz", 0),
            "ganze Sitz" to arrayOf(0.2, "Sitz", "Sitz", 0),
            "Rücken" to arrayOf(0.1, "Sitz", "Rücken", 1),
            "Halbe Rücken" to arrayOf(0.2, "Sitz", "Rücken", 1),
            "Bauch" to arrayOf(0.1, "Sitz", "Bauch", 0)
    )
    var elementeAusRuecken :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Rücken", "Stand", 0),
            "halbe Stand" to arrayOf(0.2, "Rücken", "Stand", 0),
            "ganze Stand" to arrayOf(0.3, "Rücken", "Stand", 1),
            "Muffel" to arrayOf(0.3, "Rücken", "Stand", 0),
            "Salto vorwärts z. Stand" to arrayOf(0.6, "Rücken", "Stand", 1),
            "halbe Sitz" to arrayOf(0.2, "Rücken", "Sitz", 1),
            "Muffel in denSitz" to arrayOf(0.3, "Rücken", "Sitz", 0),
            "Rücken" to arrayOf(0, "Rücken", "Rücken", 0),
            "Halbe Rücken" to arrayOf(0.3, "Rücken", "Rücken", 0),
            "Salto vorwärts z. Rücken c" to arrayOf(0.5, "Rücken", "Rücken", 0),
            "Muffel in den Rücken" to arrayOf(0.5, "Rücken", "Stand", 1),
            "Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1),
            "Muffel in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1),
            "Wende in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 0)
    )
    var elementeAusBauch :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Bauch", "Stand", 0),
            "halbe Stand" to arrayOf(0.1, "Bauch", "Stand", 0),
            "Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0),
            "Wende in den Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0),
            "Bauch" to arrayOf(0, "Bauch", "Bauch", 0),
            "halbe Heli" to arrayOf(0.1, "Bauch", "Bauch", 0)
    )

    val random = Random()
    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

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
        //fun Random.asJavaRandom(): Random

        //var erstesElement : Array<Any>? = elementeAusStand[randomNumber]
        //open val keys: elementeAusStand <K>
        //val randomElementNumber = Random().nextInt(elementeAusBauch.size) + 1
        //println(elementeAusBauch[randomElementNumber])
        //println(elementeAusBauch[random.nextInt(elementeAusBauch.size)])



        btn_jump_to_user_settings.setOnClickListener {
            val intent = Intent(this, UserSettings::class.java)
            startActivity(intent)
        }

        btn_new_exercise.setOnClickListener {

            //greeting.visibility = View.INVISIBLE
            list_elemente_der_uebung.visibility = View.VISIBLE
            var newExercise = generateNewExercise()

            for (element in newExercise){

                //setContentView(R.layout.content_main_screen)
                val greeting = findViewById<View>(R.id.greeting)
                //val greeting = findViewById<View>(R.id.greeting)
                //TextView greeting = (TextView) findViewById(R.id.greeting)
                //greeting.text.setText(uebung)
                //TextView exercise = findViewById(R.id.exercise)
                exercise.setText(element.toString())
            }

        }

        }

    fun getNewElement(startingPosition : String):Pair<String,Array<Any>>{
        var keysOfDict = emptySet<String>()
        when (startingPosition){
            "Stand" -> keysOfDict = elementeAusStand.keys
            "Sitz" -> keysOfDict = elementeAusSitz.keys
            "Rücken" -> keysOfDict = elementeAusRuecken.keys
            "Bauch" -> keysOfDict = elementeAusBauch.keys
            else ->listOf("")
        }

        var DictList = elementeAusStand.map{it.key to it.value}.shuffled()
        var newElement = DictList[0]

        var randomNumber = rand(0,elementeAusStand.size)

        //var newElement = elementeAusStand.keys.get(randomNumber: Int)
        //val keysOfDictTwo=listOf("A","B")
        //var newElement = keysOfDict.get(0)//(randomNumber)

        return newElement

    }
    fun generateNewExercise():MutableList<Pair<String,Array<Any>>> {
        var jumpsInExercise = 0
        var newExercise : MutableList<Pair<String,Array<Any>>> = mutableListOf()
        while (jumpsInExercise<10){
            var newElement : Pair<String,Array<Any>> = "Hocke" to arrayOf(0, "Stand", "Stand", 0)
            if (newExercise.size==0){
                newElement = getNewElement("Stand")
            }
            else if (newExercise.size>0) {
                var letztesElement=newExercise[newExercise.lastIndex]
                newElement = getNewElement(letztesElement.second[2].toString()) //HIER DIE POSITION NACH DEM LETZTEN ELEMENT HOLEN
            }
            newExercise.add(newElement)
            jumpsInExercise+=1
        }

        return newExercise

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




