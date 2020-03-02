package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_saved_exercises.*
import kotlinx.android.synthetic.main.activity_saved_exercises.toolbar
import kotlinx.android.synthetic.main.content_saved_exercises.*

class SavedExercises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_exercises)
        //setSupportActionBar(toolbar)

        val extras = intent.extras
        var currentExercise: String? =""
        if (extras != null) {
            currentExercise = extras.getString("currentExerciseElementNames")
        }
        val context=applicationContext
        //savedExercises im Stil Element1,Übung1\nElement2\nElement3\n__________\nElement1, Übung2...
        val savedExercises = UserSettings().readFromFile(context,"savedExercises")
        var savedExercisesList = mutableListOf<String>()
        for (exercise in savedExercises.split("__________\n")){
            if (exercise!="\n") {
                savedExercisesList.add(exercise)
            }
        }
        println("Übung0:"+savedExercisesList[0])

        var list = mutableListOf<Model2>()

        for (exercise in savedExercisesList){
            if (exercise!="\n"){
                println(exercise)
                list.add(Model2(exercise, R.drawable.image_noimage))
            }

        }


        var listview = findViewById<ListView>(R.id.listView)
        listview.adapter = MyAdapter2(this, R.layout.saved_exercises, list)


        /*
        val savedExercise = currentExercise.split(",")
        var exerciseToDisplay = ""
        for (element in savedExercise){
            val firstPart=element.first()
            exerciseToDisplay+="$firstPart\n"
        }
         */
        //placeholderSavedExercises.setText(currentExercise)
        //placeholderSavedExercises.setTextSize(TypedValue.COMPLEX_UNIT_SP,18)

        var selectedListElements = Array(list.size,{0})

        listview.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            var activationStatusOfListElement = selectedListElements[position]
            when (activationStatusOfListElement){
                0 -> {
                    parent.getChildAt(position).setBackgroundColor(Color.GRAY)
                    selectedListElements[position]=1
                }
                1 -> {
                    parent.getChildAt(position).setBackgroundColor(Color.TRANSPARENT)
                    selectedListElements[position]=0
                }
            }

        }

        btn_sendExercise.setOnClickListener { view ->
            Snackbar.make(view, "In Zukunft wird über diesen Button die Übung versendbar sein", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        btn_deleteExercise.setOnClickListener { view ->
            for (index in selectedListElements.indices){
                val elementActivation=selectedListElements[index]
                if (elementActivation==1){
                    list.removeAt(index)
                    savedExercisesList.removeAt(index)
                }
            }
            selectedListElements = Array(list.size,{0})
            listview.adapter = MyAdapter2(this, R.layout.saved_exercises, list)
            Snackbar.make(view, "Markierte Übung(en) gelöscht.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        back_and_save_saved_exercises.setOnClickListener {
            var exercisesStringToSave =""
            for (index in list.indices){
                //val currentView = listView.findViewById<View>(R.id.textView1)//findViewById<View>(index)
                //val textView: TextView = currentView.findViewById(R.id.textView1) as TextView
                //val exercise = textView.text.toString()//listview.getItemAtPosition(index).toString()
                val exercise = savedExercisesList[index]
                println("__EXERCISE:"+exercise)
                exercisesStringToSave+= "__________\n"+exercise
            }
            UserSettings().writeToFile(context,exercisesStringToSave,"savedExercises")
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

    }
}
