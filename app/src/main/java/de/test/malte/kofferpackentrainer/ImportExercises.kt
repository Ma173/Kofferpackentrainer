package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_import_exercises.*
import kotlinx.android.synthetic.main.content_import_exercises.*

class ImportExercises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_exercises)
        setSupportActionBar(toolbar)
        exercisesEditText.setHint(R.string.importGreeting)

        saveImport.setOnClickListener { view ->
            var loadedSavedExercisesAsString=UserSettings().readFromFile(applicationContext,"savedExercises")
            var allSavedExercises=loadedSavedExercisesAsString.substring(3)
            allSavedExercises+= "__________\n"+exercisesEditText.text.toString()
            UserSettings().writeToFile(applicationContext,allSavedExercises,"savedExercises")
            Snackbar.make(view, "Übungen importiert", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val intent = Intent(this, SavedExercises::class.java)
            startActivity(intent)
        }

        exercisesEditText.setOnClickListener {view ->
            /*val importGreeting = getString(R.string.importGreeting)
            if (exercisesEditText.text.toString() == importGreeting) {//text=findViewById)
                exercisesEditText.setText("")
            }*/
        }

        back_and_save_import_exercises.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }


    }

}
