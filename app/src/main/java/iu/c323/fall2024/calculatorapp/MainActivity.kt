package iu.c323.fall2024.calculatorapp
//import statements

import CalculatorViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import iu.c323.fall2024.calculatorapp.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.sin
import kotlin.math.tan

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    //instance variables
    private lateinit var binding: ActivityMainBinding

    private val calcViewModel: CalculatorViewModel by viewModels()

    

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //useful for accesing UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val displayText = calcViewModel.inputNumbers

        binding.textViewDisplay.text = calcViewModel.inputNumbers



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
            calcViewModel.operationCounter++ // Increment the operation counter

          if ( (calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {//first time calculation, preliminary
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }
            // If the calcViewModel.operationCounter is greater than 1, calculate the calcViewModel.result
            else if (calcViewModel.operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }

            //reset variables to default
            calcViewModel.inputNumbers = "0"
            calcViewModel.checkAdd = true //relevant boolean needs to be switched
            calcViewModel.checkMultiply = false
            calcViewModel.checkSubtract = false
            calcViewModel.checkDiv = false
            calcViewModel.checkPercent = false
            calcViewModel.checkEquals = false
        }

        //subtraction listener
        binding.minusButton.setOnClickListener {
            //method is similar to addition process
            calcViewModel.operationCounter++ // Increment the operation counter


           if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }
            // If the calcViewModel.operationCounter is greater than 1, calculate the calcViewModel.result (cumulative calculations)
            else if (calcViewModel.operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }

            calcViewModel.inputNumbers = "0"
            calcViewModel.checkSubtract = true //need to relevant bool as true
            calcViewModel.checkAdd = false
            calcViewModel.checkMultiply = false
            calcViewModel.checkDiv = false
            calcViewModel.checkPercent = false
            calcViewModel.checkEquals = false
        }
        //multiplication listener
        binding.xButton.setOnClickListener {
            //method is similar to addition
            calcViewModel.operationCounter++ // Increment the operation counter


            if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) { //default, preliminary calcViewModel.result
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }

            //needed for cumulative calculation
            else if (calcViewModel.operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }

            calcViewModel.inputNumbers = "0"
            calcViewModel.checkMultiply = true //relevant bool
            calcViewModel.checkSubtract = false
            calcViewModel.checkAdd = false
            calcViewModel.checkPercent = false
            calcViewModel.checkDiv = false
            calcViewModel.checkEquals = false
        }
        //division listener
        binding.divButton.setOnClickListener {
            //method is same as addition listener
            calcViewModel.operationCounter++

            if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            else if (calcViewModel.operationCounter > 1) {
                calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()}

            calcViewModel.inputNumbers = "0"
            calcViewModel.checkSubtract = false
            calcViewModel.checkAdd = false
            calcViewModel.checkMultiply = false
            calcViewModel.checkPercent = false
            calcViewModel.checkDiv = true //relevant bool
            calcViewModel.checkEquals = false
        }
        //////////buttons for project2
        //sin button

        binding.sinButton?.setOnClickListener {
            //method is same as addition listener
            if(calcViewModel.checkEquals){
                calcViewModel.result = sin(Math.toRadians(calcViewModel.result))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){
                calcViewModel.inputNumbers = sin(Math.toRadians(calcViewModel.inputNumbers.toDouble())).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {
                calcViewModel.result = sin(Math.toRadians(calcViewModel.inputNumbers.toDouble()))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }

        //cos button
        binding.cosButton?.setOnClickListener {
            //method is same as addition listener
            if(calcViewModel.checkEquals){
                calcViewModel.result = cos(Math.toRadians(calcViewModel.result))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){
                calcViewModel.inputNumbers = cos(Math.toRadians(calcViewModel.inputNumbers.toDouble())).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {
                calcViewModel.result = cos(Math.toRadians(calcViewModel.inputNumbers.toDouble()))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }

        //tam button
        binding.tanButton?.setOnClickListener {
            //method is same as addition listener
            if(calcViewModel.checkEquals){
                calcViewModel.result = tan(Math.toRadians(calcViewModel.result))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){
                calcViewModel.inputNumbers = tan(Math.toRadians(calcViewModel.inputNumbers.toDouble())).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {
                calcViewModel.result = tan(Math.toRadians(calcViewModel.inputNumbers.toDouble()))
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }

        //natural log
        binding.lnButton?.setOnClickListener {
            //method is same as addition listener
            if(calcViewModel.checkEquals){
                calcViewModel.result = ln(calcViewModel.result)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){
                calcViewModel.inputNumbers = ln(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {
                calcViewModel.result = ln(calcViewModel.inputNumbers.toDouble())
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }


        //log10 button
        binding.logButton?.setOnClickListener {
            //method is same as addition listener
            if(calcViewModel.checkEquals){
                calcViewModel.result = log(calcViewModel.result, 10.0)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){
                calcViewModel.inputNumbers = log(calcViewModel.inputNumbers.toDouble(), 10.0).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {
                calcViewModel.result = log(calcViewModel.inputNumbers.toDouble(), 10.0)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }


        //percent symbol listener, will return value / 100
        binding.percentButton.setOnClickListener{
            if (calcViewModel.checkEquals) { // Handle percentage based on the result from previous operation
                calcViewModel.result /= 100
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.inputNumbers != "0") {
                calcViewModel.inputNumbers = (calcViewModel.inputNumbers.toDouble() / 100).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers
            }

            calcViewModel.checkSubtract = false
            calcViewModel.checkAdd = false
            calcViewModel.checkMultiply = false
            calcViewModel.checkPercent = true //relevant bool
            calcViewModel.checkDiv = false
        }

        //clear button used as reset
        binding.cButton.setOnClickListener {
            //setting ALL values back to default
            binding.textViewDisplay.text = "0"
            calcViewModel.inputNumbers = "0"
            calcViewModel.checkAdd = false
            calcViewModel.checkSubtract = false
            calcViewModel.checkEquals = false
            calcViewModel.checkMultiply = false
            calcViewModel.checkDiv = false
            calcViewModel.checkPercent = false
            calcViewModel.operationCounter = 0
            calcViewModel.result = 0.0
        }

        //equal button listener
        binding.equalsButton.setOnClickListener { view: View ->
           calculations()
            calcViewModel.checkEquals = true //will be set to true since equal button is clicked
           calcViewModel.operationCounter = 0
            calcViewModel.inputNumbers = "0"
        }


        //adding decimal to display and actual value
        binding.decimalButton.setOnClickListener {
            binding.textViewDisplay.text = calcViewModel.inputNumbers + "."
            calcViewModel.inputNumbers += "."
        }

        //makes positive numbers neg and vice versa
        binding.signButton.setOnClickListener {
            //when default with no value, start with - sign alone
            if(calcViewModel.inputNumbers.equals("0") and !calcViewModel.checkEquals){
                binding.textViewDisplay.text = "-"
                calcViewModel.inputNumbers = "-"
            }else if(calcViewModel.checkEquals and calcViewModel.inputNumbers.equals("0")){
                calcViewModel.result = -calcViewModel.result;
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
//            if number is positive, make neg
            else if (calcViewModel.inputNumbers.toDouble() > 0) {
                //adding a neg sign to input
                binding.textViewDisplay.text = "-" + calcViewModel.inputNumbers
                calcViewModel.inputNumbers = "-" + calcViewModel.inputNumbers
                if(calcViewModel.checkEquals){//if cumulative calculation, then edit the result
                    binding.textViewDisplay.text = "-" + calcViewModel.result
                    calcViewModel.inputNumbers = "-" + calcViewModel.result
                }
                //make neg numbers positive
            } else if (calcViewModel.inputNumbers.toDouble() < 0){
                val positive = calcViewModel.result * -1 //update value
                binding.textViewDisplay.text = positive.toString() //change display
                calcViewModel.inputNumbers = positive.toString() //updating input since equal button hasn't been pressed
                if(calcViewModel.checkEquals){//cumulative calculation so edit result
                    val positive = calcViewModel.result * -1
                    binding.textViewDisplay.text = positive.toString()
                    calcViewModel.result = positive
                }
            }
        }
    }


    //function for passing in respective values when a number button is called
    //parameters: String passed in by user when method is called
    private fun buttonFunc(userAnswer : String){
        //if first num being added, display the value but don't add it to default value of "0"
        if (calcViewModel.inputNumbers.equals("0")){
            calcViewModel.inputNumbers = userAnswer
            binding.textViewDisplay.text = calcViewModel.inputNumbers
            //if any operator is pressed or equal button is pressed
        } else if (calcViewModel.checkAdd or calcViewModel.checkEquals or calcViewModel.checkSubtract or calcViewModel.checkMultiply or calcViewModel.checkDiv){
            calcViewModel.inputNumbers += userAnswer //can add to growing string since entered number is not first value
            binding.textViewDisplay.text = calcViewModel.inputNumbers //display
            calcViewModel.checkEquals = false //reset so that calcViewModel.checkEquals can be switched to true if hit again
        }
        else {//used to add numbers even if operator hasn't been clicked
            calcViewModel.inputNumbers += userAnswer
            binding.textViewDisplay.text = calcViewModel.inputNumbers
        }
    }

    private fun calculations(){
        if (calcViewModel.checkAdd) { //addition
            calcViewModel.result += calcViewModel.inputNumbers.toDouble()
            binding.textViewDisplay.text = calcViewModel.result.toString()
        } else if (calcViewModel.checkSubtract) {//subtraction
            calcViewModel.result -= calcViewModel.inputNumbers.toDouble()
            binding.textViewDisplay.text = calcViewModel.result.toString()
        } else if (calcViewModel.checkMultiply) {//multiplication
            calcViewModel.result *= calcViewModel.inputNumbers.toDouble()
            binding.textViewDisplay.text = calcViewModel.result.toString()
        } else if (calcViewModel.checkDiv) {//division
            calcViewModel.result /= calcViewModel.inputNumbers.toDouble()
            binding.textViewDisplay.text = calcViewModel.result.toString()
        }



        //resetting all values to default
        calcViewModel.checkAdd = false
        calcViewModel.checkMultiply = false
        calcViewModel.checkDiv = false
        calcViewModel.checkPercent = false


    }


    override fun onResume() {
        super.onResume()
        if (calcViewModel.checkEquals) {
            binding.textViewDisplay.text = (calcViewModel.result).toString()
        }
        else{
            binding.textViewDisplay.text = calcViewModel.inputNumbers
        }

    }


}