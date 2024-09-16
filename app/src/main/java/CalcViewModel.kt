import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
const val KEY_INPUT_NUMBERS = "inputNumbers"
const val KEY_RESULT = "result"
const val KEY_CHECK_ADD = "checkAdd"
const val KEY_CHECK_SUBTRACT = "checkSubtract"
const val KEY_CHECK_MULTIPLY = "checkMultiply"
const val KEY_CHECK_DIV = "checkDiv"
const val KEY_CHECK_EQUALS = "checkEquals"
const val KEY_CHECK_PERCENT = "checkPercent"
const val KEY_OPERATION_COUNTER = "operationCounter"

class CalculatorViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Keys for saving data in SavedStateHandle





    // Getters and setters through SavedStateHandle
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



}


