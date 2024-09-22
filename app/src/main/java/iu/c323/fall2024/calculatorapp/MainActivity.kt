package iu.c323.fall2024.calculatorapp
//import statements
import CalculatorViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

//used for logging reference purposes
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    //instance variables
    private lateinit var binding: ActivityMainBinding

    private val calcViewModel: CalculatorViewModel by viewModels() //making reference to ViewModel that will help maintain orientation



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //useful for accesing UI
        binding = ActivityMainBinding.inflate(layoutInflater) //converts XML file to Kotlin Objects
        setContentView(binding.root)


        binding.textViewDisplay.text = calcViewModel.inputNumbers //replace all variables with variable references from ViewModel
        //sets Display to default input value



        //button listeners to pass in arbitrary value to be used in calculation
        binding.zeroButton.setOnClickListener { view: View ->
            buttonFunc("0")
            Log.d(TAG, "Zero Button is clicked")//logging
        }
        binding.oneButton.setOnClickListener { view: View ->
            buttonFunc("1")
            Log.d(TAG, "One Button is clicked")//logging
        }
        binding.twoButton.setOnClickListener { view: View ->
            buttonFunc("2")
            Log.d(TAG, "Two Button is clicked")//logging
        }
        binding.threeButton.setOnClickListener { view: View ->
            buttonFunc("3")
            Log.d(TAG, "Three Button is clicked")//logging
        }
        binding.fourButton.setOnClickListener { view: View ->
            buttonFunc("4")
            Log.d(TAG, "Four Button is clicked")//logging
        }
        binding.fiveButton.setOnClickListener { view: View ->
            buttonFunc("5")
            Log.d(TAG, "Five Button is clicked")///logging
        }
        binding.sixButton.setOnClickListener { view: View ->
            buttonFunc("6")
            Log.d(TAG, "Six Button is clicked")//logging
        }
        binding.sevenButton.setOnClickListener { view: View ->
            buttonFunc("7")
            Log.d(TAG, "Seven Button is clicked")//logging
        }
        binding.eightButton.setOnClickListener { view: View ->
            buttonFunc("8")
            Log.d(TAG, "Eight Button is clicked")//logging
        }
        binding.nineButton.setOnClickListener { view: View ->
            buttonFunc("9")
            Log.d(TAG, "Nine Button is clicked")//logging
        }

        //method for when addition button is pressed
        binding.plusButton.setOnClickListener { view: View ->
            Log.d(TAG, "Plus Button is clicked")
            calcViewModel.operationCounter++ // Increment the operation counter

          if ( (calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {//first time calculation, preliminary and before equal button is pressed
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }
            // If the calcViewModel.operationCounter is greater than 1, calculate the result upon next Button click
            else if (calcViewModel.operationCounter > 1) {
                calcViewModel.calculations() //call function that will calculate and provide result while accounting for previous operations
                binding.textViewDisplay.text = calcViewModel.result.toString()
                binding.textViewDisplay.text = calcViewModel.result.toString() //display result
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
            Log.d(TAG, "Minus Button is clicked")
            calcViewModel.operationCounter++ // Increment the operation counter


           if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }
            // If the calcViewModel.operationCounter is greater than 1, calculate the result
            else if (calcViewModel.operationCounter > 1) {
                calcViewModel.calculations() //call method that will calculate + store result
               binding.textViewDisplay.text = calcViewModel.result.toString()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }

            //reset
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
            Log.d(TAG, "Multiplication Button is clicked")
            calcViewModel.operationCounter++ // Increment the operation counter


            if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) { //default, preliminary calcViewModel.result
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
            }

            //needed for cumulative calculation
            else if (calcViewModel.operationCounter > 1) {
                calcViewModel.calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }

            //reset
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
            Log.d(TAG, "Division Button is clicked")
            //method is same as addition listener
            calcViewModel.operationCounter++

            if ((calcViewModel.operationCounter == 1) and !calcViewModel.checkEquals) {//preliminary calculation
                calcViewModel.result = calcViewModel.inputNumbers.toDouble()
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            else if (calcViewModel.operationCounter > 1) {//more than 1 operation being done
                calcViewModel.calculations()
                binding.textViewDisplay.text = calcViewModel.result.toString()
                binding.textViewDisplay.text = calcViewModel.result.toString()}

            calcViewModel.inputNumbers = "0"
            calcViewModel.checkSubtract = false
            calcViewModel.checkAdd = false
            calcViewModel.checkMultiply = false
            calcViewModel.checkPercent = false
            calcViewModel.checkDiv = true //relevant bool
            calcViewModel.checkEquals = false
        }

        //////////buttons for project2/////////
        //sin button, returns sin
        binding.sinButton?.setOnClickListener {
            //calculate results directly within conditionals to show result instantly
            Log.d(TAG, "Sin Button is clicked") //logging
            if(calcViewModel.checkEquals){//if equal button is pressed
                calcViewModel.result = calcViewModel.sinFunc(calcViewModel.result) //use prev result to get new
                binding.textViewDisplay.text = calcViewModel.result.toString() //display
            } else if (calcViewModel.operationCounter >= 1){ //if there has been more than 1 operation, use latest input and give as new input
                calcViewModel.inputNumbers = calcViewModel.sinFunc(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString() //display
            }
            else {//preliminary calculation and before any other operation has been conducted
                calcViewModel.result = calcViewModel.sinFunc(calcViewModel.inputNumbers.toDouble()) //use given input to calculate result
                binding.textViewDisplay.text = calcViewModel.result.toString() //display
            }
            calcViewModel.operationCounter++ //increment operationCounter to keep track of if button has been clicked
        }

        //cos button, returns cosine
        binding.cosButton?.setOnClickListener {
            Log.d(TAG, "Cos Button is clicked") //logging
            //algorithm is same as sin button
            if(calcViewModel.checkEquals){ //equals condition
                calcViewModel.result = calcViewModel.cosFunc(calcViewModel.result)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){ //sequential operations
                calcViewModel.inputNumbers = calcViewModel.cosFunc(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {//standard calculation
                calcViewModel.result = calcViewModel.cosFunc(calcViewModel.inputNumbers.toDouble())
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }

        //tan button, returns tangent value
        binding.tanButton?.setOnClickListener {
            //same algorithm as sin button
            Log.d(TAG, "Tan Button is clicked")//logging
            if(calcViewModel.checkEquals){//equals condition
                calcViewModel.result = calcViewModel.tanFunc(calcViewModel.result)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){//sequential operations
                calcViewModel.inputNumbers = calcViewModel.tanFunc(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {//standard operation
                calcViewModel.result = calcViewModel.tanFunc(calcViewModel.inputNumbers.toDouble())
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++ //increment
        }

        //natural log value
        binding.lnButton?.setOnClickListener {
            //algorithm is same as sin button
            Log.d(TAG, "Natural log Button is clicked") //logging
            if(calcViewModel.checkEquals){//equals condition
                calcViewModel.result = calcViewModel.lnFunc(calcViewModel.result)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){//sequential operations
                calcViewModel.inputNumbers = calcViewModel.lnFunc(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {//standard operation
                calcViewModel.result = calcViewModel.lnFunc(calcViewModel.inputNumbers.toDouble())
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++ //increment
        }


        //log10 button, will return log value with base 10
        binding.logButton?.setOnClickListener {
            //method is same as sin button
            Log.d(TAG, "Log Button is clicked")
            if(calcViewModel.checkEquals){//equals condition
                calcViewModel.result = calcViewModel.logFunc(calcViewModel.result)
                binding.textViewDisplay.text = calcViewModel.result.toString()
            } else if (calcViewModel.operationCounter >= 1){//sequential operations
                calcViewModel.inputNumbers = calcViewModel.logFunc(calcViewModel.inputNumbers.toDouble()).toString()
                binding.textViewDisplay.text = calcViewModel.inputNumbers.toString()
            }
            else {//standard calculation
                calcViewModel.result = calcViewModel.logFunc(calcViewModel.inputNumbers.toDouble())
                binding.textViewDisplay.text = calcViewModel.result.toString()
            }
            calcViewModel.operationCounter++
        }


        //percent symbol listener, will return value / 100
        binding.percentButton.setOnClickListener{
            Log.d(TAG, "Percent Button is clicked")
            if (calcViewModel.checkEquals) { // Handle percentage based on the result from previous operation if equal button is clicked
                calcViewModel.result = calcViewModel.percentFunc(calcViewModel.result) //need result to be shown as soon as button is clocked
                binding.textViewDisplay.text = calcViewModel.result.toString() //display
            } else if (calcViewModel.inputNumbers != "0") {
                calcViewModel.inputNumbers = calcViewModel.percentFunc(calcViewModel.inputNumbers.toDouble()).toString()
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
            Log.d(TAG, "Clear Button is clicked") //logging
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
            Log.d(TAG, "Equals Button is clicked")
           calcViewModel.calculations() //when equals is pressed, necessary bool will finish the calculation
            binding.textViewDisplay.text = calcViewModel.result.toString()
            calcViewModel.checkEquals = true //will be set to true since equal button is clicked
            //reset operation counter and inputs since equals gives final result
           calcViewModel.operationCounter = 0
            calcViewModel.inputNumbers = "0"
        }


        //adding decimal to display and actual value
        binding.decimalButton.setOnClickListener {
            Log.d(TAG, "Decimal Button is clicked")
            binding.textViewDisplay.text = calcViewModel.inputNumbers + "."
            calcViewModel.inputNumbers += "."
        }

        //makes positive numbers neg and vice versa
        binding.signButton.setOnClickListener {
            //when default with no value, start with - sign alone
            Log.d(TAG, "Pos/Neg Button is clicked")
            if(calcViewModel.inputNumbers.equals("0") and !calcViewModel.checkEquals){//show negative sign if neg button is clicked before entering number
                binding.textViewDisplay.text = "-"
                calcViewModel.inputNumbers = "-"
            }else if(calcViewModel.checkEquals and calcViewModel.inputNumbers.equals("0")){//if equal button has been pressed and reset is needed, use result in order to build off of prev calculayion
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



    override fun onResume() {//when orientation is turned, display still needs to be updated upon resuming (even tho Active objects are not destroyed)
        super.onResume()
        if (calcViewModel.checkEquals) {//if equal button has been pressed, last display should be the final result when orientation is changed
            binding.textViewDisplay.text = (calcViewModel.result).toString()
        }
        else{//otherwise, use input for the display
            binding.textViewDisplay.text = calcViewModel.inputNumbers
        }

    }


}

