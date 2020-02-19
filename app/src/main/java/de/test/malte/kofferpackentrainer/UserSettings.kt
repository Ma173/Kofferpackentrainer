package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.SparseBooleanArray
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_settings.*
import kotlinx.android.synthetic.main.content_user_settings.*

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
        setContentView(R.layout.content_user_settings)

        var listview = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<Model>()

        list.add(Model("Element1","Element1 description", R.drawable.button_dectivated))
        list.add(Model("Element2","Elememt2 description",R.drawable.button_dectivated))

        listview.adapter = MyAdapter(this, R.layout.row, list)

        listview.setOnItemClickListener{ parent: AdapterView<*>, view: View, position:Int, id: Long ->
            if (position == 0) {
                Toast.makeText(this@UserSettings,"Item $position geklickt", Toast.LENGTH_LONG).show()
            }
            if (position == 1) {
                Toast.makeText(this@UserSettings,"Item ZWEI geklickt", Toast.LENGTH_LONG).show()
            }
            if (position == 2) {
                Toast.makeText(this@UserSettings,"Item DREI geklickt", Toast.LENGTH_LONG).show()
            }

        }


        var itemlistAusStand = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlistAusStand)

        //listView.setOnItemClickListener { adapterView, view, i, l -> android.widget.Toast.makeText(this,"Folgendes element ausgewählt --> "+itemlistAusStand.get(i),android.widget.Toast.LENGTH_SHORT).show()}
        //val position: SparseBooleanArray = listView.checkedItemPositions
    }
}

