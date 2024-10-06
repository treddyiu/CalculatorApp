import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.sin
import kotlin.math.tan

//used to preserve orientation changes

//make keys to pass in for set functions
const val KEY_INPUT_NUMBERS = "inputNumbers"
const val KEY_RESULT = "result"
const val KEY_CHECK_ADD = "checkAdd"
const val KEY_CHECK_SUBTRACT = "checkSubtract"
const val KEY_CHECK_MULTIPLY = "checkMultiply"
const val KEY_CHECK_DIV = "checkDiv"
const val KEY_CHECK_EQUALS = "checkEquals"
const val KEY_CHECK_PERCENT = "checkPercent"
const val KEY_OPERATION_COUNTER = "operationCounter"

//using savedStateHandle data to save and use data through process data
class CalculatorViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Getters and setters through SavedStateHandle
    //made for each instance variable and set to default value in getter function
    var inputNumbers: String
        get() = savedStateHandle.get(KEY_INPUT_NUMBERS) ?: "0"
        set(value) = savedStateHandle.set(KEY_INPUT_NUMBERS, value)

    var result: Double
        get() = savedStateHandle.get(KEY_RESULT) ?: 0.0
        set(value) = savedStateHandle.set(KEY_RESULT, value)

    var checkAdd: Boolean
        get() = savedStateHandle.get(KEY_CHECK_ADD) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_ADD, value)

    var checkSubtract: Boolean
        get() = savedStateHandle.get(KEY_CHECK_SUBTRACT) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_SUBTRACT, value)

    var checkMultiply: Boolean
        get() = savedStateHandle.get(KEY_CHECK_MULTIPLY) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_MULTIPLY, value)

    var checkDiv: Boolean
        get() = savedStateHandle.get(KEY_CHECK_DIV) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_DIV, value)

    var checkEquals: Boolean
        get() = savedStateHandle.get(KEY_CHECK_EQUALS) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_EQUALS, value)

    var checkPercent: Boolean
        get() = savedStateHandle.get(KEY_CHECK_PERCENT) ?: false
        set(value) = savedStateHandle.set(KEY_CHECK_PERCENT, value)

    var operationCounter: Int
        get() = savedStateHandle.get(KEY_OPERATION_COUNTER) ?: 0
        set(value) = savedStateHandle.set(KEY_OPERATION_COUNTER, value)



    fun calculations(){//function that checks for specific booleans and when true, calculates and displays result accordingly
        if (checkAdd) { //addition
            result += inputNumbers.toDouble()
        } else if (checkSubtract) {//subtraction
            result -= inputNumbers.toDouble()
        } else if (checkMultiply) {//multiplication
            result *= inputNumbers.toDouble()
        } else if (checkDiv) {//division
            result /= inputNumbers.toDouble()
        }

        //resetting all values to default
        checkAdd = false
        checkMultiply = false
        checkDiv = false
        checkPercent = false

    }

    fun cosFunc(double: Double): Double {
        return cos(Math.toRadians(double))
    }

    fun sinFunc(double: Double): Double{
        return sin(Math.toRadians(double))
    }

    fun tanFunc(double: Double):Double{
        return tan(Math.toRadians(double))
    }

    fun logFunc(double: Double):Double{
        return log(double, 10.0)
    }

    fun lnFunc(double: Double):Double{
        return ln(double)
    }

    fun percentFunc(double: Double):Double{
        return double/100
    }


}


