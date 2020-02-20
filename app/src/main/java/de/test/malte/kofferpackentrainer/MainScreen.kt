package de.test.malte.kofferpackentrainer

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import kotlinx.android.synthetic.main.content_main_screen.*
import java.util.Random

//import kotlinx.android.synthetic.main.main_screen.*

class MainScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //item changeUser

    var elementeAusStand :  Map<String,Array<Any>> = mapOf(
            "Hocke" to arrayOf(0, "Stand", "Stand", 0,0),
            "Bücke" to arrayOf(0, "Stand", "Stand", 0,1),
            "Grätsche" to arrayOf(0, "Stand", "Stand", 0,2),
            "halbe Schraube" to arrayOf(0.1, "Stand", "Stand", 0,3),
            "halbe Hocke" to arrayOf(0.1, "Stand", "Stand", 1,4),
            "halbe Bücke" to arrayOf(0.1, "Stand", "Stand", 1,5),
            "halbe Grätsche" to arrayOf(0.1, "Stand", "Stand", 1,6),
            "ganze Schraube" to arrayOf(0.2, "Stand", "Stand", 0,7),
            "Sitz" to arrayOf(0, "Stand", "Sitz", 0,8),
            "halbe Sitz" to arrayOf(0.1, "Stand", "Sitz", 0,9),
            "ganze Sitz" to arrayOf(0.2, "Stand", "Sitz", 1,10),
            "Hocke in den Sitz" to arrayOf(0, "Stand", "Sitz", 2,11),
            "Grätsche in den Sitz" to arrayOf(0, "Stand", "Sitz", 2,12),
            "Rücken" to arrayOf(0.1,"Stand", "Rücken", 0,13),
            "Halbe Rücken" to arrayOf(0.2, "Stand", "Rücken", 0,14),
            "Bauch" to arrayOf(0.1, "Stand","Bauch", 0,15)
    )
    var elementeAusSitz :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0, "Sitz", "Stand", 0,16),
            "halbe Stand" to arrayOf(0.1, "Sitz", "Stand", 0,17),
            "ganze Stand" to arrayOf(0.2, "Sitz", "Stand", 1,18),
            "Sitz" to arrayOf(0, "Sitz", "Sitz", 0,19),
            "halbe Sitz" to arrayOf(0.1, "Sitz", "Sitz", 0,20),
            "ganze Sitz" to arrayOf(0.2, "Sitz", "Sitz", 0,21),
            "Rücken" to arrayOf(0.1, "Sitz", "Rücken", 1,22),
            "Halbe Rücken" to arrayOf(0.2, "Sitz", "Rücken", 1,23),
            "Bauch" to arrayOf(0.1, "Sitz", "Bauch", 0,24)
    )
    var elementeAusRuecken :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Rücken", "Stand", 0,25),
            "halbe Stand" to arrayOf(0.2, "Rücken", "Stand", 0,26),
            "ganze Stand" to arrayOf(0.3, "Rücken", "Stand", 1,27),
            "Muffel" to arrayOf(0.3, "Rücken", "Stand", 0,28),
            "Salto vorwärts z. Stand" to arrayOf(0.6, "Rücken", "Stand", 1,29),
            "halbe Sitz" to arrayOf(0.2, "Rücken", "Sitz", 1,30),
            "Muffel in denSitz" to arrayOf(0.3, "Rücken", "Sitz", 0,31),
            "Rücken" to arrayOf(0, "Rücken", "Rücken", 0,32),
            "Halbe Rücken" to arrayOf(0.3, "Rücken", "Rücken", 0,33),
            "Salto vorwärts z. Rücken c" to arrayOf(0.5, "Rücken", "Rücken", 0,34),
            "Muffel in den Rücken" to arrayOf(0.5, "Rücken", "Rücken", 1,35),
            "Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1,36),
            "Muffel in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1,37),
            "Wende in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 0,38)
    )
    var elementeAusBauch :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Bauch", "Stand", 0,39),
            "halbe Stand" to arrayOf(0.1, "Bauch", "Stand", 0,40),
            "Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0,41),
            "Wende in den Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0,42),
            "Bauch" to arrayOf(0, "Bauch", "Bauch", 0,43),
            "halbe Heli" to arrayOf(0.1, "Bauch", "Bauch", 0,44)
    )

    val random = Random()
    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

    private lateinit var listView: ListView
    private lateinit var list_elemente_der_uebung: ListView

    fun buttonEffect(button: View) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background.setColorFilter(Color.argb(255,63,81,181), PorterDuff.Mode.SRC_ATOP)//-0x1f0b8adf //(3f51b5, PorterDuff.Mode.SRC_ATOP)
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }
    }
    private val TAG = "None"

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")

        val userText = savedInstanceState?.getCharSequence("savedText")
        //editText.setText(userText)
        greeting.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setSupportActionBar(toolbar)

        if (savedInstanceState != null){
            var x = savedInstanceState.getBoolean("visibility")
            var s = savedInstanceState.getString("text")
        }

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

            buttonEffect(btn_new_exercise)

            greeting.visibility = View.INVISIBLE
            //list_elemente_der_uebung.visibility = View.VISIBLE

            val elementList = findViewById<ListView>(R.id.list_elemente_der_uebung)
            var newExercise = generateNewExercise()
            val listItems = arrayOfNulls<String>(newExercise.size)

            var exerciseString = ""
            for (i in 0 until newExercise.size) {//(element in newExercise){
                //TextView exercise = findViewById(R.id.exercise)
                val element = newExercise[i]
                listItems[i] = element.first //dies ist der String (Name des Elements), der vorne im Pair aus Element und Elementeigenschaften steht
                exerciseString=exerciseString+"\n"+element.first
            }
            exercise.setText(exerciseString)
            val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, listItems)
            //listView.adapter = MyCustomAdapter(this)


        }

        btn_save_exercise.setOnClickListener {view ->
            Snackbar.make(view, "Zurzeit kein Speichern möglich", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            buttonEffect(btn_save_exercise)

        }


        }
    private class MyCustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context
        init {
            this.mContext = context
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "HERE is my ROW for my LISTVIEW"
            return textView
        }
    }

    fun getNewElement(startingPosition : String):Pair<String,Array<Any>>{
        var keysOfDict = emptySet<String>()
        var DictList = elementeAusStand.map{it.key to it.value}.shuffled()
        when (startingPosition){
            "Stand" -> DictList = elementeAusStand.map{it.key to it.value}.shuffled()//keysOfDict = elementeAusStand.keys
            "Sitz" -> DictList = elementeAusSitz.map{it.key to it.value}.shuffled()//keysOfDict = elementeAusSitz.keys
            "Rücken" -> DictList = elementeAusRuecken.map{it.key to it.value}.shuffled()//keysOfDict = elementeAusRuecken.keys
            "Bauch" -> DictList = elementeAusBauch.map{it.key to it.value}.shuffled()//keysOfDict = elementeAusBauch.keys
            else ->listOf("")
        }

        var newElement = DictList[0]

        //var randomNumber = rand(0,elementeAusStand.size)

        //var newElement = elementeAusStand.keys.get(randomNumber: Int)
        //val keysOfDictTwo=listOf("A","B")
        //var newElement = keysOfDict.get(0)//(randomNumber)

        return newElement

    }
    private fun generateNewExercise():MutableList<Pair<String,Array<Any>>> {
        var jumpsInExercise = 0
        var newExercise : MutableList<Pair<String,Array<Any>>> = mutableListOf()
        while (jumpsInExercise<10){
            var newElement : Pair<String,Array<Any>> = "Hocke" to arrayOf(0, "Stand", "Stand", 0,0) //default-Element (Hocke) zum Initialisieren der Variable verwendet
            var elementArray=UserSettings().elementArray
            var elementId = newElement.second[4]
            var gesperrte = arrayOf(10, 34)
            if (newExercise.size==0){
                newElement = getNewElement("Stand")
                var elementId = newElement.second[4]
                while (elementId in gesperrte){//(elementArray[elementId.toInt()]==0){
                    newElement = getNewElement("Stand")
                    var elementId = newElement.second[4]
                }
            }
            else if (newExercise.size>0) {
                var letztesElement=newExercise[newExercise.lastIndex]
                newElement = getNewElement(letztesElement.second[2].toString()) //HIER DIE POSITION NACH DEM LETZTEN ELEMENT HOLEN
                var elementId = newElement.second[4]
                var elementArray=UserSettings().elementArray
                if (jumpsInExercise==9){
                    while (elementId in gesperrte || newElement.second[2]!="Stand"){
                        newElement = getNewElement(letztesElement.second[2].toString())
                        var elementId = newElement.second[4]
                    }
                }

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
                val intent = Intent(this,SavedExercises::class.java)
                startActivity(intent)
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




