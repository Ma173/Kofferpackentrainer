package de.test.malte.kofferpackentrainer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import android.widget.*
//import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import kotlinx.android.synthetic.main.activity_user_settings.*
import java.io.*


class UserSettings : AppCompatActivity() {

    private fun writeToFile(context: Context, data: String, filename: String) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }
    private fun readFromFile(context: Context, filename: String): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput(filename)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String = ""
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
            "Muffel in denSitz" to arrayOf(0.3, "Rücken", "Sitz", 0, 33),
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
    //public var elementArray: IntArray = intArrayOf(1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
    var elementArray = Array(50,{IntArray(1)}) // Array( elementId, {IntArray(activationStatus)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
        //setSupportActionBar(toolbar)
        switchUser.setOnClickListener { view ->
            Snackbar.make(view, "In Zukunft lässt sich hier der Username ändern", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        back_and_save.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        // Initializing the array lists and the adapter
        //setContentView(R.layout.content_user_settings)

        var listview = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<Model>()
        list.add(Model("Sprünge aus dem Stand","", R.drawable.image_noimage))
        list.add(Model("Hocke","Stand > Hocke > Stand", R.drawable.button_activated))
        list.add(Model("Bücke","Stand > Bücke > Stand",R.drawable.button_activated))
        list.add(Model("Grätsche","Stand > Grätsche > Stand",R.drawable.button_activated))
        list.add(Model("Halbe Schraube","Stand > halbe Schraube > Stand",R.drawable.button_activated))
        list.add(Model("Halbe Hocke","Stand > halbe Hocke > Stand",R.drawable.button_activated))
        list.add(Model("Halbe Bücke","Stand > halbe Bücke > Stand",R.drawable.button_activated))
        list.add(Model("Halbe Grätsche","Stand > halbe Grätsche > Stand",R.drawable.button_activated))
        list.add(Model("Ganze Schraube","Stand > ganze Schraube > Stand",R.drawable.button_activated))
        list.add(Model("Sitz","Stand > Sitz",R.drawable.button_activated))
        list.add(Model("Halbe Sitz","Stand > halbe Sitz",R.drawable.button_activated))
        list.add(Model("Ganze Sitz","Stand > ganze Sitz",R.drawable.button_activated))
        list.add(Model("Hocke in den Sitz","Stand > Hocke in den Sitz",R.drawable.button_activated))
        list.add(Model("Grätsche in den Sitz","Stand > Grätsche in den Sitz",R.drawable.button_activated))
        list.add(Model("Rücken","Stand > Rücken",R.drawable.button_activated))
        list.add(Model("Halbe Rücken","Stand > halbe Rücken",R.drawable.button_activated))
        list.add(Model("Bauch","Stand > Bauch",R.drawable.button_activated))
        list.add(Model("Halbe Bauch","Stand > Bauch",R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Sitz","", R.drawable.image_noimage))
        list.add(Model("Stand","Sitz > Stand",R.drawable.button_activated))
        list.add(Model("Halbe Stand","Sitz > halbe Stand",R.drawable.button_activated))
        list.add(Model("Ganze Stand","Sitz > ganze Stand",R.drawable.button_activated))
        list.add(Model("Sitz","Sitz > Sitz",R.drawable.button_activated))
        list.add(Model("Halbe Sitz","Sitz > halbe Sitz",R.drawable.button_activated))
        list.add(Model("Ganze Sitz","Sitz > ganze Sitz",R.drawable.button_activated))
        list.add(Model("Rücken","Sitz > Rücken",R.drawable.button_activated))
        list.add(Model("Halbe Rücken","Sitz > halbe Rücken",R.drawable.button_activated))
        list.add(Model("Bauch","Sitz > Bauch",R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Rücken","", R.drawable.image_noimage))
        list.add(Model("Stand","Rücken > Stand",R.drawable.button_activated))
        list.add(Model("halbe Stand","Rücken > halbe Stand",R.drawable.button_activated))
        list.add(Model("ganze Stand","Rücken > ganze Stand",R.drawable.button_activated))
        list.add(Model("halbe Sitz","Rücken > halbe Sitz",R.drawable.button_activated))
        list.add(Model("Rücken","Rücken > Rücken (= Käfer)",R.drawable.button_activated))
        list.add(Model("Muffel","Rücken > Muffel in den Stand",R.drawable.button_activated))
        list.add(Model("Muffel in den Sitz","Rücken > Muffel in den Sitz",R.drawable.button_activated))
        list.add(Model("Muffel in den Bauch","Rücken > Muffel in den Bauch",R.drawable.button_activated))
        list.add(Model("Muffel in den Rücken","Rücken > Muffel in den Rücken",R.drawable.button_activated))
        list.add(Model("Salto vw. in den Rücken","Rücken > Salto vw. i.d. Rücken",R.drawable.button_activated))
        list.add(Model("Salto vw. in den Stand","Rücken > Salto vw. i.d. Stand",R.drawable.button_activated))
        list.add(Model("Bauch","Rücken > Bauch",R.drawable.button_activated))
        list.add(Model("Wende in den Bauch","Rücken > Wende in den Bauch",R.drawable.button_activated))

        list.add(Model("Sprünge aus dem Bauch","", R.drawable.image_noimage))
        list.add(Model("Stand","Bauch > Stand",R.drawable.button_activated))
        list.add(Model("halbe Stand","Bauch > halbe Stand",R.drawable.button_activated))
        list.add(Model("Rücken","Bauch > Rücken",R.drawable.button_activated))
        list.add(Model("Wende in den Rücken","Bauch > Wende in den Rücken",R.drawable.button_activated))
        list.add(Model("Bauch","Bauch > Bauch",R.drawable.button_activated))
        list.add(Model("halbe Heli","Bauch > halbe Heli (= halbe Bauch)",R.drawable.button_activated))

        // loading the array from file
        val context: Context = applicationContext
        try {
            val loadedStringOfDeactivatedElements=readFromFile(context,"deactivatedElementsFile")
            val loadedStringArray= loadedStringOfDeactivatedElements.split(",").toTypedArray()
            println("**********************LOADED STRING ARRAY $loadedStringArray")
            for (index in loadedStringArray.indices){
                elementArray[index][0]=loadedStringArray[index].toInt()
            }
        }
        catch (e: IOException){

        }



        // deactivating all elements that are saved as deactivated in the elementArray (list elements are initialized as activated above)
        // by setting the new Model - same title & description but different drawable
        for (element in elementArray.indices){
            var activationOfElement: Int = elementArray[element][0]//elementArray[element%2]
            var currentListItem=list.get(element)
            if (activationOfElement==0){
                list.set(element,Model(currentListItem.title,currentListItem.description,R.drawable.button_dectivated))
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
            if (elementArray[position][0] == 0) {
                img.setImageResource(R.drawable.button_activated)
                //val drawable= ContextCompat.getDrawable(context,button_activated)
                //textView.setCompoundDrawables(@button_activated,null)
                elementArray[position][0]=1
                val textView: TextView = view.findViewById(R.id.textView1) as TextView
                var tappedElementName: String = textView.text.toString()
                Toast.makeText(this@UserSettings, "$tappedElementName aktiviert", Toast.LENGTH_LONG).show()
            } else if (elementArray[position][0] == 1) {
                println("-------------------------- elementId ist in gesperrte oder das letzte Element ist nicht Stand. Hole neues Element")
                img.setImageResource(R.drawable.button_dectivated)
                elementArray[position][0]=0
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
        val deactivatedElementsArrayString=elementArray.contentToString()
        writeToFile(context,deactivatedElementsArrayString,"deactivatedElementsFile")
    }
}

