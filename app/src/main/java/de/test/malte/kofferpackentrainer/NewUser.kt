package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUser : AppCompatActivity() {

    public var userSkill= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)



        lowSkill.setOnClickListener {
            lowSkill.background.setColorFilter(Color.argb(255,180, 184, 209), PorterDuff.Mode.SRC_ATOP)
            mediumSkill.background.clearColorFilter()
            highSkill.background.clearColorFilter()
            userSkill="low"
        }

        mediumSkill.setOnClickListener {
            lowSkill.background.clearColorFilter()
            mediumSkill.background.setColorFilter(Color.argb(255,180, 184, 209), PorterDuff.Mode.SRC_ATOP)
            highSkill.background.clearColorFilter()
            userSkill="medium"
        }

        highSkill.setOnClickListener {
            lowSkill.background.clearColorFilter()
            mediumSkill.background.clearColorFilter()
            highSkill.background.setColorFilter(Color.argb(255,180, 184, 209), PorterDuff.Mode.SRC_ATOP)
            userSkill="high"
        }



        createNewUser.setOnClickListener {

            val newUserName = usernameEditText.text.toString()
            UserSettings().userNames.add(newUserName)

            Toast.makeText(this, "SpringerIn $newUserName angelegt", Toast.LENGTH_LONG).show()

            val intent = Intent(this, UserSettings::class.java)
            startActivity(intent)
        }



    }
}
