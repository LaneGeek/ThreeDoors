package sekhah.lane.threedoors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var doorNumber: Int

        // Set the listeners for the three door buttons
        door1Button.setOnClickListener {
            doorNumber = 1
            launchSecondActivity(doorNumber)
        }

        door2Button.setOnClickListener {
            doorNumber = 2
            launchSecondActivity(doorNumber)
        }

        door3Button.setOnClickListener {
            doorNumber = 3
            launchSecondActivity(doorNumber)
        }

        // A button to exit the game by finishing the MainActivity. Is this a good idea???
        quitButton.setOnClickListener { finish() }
    }

    private fun launchSecondActivity(doorNumber: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("doorNumber", doorNumber)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val prize = data?.getStringExtra("prize")
        val accepted = data?.getBooleanExtra("accepted", false)
        if (accepted!!)
            textView.text = "You accepted a great prize in the form of a ${prize}. Well done!"
        else
            textView.text = "Why on earth would you reject a ${prize}??? Sad!"
    }
}
