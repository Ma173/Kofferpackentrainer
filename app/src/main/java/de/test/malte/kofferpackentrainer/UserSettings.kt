package de.test.malte.kofferpackentrainer

//import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import kotlinx.android.synthetic.main.activity_user_settings.*
import java.io.*


//TODO: Daten/ Variablen, die dauerhaft gespeichert werden müssen:
// die mutableList: userNames
// das array der gesperrten Elemente: elementArray

class UserSettings : AppCompatActivity() {

    fun writeToFile(context: Context, data: String, filename: String) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }

    fun readFromFile(context: Context, filename: String): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput(filename)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also({ receiveString = it }) != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: " + e.toString())
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return ret
    }


    var elementeAusStand: Map<String, Array<Any>> = mapOf(
            "Hocke" to arrayOf(0, "Stand", "Stand", 0, 0),
            "Bücke" to arrayOf(0, "Stand", "Stand", 0, 1),
            "Grätsche" to arrayOf(0, "Stand", "Stand", 0, 2),
            "halbe Schraube" to arrayOf(0.1, "Stand", "Stand", 0, 3),
            "halbe Hocke" to arrayOf(0.1, "Stand", "Stand", 1, 4),
            "halbe Bücke" to arrayOf(0.1, "Stand", "Stand", 1, 5),
            "halbe Grätsche" to arrayOf(0.1, "Stand", "Stand", 1, 6),
            "ganze Schraube" to arrayOf(0.2, "Stand", "Stand", 0, 7),
            "Sitz" to arrayOf(0, "Stand", "Sitz", 0, 8),
            "halbe Sitz" to arrayOf(0.1, "Stand", "Sitz", 0, 9),
            "ganze Sitz" to arrayOf(0.2, "Stand", "Sitz", 1, 10),
            "Hocke in den Sitz" to arrayOf(0, "Stand", "Sitz", 2, 11),
            "Grätsche in den Sitz" to arrayOf(0, "Stand", "Sitz", 2, 12),
            "Rücken" to arrayOf(0.1, "Stand", "Rücken", 0, 13),
            "Halbe Rücken" to arrayOf(0.2, "Stand", "Rücken", 0, 14),
            "Bauch" to arrayOf(0.1, "Stand", "Bauch", 0, 15),
            "halbe Bauch" to arrayOf(0.1, "Stand", "Bauch", 0, 16)
    )
    var elementeAusSitz: Map<String, Array<Any>> = mapOf(
            "Stand" to arrayOf(0, "Sitz", "Stand", 0, 17),
            "halbe Stand" to arrayOf(0.1, "Sitz", "Stand", 0, 18),
            "ganze Stand" to arrayOf(0.2, "Sitz", "Stand", 1, 19),
            "Sitz" to arrayOf(0, "Sitz", "Sitz", 0, 20),
            "halbe Sitz" to arrayOf(0.1, "Sitz", "Sitz", 0, 21),
            "ganze Sitz" to arrayOf(0.2, "Sitz", "Sitz", 0, 22),
            "Rücken" to arrayOf(0.1, "Sitz", "Rücken", 1, 23),
            "Halbe Rücken" to arrayOf(0.2, "Sitz", "Rücken", 1, 24),
            "Bauch" to arrayOf(0.1, "Sitz", "Bauch", 0, 25)
    )
    var elementeAusRuecken: Map<String, Array<Any>> = mapOf(
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
    var elementeAusBauch: Map<String, Array<Any>> = mapOf(
            "Stand" to arrayOf(0.1, "Bauch", "Stand", 0, 40),
            "halbe Stand" to arrayOf(0.1, "Bauch", "Stand", 0, 41),
            "Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0, 42),
            "Wende in den Rücken" to arrayOf(0.2, "Bauch", "Rücken", 0, 43),
            "Bauch" to arrayOf(0, "Bauch", "Bauch", 0, 44),
            "halbe Heli" to arrayOf(0.1, "Bauch", "Bauch", 0, 45)
    )

    object Utility {
        fun setListViewHeightBasedOnChildren(listView: ListView) {
            val listAdapter = listView.adapter
                    ?: // pre-condition
                    return
            var totalHeight = 0
            val desiredWidth = MeasureSpec.makeMeasureSpec(listView.width, MeasureSpec.AT_MOST)
            for (i in 0 until listAdapter.count) {
                val listItem = listAdapter.getView(i, null, listView)
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED)
                totalHeight += listItem.measuredHeight
            }
            val params = listView.layoutParams
            params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
            listView.layoutParams = params
            listView.requestLayout()
        }
    }

    public var elementArray: IntArray = intArrayOf(-1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, -1, 1, 1, 1, 1, 1, 1)

    // Ändert das Array der deaktivierten Übungen. Wird aufgerufen, bevor das Array in die ListView geladen wird
    fun setDeactivatedElementsToSkill(skill: String?,arrayOfDeactivatedElements:IntArray) {
        when (skill) {
            "low" -> {
                arrayOfDeactivatedElements[11] = 0
                arrayOfDeactivatedElements[12] = 0
                arrayOfDeactivatedElements[13] = 0
                arrayOfDeactivatedElements[15] = 0
                arrayOfDeactivatedElements[16] = 0
                arrayOfDeactivatedElements[17] = 0
                arrayOfDeactivatedElements[21] = 0
                arrayOfDeactivatedElements[24] = 0
                arrayOfDeactivatedElements[26] = 0
                arrayOfDeactivatedElements[27] = 0
                arrayOfDeactivatedElements[31] = 0
                arrayOfDeactivatedElements[32] = 0
                arrayOfDeactivatedElements[34] = 0
                arrayOfDeactivatedElements[35] = 0
                arrayOfDeactivatedElements[36] = 0
                arrayOfDeactivatedElements[37] = 0
                arrayOfDeactivatedElements[38] = 0
                arrayOfDeactivatedElements[39] = 0
                arrayOfDeactivatedElements[40] = 0
                arrayOfDeactivatedElements[41] = 0
                arrayOfDeactivatedElements[42] = 0
                arrayOfDeactivatedElements[44] = 0
                arrayOfDeactivatedElements[45] = 0
                arrayOfDeactivatedElements[46] = 0
                arrayOfDeactivatedElements[47] = 0
                arrayOfDeactivatedElements[48] = 0
                arrayOfDeactivatedElements[49] = 0
            }
            "medium" -> {
                arrayOfDeactivatedElements[11] = 0
                arrayOfDeactivatedElements[21] = 0
                arrayOfDeactivatedElements[24] = 0
                arrayOfDeactivatedElements[31] = 0
                arrayOfDeactivatedElements[38] = 0
                arrayOfDeactivatedElements[39] = 0
                arrayOfDeactivatedElements[40] = 0
            }
            "high" -> {

            }
        }

    }

    //var elementArray = Array(45)//arrayOf<int,array(50,{IntArray(1)}) // Array( elementId, {IntArray(activationStatus)})
    //create a list of items for the spinner
    //public var userNames = emptyArray<String>()

    public var userNames = mutableListOf<String>()
    public var currentUserName=""



    fun firstTime() {
        val sharedTime = getSharedPreferences("preferences_name2", 0)
        if (sharedTime.getBoolean("firstTime", true)) { //Your tutorial code
            sharedTime.edit().putBoolean("firstTime", false).apply()
            //TODO: Import user files like list of users


        }
        else { //When not using tutorial code
        //Toast.makeText(this, "Sie waren schon in der Activity NewUser", Toast.LENGTH_LONG).show()
        val intentFromNewUserActivity: Intent = getIntent();
        var newUserName = intentFromNewUserActivity.getStringExtra("newUserName")
        if (newUserName == null) {
            newUserName = "defaultUser"
        }
        // In der Activity "NewUser" heißt es: sharedUserData.edit().putString(newUserName,userSkill).apply()
        val sharedUserData = getSharedPreferences(newUserName, 0)


        //Toast.makeText(this, "Elemente deaktiviert für Skill: $newUserSkill", Toast.LENGTH_LONG).show() //"Anzahl gespeicherte User: $lengthUserNames"

        }
    }
    var list = mutableListOf<Model>()

    fun setElementsListObjects(){
        list.add(Model("Sprünge aus dem Stand", "", R.drawable.image_noimage))
        list.add(Model("Hocke", "Stand > Hocke > Stand", R.drawable.button_activated))
        list.add(Model("Bücke", "Stand > Bücke > Stand", R.drawable.button_activated))
        list.add(Model("Grätsche", "Stand > Grätsche > Stand", R.drawable.button_activated))
        list.add(Model("Halbe Schraube", "Stand > halbe Schraube > Stand", R.drawable.button_activated))
        list.add(Model("Halbe Hocke", "Stand > halbe Hocke > Stand", R.drawable.button_activated))
        list.add(Model("Halbe Bücke", "Stand > halbe Bücke > Stand", R.drawable.button_activated))
        list.add(Model("Halbe Grätsche", "Stand > halbe Grätsche > Stand", R.drawable.button_activated))
        list.add(Model("Ganze Schraube", "Stand > ganze Schraube > Stand", R.drawable.button_activated))
        list.add(Model("Sitz", "Stand > Sitz", R.drawable.button_activated))
        list.add(Model("Halbe Sitz", "Stand > halbe Sitz", R.drawable.button_activated))
        list.add(Model("Ganze Sitz", "Stand > ganze Sitz", R.drawable.button_activated))
        list.add(Model("Hocke in den Sitz", "Stand > Hocke in den Sitz", R.drawable.button_activated))
        list.add(Model("Grätsche in den Sitz", "Stand > Grätsche in den Sitz", R.drawable.button_activated))
        list.add(Model("Rücken", "Stand > Rücken", R.drawable.button_activated))
        list.add(Model("Halbe Rücken", "Stand > halbe Rücken", R.drawable.button_activated))
        list.add(Model("Bauch", "Stand > Bauch", R.drawable.button_activated))
        list.add(Model("Halbe Bauch", "Stand > Bauch", R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Sitz", "", R.drawable.image_noimage))
        list.add(Model("Stand", "Sitz > Stand", R.drawable.button_activated))
        list.add(Model("Halbe Stand", "Sitz > halbe Stand", R.drawable.button_activated))
        list.add(Model("Ganze Stand", "Sitz > ganze Stand", R.drawable.button_activated))
        list.add(Model("Sitz", "Sitz > Sitz", R.drawable.button_activated))
        list.add(Model("Halbe Sitz", "Sitz > halbe Sitz", R.drawable.button_activated))
        list.add(Model("Ganze Sitz", "Sitz > ganze Sitz", R.drawable.button_activated))
        list.add(Model("Rücken", "Sitz > Rücken", R.drawable.button_activated))
        list.add(Model("Halbe Rücken", "Sitz > halbe Rücken", R.drawable.button_activated))
        list.add(Model("Bauch", "Sitz > Bauch", R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Rücken", "", R.drawable.image_noimage))
        list.add(Model("Stand", "Rücken > Stand", R.drawable.button_activated))
        list.add(Model("halbe Stand", "Rücken > halbe Stand", R.drawable.button_activated))
        list.add(Model("ganze Stand", "Rücken > ganze Stand", R.drawable.button_activated))
        list.add(Model("halbe Sitz", "Rücken > halbe Sitz", R.drawable.button_activated))
        list.add(Model("Rücken", "Rücken > Rücken (= Käfer)", R.drawable.button_activated))
        list.add(Model("Muffel", "Rücken > Muffel in den Stand", R.drawable.button_activated))
        list.add(Model("Muffel in den Sitz", "Rücken > Muffel in den Sitz", R.drawable.button_activated))
        list.add(Model("Muffel in den Bauch", "Rücken > Muffel in den Bauch", R.drawable.button_activated))
        list.add(Model("Muffel in den Rücken", "Rücken > Muffel in den Rücken", R.drawable.button_activated))
        list.add(Model("Salto vw. in den Rücken", "Rücken > Salto vw. i.d. Rücken", R.drawable.button_activated))
        list.add(Model("Salto vw. in den Stand", "Rücken > Salto vw. i.d. Stand", R.drawable.button_activated))
        list.add(Model("Bauch", "Rücken > Bauch", R.drawable.button_activated))
        list.add(Model("Wende in den Bauch", "Rücken > Wende in den Bauch", R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Bauch", "", R.drawable.image_noimage))
        list.add(Model("Stand", "Bauch > Stand", R.drawable.button_activated))
        list.add(Model("halbe Stand", "Bauch > halbe Stand", R.drawable.button_activated))
        list.add(Model("Rücken", "Bauch > Rücken", R.drawable.button_activated))
        list.add(Model("Wende in den Rücken", "Bauch > Wende in den Rücken", R.drawable.button_activated))
        list.add(Model("Bauch", "Bauch > Bauch", R.drawable.button_activated))
        list.add(Model("halbe Heli", "Bauch > halbe Heli (= halbe Bauch)", R.drawable.button_activated))
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
        //setSupportActionBar(toolbar)

        firstTime()

        val context: Context = applicationContext
        currentUserName = readFromFile(context,"lastSessionUserName")//sharedUserData.getString(newUserName,userSkill)
        userNames.add(currentUserName) //userNames.add(newUserName)



        /*
        val dateTimeUserSkillSaved = readFromFile(context,"dateTimeUserSkillSaved")
        val dateUserSkillSaved = dateTimeUserSkillSaved.split("|")[0]
        val timeUserSkillSaved = dateTimeUserSkillSaved.split("|")[1]
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        if (dateUserSkillSaved==currentDate)
            if (currentTime[-5].toString()+currentTime[-4]==timeUserSkillSaved[-5].toString()+timeUserSkillSaved[-4]){

            }
            */
        val freshlySavedUserSkill = readFromFile(context,"freshlySavedUserSkill")
        if (freshlySavedUserSkill=="true"){
            UserSettings().writeToFile(context,"false","userSkillFreshlySaved")
            val newUserSkill = readFromFile(context,"lastSessionUserSkill") //sharedUserData.getString(newUserName,userSkill)
            setDeactivatedElementsToSkill(newUserSkill,elementArray)
            println("ÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜÜ - newUserSkill $newUserSkill")
        }




        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (!prefs.getBoolean("firstStart", true)) {


            //startActivity(Intent(this@UserSettings, NewUser::class.java))
            //finish() // Finish the current one, is like forwarding directly to the second one
        }



        switchUser.setOnClickListener { view ->
            //Snackbar.make(view, "In Zukunft lässt sich hier der Username ändern", Snackbar.LENGTH_LONG)
            //.setAction("Action", null).show()
            val jumpToNewUserIntent = Intent(this, NewUser::class.java)
            startActivity(jumpToNewUserIntent)
        }

        // Initializing the array lists and the adapter for the listview of deactivated elements
        //setContentView(R.layout.content_user_settings)
        var listview = findViewById<ListView>(R.id.listView)
        setElementsListObjects()




        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //get the spinner from the xml
        val usersDropdown: Spinner = findViewById(R.id.PickUserDropdown)
        val dropdownAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, userNames)
        usersDropdown.setAdapter(dropdownAdapter)




        // loading the array from file
        //val context: Context = applicationContext
        //val currentUsername = usersDropdown.getSelectedItem()
        println("9999999999 currentUserName: $currentUserName")
        val filenameRead = currentUserName + "_deactivatedElementsFile"
        val loadedStringOfDeactivatedElements = readFromFile(context, filenameRead)
        val loadedStringArray = loadedStringOfDeactivatedElements.split(",").toTypedArray()
        println("**********************LOADED STRING ARRAY $loadedStringArray")
        for (index in loadedStringArray.indices) {
            val element=loadedStringArray[index].replace("\\s".toRegex(),"")
            if (element=="1"||element=="0"){
                elementArray[index] = element.toInt()
            }

        }


        // If no user is in the usersDropdown -> send user to activity NewUser
        //usersDropdown.getAdapter().getCount()==0)
        if (userNames.size == 0) {
            val intent = Intent(this, NewUser::class.java)
            startActivity(intent)
        }
        // deactivating all elements that are saved as deactivated in the elementArray (list elements are initialized as activated above)
        // by setting the new Model - same title & description but different drawable
        for (element in elementArray.indices) {
            var activationOfElement: Int = elementArray[element]//elementArray[element%2]
            var currentListItem = list.get(element)
            if (activationOfElement == 0) {
                list.set(element, Model(currentListItem.title, currentListItem.description, R.drawable.button_dectivated))
            }

        }



        listview.adapter = MyAdapter(this, R.layout.row, list)




        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

            var img: ImageView = view.findViewById(R.id.image0)
            //var textView: TextView = listview.getItemAtPosition(position)

            /*when (position){

            0 -> img = findViewById(R.id.image)
            1 -> img = findViewById(R.id.image0)
            2 -> img = findViewById(R.id.image2)
            3 -> img = findViewById(R.id.image3)
            4 -> img = findViewById(R.id.image4)
            5 -> img = findViewById(R.id.image5)
            6 -> img = findViewById(R.id.image6)
            7 -> img = findViewById(R.id.image7)
            8 -> img = findViewById(R.id.image8)
            9 -> img = findViewById(R.id.image9)
        }*/

            // When the button's state in the array  is 'deactivated' -> set state in arrray to 'activated' and change the drawable
            if (elementArray[position] == 0) {
                img.setImageResource(R.drawable.button_activated)
                //val drawable= ContextCompat.getDrawable(context,button_activated)
                //textView.setCompoundDrawables(@button_activated,null)
                elementArray[position] = 1
                val textView: TextView = view.findViewById(R.id.textView1) as TextView
                var tappedElementName: String = textView.text.toString()
                Toast.makeText(this@UserSettings, "$tappedElementName aktiviert", Toast.LENGTH_LONG).show()
            } else if (elementArray[position] == 1) {
                println("-------------------------- elementId ist in gesperrte oder das letzte Element ist nicht Stand. Hole neues Element")
                img.setImageResource(R.drawable.button_dectivated)
                elementArray[position] = 0
                val textView: TextView = view.findViewById(R.id.textView1) as TextView
                var tappedElementName: String = textView.text.toString()
                Toast.makeText(this@UserSettings, "$tappedElementName deaktiviert", Toast.LENGTH_LONG).show()
                println(elementArray)
            }
            }


            var itemlistAusStand = arrayListOf<String>()
            var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlistAusStand)

            //listView.setOnItemClickListener { adapterView, view, i, l -> android.widget.Toast.makeText(this,"Folgendes element ausgewählt --> "+itemlistAusStand.get(i),android.widget.Toast.LENGTH_SHORT).show()}
            //val position: SparseBooleanArray = listView.checkedItemPositions





            //Saving all user-related files and going back to MainScreen
            back_and_save_user_settings.setOnClickListener {
                val deactivatedElementsArrayString = elementArray.contentToString()
                val currentUserName = usersDropdown.getSelectedItem().toString()
                val deactivatedElementsFilename = currentUserName + "_deactivatedElementsFile"
                println("DeactivatedElementsFilename ist $deactivatedElementsFilename")

                val sharedUserData = getSharedPreferences("currentUser", 0)
                sharedUserData.edit().putString("currentUser", currentUserName).apply()
                writeToFile(context, deactivatedElementsArrayString, deactivatedElementsFilename)
                println("1111111111 Wrote following Array to file: $deactivatedElementsArrayString")

                println("______________________________________WRITING CURRENT USERNAME TO FILE: $currentUserName")
                writeToFile(context,currentUserName,"lastSessionUserName")

                val intent = Intent(this, MainScreen::class.java)
                intent.putExtra("currentUser", currentUserName)
                startActivity(intent)
            }

        fun changeCurrentUserName(name: String){
            currentUserName=name
        }

        }

    }


