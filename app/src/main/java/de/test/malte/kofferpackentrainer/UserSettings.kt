package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.MeasureSpec
import android.widget.*
import kotlinx.android.synthetic.main.activity_user_settings.*
import kotlinx.android.synthetic.main.nav_header_main_screen.*


class UserSettings : AppCompatActivity() {

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
            "Muffel in den Rücken" to arrayOf(0.5, "Rücken", "Rücken", 1),
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
    public var elementArray: IntArray = intArrayOf(1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1)
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

        list.add(Model("Hocke","Eine normale Hocke", R.drawable.button_activated))
        //Utility.setListViewHeightBasedOnChildren(listView)
        list.add(Model("Bücke","Eine normale Bücke",R.drawable.button_activated))
        list.add(Model("Grätsche","Eine normale Grätsche",R.drawable.button_activated))
        list.add(Model("Halbe Schraube","Eine normale halbe Schraube",R.drawable.button_activated))
        list.add(Model("Halbe Hocke","Eine halbe Schraube mit Hocke dabei",R.drawable.button_activated))
        list.add(Model("Halbe Bücke","Eine halbe Schraube mit Bücke dabei",R.drawable.button_activated))
        list.add(Model("Halbe Grätsche","Eine halbe Schraube mit Grätsche dabei",R.drawable.button_activated))

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
            if (elementArray.get(position) == 0) {
                img.setImageResource(R.drawable.button_activated)
                //val drawable= ContextCompat.getDrawable(context,button_activated)
                //textView.setCompoundDrawables(@button_activated,null)
                elementArray.set(position, 1)
                val textView: TextView = view.findViewById(R.id.textView1) as TextView
                var tappedElementName: String = textView.text.toString()
                Toast.makeText(this@UserSettings, "$tappedElementName aktiviert", Toast.LENGTH_LONG).show()
            } else if (elementArray.get(position) == 1) {
                img.setImageResource(R.drawable.button_dectivated)
                elementArray.set(position, 0)
                val textView: TextView = view.findViewById(R.id.textView1) as TextView
                var tappedElementName: String = textView.text.toString()
                Toast.makeText(this@UserSettings, "$tappedElementName deaktiviert", Toast.LENGTH_LONG).show()
            }
        }


        var itemlistAusStand = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlistAusStand)

        //listView.setOnItemClickListener { adapterView, view, i, l -> android.widget.Toast.makeText(this,"Folgendes element ausgewählt --> "+itemlistAusStand.get(i),android.widget.Toast.LENGTH_SHORT).show()}
        //val position: SparseBooleanArray = listView.checkedItemPositions
    }
}

