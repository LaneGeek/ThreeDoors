package sekhah.lane.threedoors

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Let's get the door number from MainActivity
        val doorNumber = intent.getIntExtra("doorNumber", 0)

        // Get and display message about the prize
        val prize = getPrize()
        textView.text = "You chose door number ${doorNumber}. A wise choice! Your prize is a ${prize}. Well done!"

        acceptButton.text = "Accept ${prize}"
        rejectButton.text = "Reject ${prize}"

        val winner = Intent()
        winner.putExtra("prize", "Goat")
        setResult(Activity.RESULT_OK, winner)

        acceptButton.setOnClickListener {
            endActivity("accepted", prize)
        }

        rejectButton.setOnClickListener {
            endActivity("rejected", prize)
        }
    }

    private fun getPrize(): String {
        val prizes = arrayOf("Toyota Prius", "Healthy Goat", "Smelly Skunk")
        return prizes[Random.nextInt(0, 3)]
    }

    private fun endActivity(decision: String, prize: String) {
        // Return prize and decision back to MainActivity
        val intent = Intent()
        intent.putExtra("prize", prize)
        intent.putExtra("accepted", decision == "accepted")
        setResult(1, intent)
        finish()
    }
}
