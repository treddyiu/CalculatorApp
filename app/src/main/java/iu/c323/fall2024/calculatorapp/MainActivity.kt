package iu.c323.fall2024.calculatorapp
//import statements
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iu.c323.fall2024.calculatorapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //instance variables
    private lateinit var binding: ActivityMainBinding
    //variables to hold number and answers
    private var inputNumbers = "0";
    private var result = 0.0;
    //bools to check if button has been clicked
    private var checkAdd = false
    private var checkSubtract = false
    private var checkMultiply = false
    private var checkDiv = false
    private var checkEquals = false
    private var checkPercent = false
    //var to measure if there are sequential operations
    private var operationCounter = 0



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //useful for accesing UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //button listeners to pass in arbitrary value to be used in calculation
        binding.zeroButton.setOnClickListener { view: View ->
            buttonFunc("0")

        }
        binding.oneButton.setOnClickListener { view: View ->
            buttonFunc("1")
        }
        binding.twoButton.setOnClickListener { view: View ->
            buttonFunc("2")
        }
        binding.threeButton.setOnClickListener { view: View ->
            buttonFunc("3")
        }
        binding.fourButton.setOnClickListener { view: View ->
            buttonFunc("4")
        }
        binding.fiveButton.setOnClickListener { view: View ->
            buttonFunc("5")
        }
        binding.sixButton.setOnClickListener { view: View ->
            buttonFunc("6")
        }
        binding.sevenButton.setOnClickListener { view: View ->
            buttonFunc("7")
        }
        binding.eightButton.setOnClickListener { view: View ->
            buttonFunc("8")
        }
        binding.nineButton.setOnClickListener { view: View ->
            buttonFunc("9")
        }

        //method for when addition button is pressed
        binding.plusButton.setOnClickListener { view: View ->
            operationCounter++ // Increment the operation counter

          if ( (operationCounter == 1) and !checkEquals) {//first time calculation, preliminary
                result = inputNumbers.toDouble()
            }
            // If the operationCounter is greater than 1, calculate the result
            else if (operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = result.toString()
            }

            //reset variables to default
            inputNumbers = "0"
            checkAdd = true //relevant boolean needs to be switched
            checkMultiply = false
            checkSubtract = false
            checkDiv = false
            checkPercent = false
            checkEquals = false
        }

        //subtraction listener
        binding.minusButton.setOnClickListener {
            //method is similar to addition process
            operationCounter++ // Increment the operation counter


           if ((operationCounter == 1) and !checkEquals) {
                result = inputNumbers.toDouble()
            }
            // If the operationCounter is greater than 1, calculate the result (cumulative calculations)
            else if (operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = result.toString()
            }

            inputNumbers = "0"
            checkSubtract = true //need to relevant bool as true
            checkAdd = false
            checkMultiply = false
            checkDiv = false
            checkPercent = false
            checkEquals = false
        }
        //multiplication listener
        binding.xButton.setOnClickListener {
            //method is similar to addition
            operationCounter++ // Increment the operation counter


            if ((operationCounter == 1) and !checkEquals) { //default, preliminary result
                result = inputNumbers.toDouble()
            }

            //needed for cumulative calculation
            else if (operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = result.toString()
            }

            inputNumbers = "0"
            checkMultiply = true //relevant bool
            checkSubtract = false
            checkAdd = false
            checkPercent = false
            checkDiv = false
            checkEquals = false
        }
        //division listener
        binding.divButton.setOnClickListener {
            //method is same as addition listener
            operationCounter++

            if ((operationCounter == 1) and !checkEquals) {
                result = inputNumbers.toDouble()
                binding.textViewDisplay.text = result.toString()
            }
            else if (operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = result.toString()}

            inputNumbers = "0"
            checkSubtract = false
            checkAdd = false
            checkMultiply = false
            checkPercent = false
            checkDiv = true //relevant bool
            checkEquals = false
        }

        //percent symbol listener, will return value / 100
        binding.percentButton.setOnClickListener{
                if(inputNumbers.equals("0")) {
                    binding.textViewDisplay.text = (result / 100).toString()
                    result /= 100
                }else{
                    binding.textViewDisplay.text = (inputNumbers.toDouble()/ 100).toString()
                    inputNumbers = (inputNumbers.toDouble()/100).toString()
                }

            checkSubtract = false
            checkAdd = false
            checkMultiply = false
            checkPercent = true //relevant bool
            checkDiv = false
            checkEquals = false
        }

        //clear button used as reset
        binding.cButton.setOnClickListener {
            //setting ALL values back to default
            binding.textViewDisplay.text = "0"
            inputNumbers = "0"
            checkAdd = false
            checkSubtract = false
            checkEquals = false
            checkMultiply = false
            checkDiv = false
            checkPercent = false
            operationCounter = 0
            result = 0.0
        }

        //equal button listener
        binding.equalsButton.setOnClickListener { view: View ->
           calculations()
            checkEquals = true //will be set to true since equal button is clicked
           operationCounter = 0
            inputNumbers = "0"
        }


        //adding decimal to display and actual value
        binding.decimalButton.setOnClickListener {
            binding.textViewDisplay.text = inputNumbers + "."
            inputNumbers += "."
        }

        //makes positive numbers neg and vice versa
        binding.signButton.setOnClickListener {
            operationCounter++
            //when default with no value, start with - sign alone
            if(inputNumbers.equals("0") and !checkEquals){
                binding.textViewDisplay.text = "-"
                inputNumbers = "-"
            }else if(checkEquals and inputNumbers.equals("0")){
                result = -result;
                binding.textViewDisplay.text = result.toString()
            }
            //if number is positive, make neg
            else if (inputNumbers.toDouble() > 0) {
                //adding a neg sign to input
                binding.textViewDisplay.text = "-" + inputNumbers
                inputNumbers = "-" + inputNumbers
                if(checkEquals){//if cumulative calculation, then edit the result
                    binding.textViewDisplay.text = "-" + result
                    inputNumbers = "-" + result
                }

                //make neg numbers positive
            } else if (inputNumbers.toDouble() < 0){
                val positive = result * -1 //update value
                binding.textViewDisplay.text = positive.toString() //change display
                inputNumbers = positive.toString() //updating input since equal button hasn't been pressed
                if(checkEquals){//cumulative calculation so edit result
                    val positive = result * -1
                    binding.textViewDisplay.text = positive.toString()
                    result = positive
                }
            }
        }

    }


    //function for passing in respective values when a number button is called
    //parameters: String passed in by user when method is called
    private fun buttonFunc(userAnswer : String){
        //if first num being added, display the value but don't add it to default value of "0"
        if (inputNumbers.equals("0")){
            inputNumbers = userAnswer
            binding.textViewDisplay.text = inputNumbers
            //if any operator is pressed or equal button is pressed
        } else if (checkAdd or checkEquals or checkSubtract or checkMultiply or checkDiv){
            inputNumbers += userAnswer //can add to growing string since entered number is not first value
            binding.textViewDisplay.text = inputNumbers //display
            checkEquals = false //reset so that checkEquals can be switched to true if hit again
        }
        else {//used to add numbers even if operator hasn't been clicked
            inputNumbers += userAnswer
            binding.textViewDisplay.text = inputNumbers
        }
    }

    private fun calculations(){
        if (checkAdd) { //addition
            result += inputNumbers.toDouble()
            binding.textViewDisplay.text = result.toString()
        } else if (checkSubtract) {//subtraction
            result -= inputNumbers.toDouble()
            binding.textViewDisplay.text = result.toString()
        } else if (checkMultiply) {//multiplication
            result *= inputNumbers.toDouble()
            binding.textViewDisplay.text = result.toString()
        } else if (checkDiv) {//division
            result /= inputNumbers.toDouble()
            binding.textViewDisplay.text = result.toString()
        }


        //resetting all values to default
        checkAdd = false
        checkMultiply = false
        checkDiv = false
        checkPercent = false

    }


}