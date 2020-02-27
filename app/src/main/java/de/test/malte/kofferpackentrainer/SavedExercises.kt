package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import kotlinx.android.synthetic.main.activity_saved_exercises.*
import kotlinx.android.synthetic.main.activity_saved_exercises.toolbar
import kotlinx.android.synthetic.main.app_bar_main_screen.*
import kotlinx.android.synthetic.main.content_saved_exercises.*

class SavedExercises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_exercises)
        setSupportActionBar(toolbar)

        val extras = intent.extras
        var currentExercise: String =""
        if (extras != null) {
            currentExercise = extras.getString("currentExercise")
        }
        /*
        val savedExercise = currentExercise.split(",")
        var exerciseToDisplay = ""
        for (element in savedExercise){
            val firstPart=element.first()
            exerciseToDisplay+="$firstPart\n"
        }
         */
        placeholderSavedExercises.setText(currentExercise)
        //placeholderSavedExercises.setTextSize(TypedValue.COMPLEX_UNIT_SP,18)

        sendExercise.setOnClickListener { view ->
            Snackbar.make(view, "In Zukunft wird über diesen Button die Übung versendbar sein", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        back_and_save_savedExercises.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

    }
}
