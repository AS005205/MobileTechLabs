package by.bstu.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var listOfPairs = mutableListOf("1","2","3","4","5","6","7","8","1","2","3","4","5","6","7","8")
    private val handler = Handler()
    var iter = 0
    companion object {var tempId = R.id.buttonNewGame}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun <String> shuffle(list: MutableList<String>){
        list.shuffle()
    }

    private fun init(){
        shuffle(listOfPairs)
        iter = 0
        val button1 : Button = findViewById(R.id.button2)
        button1.tag = listOfPairs[0]
        val button2 : Button = findViewById(R.id.button3)
        button2.tag = listOfPairs[1]
        val button3 : Button = findViewById(R.id.button4)
        button3.tag = listOfPairs[2]
        val button4 : Button = findViewById(R.id.button5)
        button4.tag = listOfPairs[3]
        val button5 : Button = findViewById(R.id.button6)
        button5.tag = listOfPairs[4]
        val button6 : Button = findViewById(R.id.button7)
        button6.tag = listOfPairs[5]
        val button7 : Button = findViewById(R.id.button8)
        button7.tag = listOfPairs[6]
        val button8 : Button = findViewById(R.id.button9)
        button8.tag = listOfPairs[7]
        val button9 : Button = findViewById(R.id.button10)
        button9.tag = listOfPairs[8]
        val button10 : Button = findViewById(R.id.button11)
        button10.tag = listOfPairs[9]
        val button11 : Button = findViewById(R.id.button12)
        button11.tag = listOfPairs[10]
        val button12 : Button = findViewById(R.id.button13)
        button12.tag = listOfPairs[11]
        val button13 : Button = findViewById(R.id.button14)
        button13.tag = listOfPairs[12]
        val button14 : Button = findViewById(R.id.button15)
        button14.tag = listOfPairs[13]
        val button15 : Button = findViewById(R.id.button16)
        button15.tag = listOfPairs[14]
        val button16 : Button = findViewById(R.id.button17)
        button16.tag = listOfPairs[15]
    }

    fun onNewGameClick(view: View) {
        setContentView(R.layout.activity_main)
        init();
        tempId = R.id.buttonNewGame


    }

    private fun compare(LastButton: Button, CurrentButton: Button, view: View) {
        if ( (LastButton.tag == CurrentButton.tag) && tempId != view.id )
        {
            handler.postDelayed({
                CurrentButton.visibility = View.INVISIBLE
                LastButton.visibility = View.INVISIBLE
            }, 500)
            iter++
            if(iter == 8) textView.text = "Win!"
            return
        }

        if ( tempId != R.id.buttonNewGame && tempId != view.id ) {
            handler.postDelayed({LastButton.text = ""},200)
        }

        tempId = view.id
    }

    fun onClickButton(view: View) {
        val lastButton: Button = findViewById(tempId)
        val currentButton: Button = findViewById(view.id)

        when(view.tag) {
            "1" -> {
                currentButton.text = "1"
                compare(lastButton, currentButton, view)
            }
            "2" -> {
                currentButton.text = "2"
                compare(lastButton, currentButton, view)
            }
            "3" -> {
                currentButton.text = "3"
                compare(lastButton, currentButton, view)
            }
            "4" -> {
                currentButton.text = "4"
                compare(lastButton, currentButton, view)
            }
            "5" -> {
                currentButton.text = "5"
                compare(lastButton, currentButton, view)
            }
            "6" -> {
                currentButton.text = "6"
                compare(lastButton, currentButton, view)
            }
            "7" -> {
                currentButton.text = "7"
                compare(lastButton, currentButton, view)
            }
            "8" -> {
                currentButton.text = "8"
                compare(lastButton, currentButton, view)
            }
        }

    }

}