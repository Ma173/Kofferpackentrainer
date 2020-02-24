package de.test.malte.kofferpackentrainer

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_user.*


class NewUser : AppCompatActivity() {
    public fun getNewUserSkill(): String{
        return userSkill
    }

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
            val newIntent = Intent(this@NewUser, UserSettings::class.java)
            intent.putExtra("newUserName", newUserName)
            startActivity(intent)

            val lengthUserNames=UserSettings().userNames.size
            Toast.makeText(this, "SpringerIn $newUserName angelegt.", Toast.LENGTH_LONG).show() //"Anzahl gespeicherte User: $lengthUserNames"

            val sharedUserData = getSharedPreferences(newUserName, 0)
            sharedUserData.edit().putString(newUserName,userSkill).apply()
            //Toast.makeText(this, "Speichere für User $newUserName den Skill $userSkill", Toast.LENGTH_LONG).show() //"Anzahl gespeicherte User: $lengthUserNames"

            val intent = Intent(this, UserSettings::class.java)
            startActivity(intent)
        }



    }
}
