package de.test.malte.kofferpackentrainer


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import kotlinx.android.synthetic.main.content_main_screen.*
import java.util.*


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
            "Bauch" to arrayOf(0.1, "Stand","Bauch", 0,15),
            "halbe Bauch" to arrayOf(0.1, "Stand","Bauch", 0,16)
    )
    var elementeAusSitz :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0, "Sitz", "Stand", 0,17),
            "halbe Stand" to arrayOf(0.1, "Sitz", "Stand", 0,18),
            "ganze Stand" to arrayOf(0.2, "Sitz", "Stand", 1,19),
            "Sitz" to arrayOf(0, "Sitz", "Sitz", 0,20),
            "halbe Sitz" to arrayOf(0.1, "Sitz", "Sitz", 0,21),
            "ganze Sitz" to arrayOf(0.2, "Sitz", "Sitz", 0,22),
            "Rücken" to arrayOf(0.1, "Sitz", "Rücken", 1,23),
            "Halbe Rücken" to arrayOf(0.2, "Sitz", "Rücken", 1,24),
            "Bauch" to arrayOf(0.1, "Sitz", "Bauch", 0,25)
    )
    var elementeAusRuecken :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Rücken", "Stand", 0, 26),
            "halbe Stand" to arrayOf(0.2, "Rücken", "Stand", 0, 27),
            "ganze Stand" to arrayOf(0.3, "Rücken", "Stand", 1, 28),
            "halbe Sitz" to arrayOf(0.2, "Rücken", "Sitz", 1, 29),
            "Rücken" to arrayOf(0, "Rücken", "Rücken", 0, 30),
            "Halbe Rücken" to arrayOf(0.3, "Rücken", "Rücken", 0, 31),
            "Muffel" to arrayOf(0.3, "Rücken", "Stand", 0, 32),
            "Muffel in den Sitz" to arrayOf(0.3, "Rücken", "Sitz", 0, 33),
            "Muffel in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1, 34),
            "Muffel in den Rücken" to arrayOf(0.5, "Rücken", "Rücken", 1, 35),
            "Salto vw. in den Rücken" to arrayOf(0.5, "Rücken", "Rücken", 0, 36),
            "Salto vw. in den Stand" to arrayOf(0.6, "Rücken", "Stand", 1, 37),
            "Bauch" to arrayOf(0.2, "Rücken", "Bauch", 1, 38),
            "Wende in den Bauch" to arrayOf(0.2, "Rücken", "Bauch", 0, 39)
    )
    var elementeAusBauch :  Map<String,Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Bauch", "Stand", 0,40),
            "halbe Stand" to arrayOf(0.1, "Bauch", "Stand", 0,41),
            "Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0,42),
            "Wende in den Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0,43),
            "Bauch" to arrayOf(0, "Bauch", "Bauch", 0,44),
            "halbe Heli" to arrayOf(0.1, "Bauch", "Bauch", 0,45)
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

    //var greetingVisible=0//greeting.visibility // kann 0 4 oder 8 sein, also visible, invisible und gone
    //var greetingVisibility: SharedPreferences? = getSharedPreferences("greetingVisibility", Context.MODE_PRIVATE)

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //val prefsEditor: Editor = greetingVisibility.edit()
        //prefsEditor.putInt("visibility", mEditText.getVisibility())
        println("_______ onSaveInstanceState wurde getriggered")

        Log.i(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")

        val userText = savedInstanceState?.getCharSequence("savedText")
        //editText.setText(userText)
        println("_______ onRestoreInstanceState wurde getriggered")

    }
    fun getContext():Context{
        val context: Context = applicationContext
        return context
    }

    var currentUser=""
    public var newExercise : MutableList<Pair<String,Array<Any>>> = mutableListOf()


    fun getDeactivatedElementsArray():List<String>{
        //val currentUser = getSharedPreferences("currentUser",0)
        val sizeOfAllElementArrays = 4 + elementeAusStand.size+elementeAusSitz.size+elementeAusRuecken.size+elementeAusBauch.size
        val context=getContext()
        val filename =currentUser+"_deactivatedElementsFile"
        //println("$filename ist der Dateiname ************")
        var deactivatedElementsFile = ""
        var deactivatedElementsList = listOf<String>()//mutableListOf<String>()
        var outputListString =""

        deactivatedElementsFile =UserSettings().readFromFile(context,filename)

        if (deactivatedElementsFile!=""){
            deactivatedElementsList = deactivatedElementsFile.split(",")
            println("------------------------- loaded deactivatedElementsFile: $deactivatedElementsList")
        }
        else{
            val deactivatedElementsArray =intArrayOf(-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, -1, 1, 1, 1, 1, 1, 1)
            var userSkill = ""
            userSkill =UserSettings().readFromFile(context,"lastSessionUserSkill")
            if (userSkill !=""){
                UserSettings().setDeactivatedElementsToSkill(userSkill,deactivatedElementsArray)
            }

            deactivatedElementsList = mutableListOf<String>()
            for (element in deactivatedElementsArray){
                deactivatedElementsList.add(element.toString())
            }

            println("_________________ no deactivatedElementsFile found. Setting to default: $deactivatedElementsList")
        }

        return deactivatedElementsList
    }
    //getting last session's userName
    fun getLastSessionUserName():String{
        var lastSessionUserName: String = ""
        val context=getContext()
        lastSessionUserName=UserSettings().readFromFile(context,"lastSessionUserName")
        if (lastSessionUserName==""){
            lastSessionUserName="defaultUser"
            println("_______________________________________ no lastSessionUserName found. Setting to defaultUser")
        }
        else{
            println("_______________________________________User found. Loaded current user: $lastSessionUserName")
        }

        return lastSessionUserName
    }

    fun firstTime() {
        val sharedTime = getSharedPreferences("preferences_name2", 0)
        if (sharedTime.getBoolean("firstTimeMainScreen", true)) { //Your tutorial code
            sharedTime.edit().putBoolean("firstTimeMainScreen", false).apply()
        }
        else { //When not using tutorial code
            //Toast.makeText(this, "Sie waren schon in der Activity NewUser", Toast.LENGTH_LONG).show()
            val context = applicationContext
            greeting.visibility=View.GONE
            val lastExercise=UserSettings().readFromFile(context,"lastExercise")
            println("_________setting exercise to:$lastExercise")
            exercise.setText(lastExercise)
            exercise.visibility=View.VISIBLE

        }
    }

    var continuousMode = false
    var cameToSave: String? = ""
    var exerciseString = ""
    //var previousExerciseString = "TEST"

    fun getPreviousExerciseString(): String{
        val previousExerciseString = UserSettings().readFromFile(applicationContext,"lastExercise")
        return previousExerciseString
    }
    /*
    fun setPreviousExerciseString(): String{
        val previousExerciseString =
                UserSettings().writeToFile(applicationContext,previousExerciseString,"lastExercise")
        return previousExerciseString
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        //val context: Context = applicationContext
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        //val actionBar : ActionBar = getSupportActionBar()
                //getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false);


        //val actionBar = getSupportActionBar()
        //actionBar.setDisplayShowTitleEnabled(false);


        if (savedInstanceState != null){
            var x = savedInstanceState.getBoolean("visibility")
            var s = savedInstanceState.getString("text")
            }
        val extras = intent.extras
        if (extras != null) {
            println("_______________________ EXTRAS IST NICHT NULL")
            cameToSave = extras.getString("cameToSave")
            if (cameToSave=="true"){
                println("____________ cameToSave-Wert ist tatsächlich true")
                btn_save_exercise.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else{
                println("___________ cameToSave ist: $cameToSave")
            }
        }


        firstTime()

        currentUser=getLastSessionUserName().replace("\\s".toRegex(),"")
        println("_____________current User is: $currentUser")
        subtitle.text = currentUser

        /*val extras = intent.extras
        if (extras != null) {
            currentUser = extras.getString("currentUser")
            subtitle.setText(currentUser)
        }
        else{
            currentUser = "defaultUser"
            subtitle.setText(currentUser)
        }*/
        exercise.setFocusable(false)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


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
            val context =applicationContext
            UserSettings().writeToFile(context,exerciseString,"lastExercise")
            println("_________ aktuell ist Endlosmodus $continuousMode")

            buttonEffect(btn_new_exercise)
            btn_save_exercise.setImageResource(android.R.drawable.btn_star_big_off)

            greeting.visibility = View.INVISIBLE
            //list_elemente_der_uebung.visibility = View.VISIBLE

            //val elementList = findViewById<ListView>(R.id.list_elemente_der_uebung)
            val newExercise = generateNewExercise()
            var length = newExercise.size

            val listItems = arrayOfNulls<String>(newExercise.size)


            for (i in 0 until newExercise.size) {//(element in newExercise){
                //TextView exercise = findViewById(R.id.exercise)
                val element = newExercise[i]
                listItems[i] = element.first //dies ist der String (Name des Elements), der vorne im Pair aus Element und Elementeigenschaften steht
                exerciseString+="\n"+element.first
            }
            exerciseString=exerciseString.substring(1)
            length=exerciseString.split("\n").size
            println("________ exerciseString hat eine Länge von $length")
            exercise.setText(exerciseString)



            //val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, listItems)
            //listView.adapter = MyCustomAdapter(this)
        }




        // Saving the displayed exercise
        btn_save_exercise.setOnClickListener {view ->
            //Snackbar.make(view, "Zurzeit kein Speichern möglich", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show()
            //buttonEffect(btn_save_exercise)
            btn_save_exercise.setImageResource(android.R.drawable.btn_star_big_on)//@android:drawable.big_star_big_off
            val context = applicationContext

            val intent = Intent(this, SavedExercises::class.java)

            // Zwei Wege, die Übung an SavedExercises zu übergeben: 1. Mit "currentExerciseElementNames" als einzelne Übung als String, in dem mit Zeilenumbruch alle Elemente als Klarnamen stehen
            // 2. als String, in dem jede Übung eine Zeile ist und jedes Element als Array getrennt von "|" steht
            // wobei ich 2 gar nicht brauche, da beim Laden ja auch die fertige Übung als String reicht
            var currentExerciseElementNames=""
            //var currentExerciseElementsInLine= ""
            var loadedSavedExercisesAsString=UserSettings().readFromFile(context,"savedExercises")
            /*if (loadedSavedExercisesAsString.startsWith("\n__________\n",true){
                loadedSavedExercisesAsString=loadedSavedExercisesAsString.substring(11)
            }*/
            var allSavedExercises=loadedSavedExercisesAsString.substring(3)

            for (element in newExercise){
                val firstPart=element.first
                val secondPart=element.second
                currentExerciseElementNames+="$firstPart\n"
                //currentExerciseElementsInLine=currentExerciseElementsInLine+element+" | "


            }
            allSavedExercises+= "__________\n"+currentExerciseElementNames
            allSavedExercises=allSavedExercises.replace("\n__________\n","\n")
            println("______ AllSavedExercises: $allSavedExercises")
            //UserSettings().writeToFile(context,exercisesToSaveAsString,"savedExercises")
            //newExercise.toString()

            UserSettings().writeToFile(context,allSavedExercises,"savedExercises")
            intent.putExtra("currentExerciseElementNames", currentExerciseElementNames)
            intent.putExtra("cameToSave", "true")
            startActivity(intent)


        }



        btn_previousExercise.setOnClickListener{

            btn_previousExercise.background.setColorFilter(Color.argb(255,63,81,181), PorterDuff.Mode.SRC_ATOP)//btn_previousExercise.setBackgroundColor(#0000)
            var previousExerciseString=getPreviousExerciseString()
            if (previousExerciseString!=""){
                previousExerciseString=previousExerciseString.substring(1)
            }

            exercise.setText(previousExerciseString)

        }

        val context: Context = applicationContext
        UserSettings().writeToFile(context,currentUser,"lastSessionUserName")


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

        btn_previousExercise.background.clearColorFilter()

        // Mode for adding elements to the exercise or creating always a new one of 10
        val sw = findViewById(R.id.continuousExerciseSwitch) as Switch
        sw.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> continuousMode=true // The toggle is enabled
                false -> continuousMode = false // The toggle is disabled
            }}
        if (!continuousMode){
            newExercise.clear()
            exerciseString=""
        }
        exercise.visibility = View.VISIBLE
        //exercise.setVisibity()
        var jumpsInExercise = 0
        val deactivatedElementsArray = getDeactivatedElementsArray()
        /*var gesperrteElemente = arrayListOf<Int>()
        val elementArray = UserSettings().elementArray
        val gesperrte = arrayOf(11, 24, 39, 40)*/

        val deactivatedElementsArrayClean = IntArray(deactivatedElementsArray.size)//.size-4)
        println("Bereinige das deactivatedElementsArray..............")
        //val erster= deactivatedElementsArray[0]
        //val zweiter= deactivatedElementsArray[1]
        val typErster = deactivatedElementsArray[0].javaClass.name
        val laenge = deactivatedElementsArray.size
        //println("Der zweite Eintrag von deactivatedElementsArray ist $erster mit dem Typ $typErster. Länge des Arrays/ der Liste ist: $laenge\n\n")
        var currentCount=0
        // Bereinigung des DeactivatedElementArrays
        for (elementActivation in deactivatedElementsArray){//for ((index,value) in deactivatedElementsArray.withIndex()){
            val index = currentCount
            val element=elementActivation.replace("\\s".toRegex(),"")
            //println("Gehe durch das deactivatedElementsArray. Aktueller count: $currentCount. Aktuelles Element: $element")
            /*if (element=="\n[-1"){
                println("ääääääääääääääääääääääääää Element ist: [-1")
            }*/
            when (element){
                "-1" -> {
                        println("Element ist -1 und wird nicht hinzugefügt. Id ist: $index")//deactivatedElementsArrayClean[index] = element.toInt()
                        }
                "\n[-1" -> {
                    println("Element ist -1 und wird nicht hinzugefügt. Id ist: $index")//deactivatedElementsArrayClean.set(index,element.toInt())
                        }
                "-1]" -> {
                    println("Element ist -1 und wird nicht hinzugefügt. Id ist: $index")
                    deactivatedElementsArrayClean.set(index,element.toInt())}
                "0" -> {
                    println("----Füge deaktiviertes Element zu cleanArray hinzu: Id ist $index")
                    deactivatedElementsArrayClean.set(index,element.toInt())
                    currentCount+=1}

                "1" -> {

                    println("Füge Aktiviertes Element zu cleanArray hinzu: Id ist $index, Name ist $element")
                    deactivatedElementsArrayClean.set(index,element.toInt())
                    currentCount+=1}
                }
            }
        val deactivatedElementsArrayCleanString = deactivatedElementsArrayClean.toString()
        println("cleaned deactivatedElementsArray:")// $deactivatedElementsArrayCleanString")
        for (element in deactivatedElementsArrayClean){
            print("$element ")
        }
        var newElement : Pair<String,Array<Any>> = "Hocke" to arrayOf(0, "Stand", "Stand", 0,0) //default-Element (Hocke) zum Initialisieren der Variable verwendet
        var letztesElement = listOf(newElement.second).toString() //var letztesElement= newElement.second[2].Arrays.toString()
        var elementId = newElement.second[4]
        val elementArray = UserSettings().elementArray
        //var letztesElement = "Stand"
        while (jumpsInExercise<10) {
            println("\n\n ----------------------------------------")

            //var elementId: Any// vorher: newElement.second[4]
            //println("€€€€€€€€ Letztes Element: $letztesElement")
            //[2].toString()
            if (newExercise.size > 0) {letztesElement = newExercise[newExercise.lastIndex].second.toString()}
            newElement = getNewElement(letztesElement) //HIER DIE POSITION NACH DEM LETZTEN ELEMENT HOLEN
            elementId = newElement.second[4]
            //println(":::::::::::: Letztes Element ist:$letztesElement, daher ist das neue Element: $newElement")
            elementId = newElement.second[4]
            println("+ Aktuell gezogene Id ist: $elementId. Das Element ist $newElement")
            // Neugenerierung des newElement, wenn das zuvor generierte Element deaktiviert ist (deactivatedElementsArrayClean = 1 ist)
            // Vergleich bei "(deactivatedElementsArrayClean[elementId as Int] <= 0)" auf "<=" geändert von "==", damit auch der Wert "-1" betrachtet wird
            if (jumpsInExercise <9) {
                while (deactivatedElementsArrayClean[elementId as Int] != 0) {//(elementArray[elementId as Int]==0){
                    var wertImArray = elementArray[elementId as Int]
                    //Toast.makeText(this, "AltesElement: Id ist $elementId ", Toast.LENGTH_LONG).show() //"Anzahl gespeicherte User: $lengthUserNames"
                    println("+++++++++++ ElementInDerÜbungNr: $jumpsInExercise AltesElement: Id ist $elementId, Element ist $newElement. Wert im Array ist $wertImArray.")
                    newElement = getNewElement(letztesElement)
                    elementId = newElement.second[4]

                    wertImArray = elementArray[elementId as Int]
                    //Toast.makeText(this, "NeuesElement: Id ist $elementId ", Toast.LENGTH_LONG).show() //"Anzahl gespeicherte User: $lengthUserNames"
                    println("+++++++++++ NeuesElement: Id ist $elementId, Element ist $newElement. Wert im Array ist $wertImArray.\n")
                }
            }
            // If-Abfrage für letzte Übung -> damit am Ende eine Übung in den Stand kommt
            else if (jumpsInExercise == 9) {
                while (deactivatedElementsArrayClean[elementId as Int] != 0 || newElement.second[2] != "Stand") {//(elementArray[elementId as Int]==0 || newElement.second[2]!="Stand"){
                    val letzteselement = newElement.second[2]//zur Info
                    var wertImArray = elementArray[elementId as Int]
                    println("****************************************** elementId ist in gesperrte oder das letzte Element ist nicht Stand: $elementId. Hole neues Element")
                    newElement = getNewElement(letztesElement)
                    elementId = newElement.second[4]
                    wertImArray = elementArray[elementId as Int]
                    println("+++++++++++ NeuesElement: Id ist $elementId, Element ist $newElement. Wert im Array ist $wertImArray.\n")
                }
            }

            newExercise.add(newElement)
            jumpsInExercise += 1
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
            R.id.nav_importExercises -> {
                // Übung importieren
                val intent = Intent(this, ImportExercises::class.java)
                startActivity(intent)
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_savedExercises -> {
                // Navigation zu den gespeicherten Übungen
                val intent = Intent(this,SavedExercises::class.java)
                startActivity(intent)
            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_deleteAllSavedExercises -> {
                UserSettings().writeToFile(applicationContext,"","savedExercises")
            }
            R.id.btn_new_exercise -> {



            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    }




