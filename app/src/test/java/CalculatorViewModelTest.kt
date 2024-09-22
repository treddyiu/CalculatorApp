import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import iu.c323.fall2024.calculatorapp.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest{
    private lateinit var viewModel:CalculatorViewModel

    @Before
    fun setUp(){
       val savedStateHandle = SavedStateHandle()
       viewModel = CalculatorViewModel(savedStateHandle)
    }

    @Test //test1 checking for addition operation
    fun CheckAdd(){
        viewModel.result = 69.0
        viewModel.inputNumbers = "52"
        viewModel.checkAdd = true
        viewModel.calculations()
        assertEquals("69 + 52 = 121", 121.0, viewModel.result, 0.0001)
    }

    @Test //test2 checking for subtraction operation
    fun CheckSubtract(){
        viewModel.result = 5.0
        viewModel.inputNumbers = "25"
        viewModel.checkSubtract = true
        viewModel.calculations()
        assertEquals("5 - 25 = -20", -20.0,viewModel.result, 0.0001)
    }
    @Test
    fun CheckMultiply(){//test3 checking for multiplication operation
        viewModel.result = -15.0
        viewModel.inputNumbers = "2"
        viewModel.checkMultiply = true
        viewModel.calculations()
        assertEquals("-15 * 2 = -30", -30.0, viewModel.result, 0.0001)
    }

    @Test
    fun CheckDivision(){//test4 checking for division operation
        viewModel.result = 5.0
        viewModel.inputNumbers = "4"
        viewModel.checkDiv = true
        viewModel.calculations()
        assertEquals("5/4 = 1.25", 1.25, viewModel.result, 0.0001)
    }
    @Test
    fun CheckTan(){//test5 checking for tan operation
        viewModel.inputNumbers = "24"
        val x = viewModel.tanFunc(viewModel.inputNumbers.toDouble())
        assertEquals("tan of 24 is 0.04452", 0.4452, x, 0.0001)
    }

    @Test
    fun CheckSin(){//test6 checking for sin operation
        viewModel.result = 4.0
        viewModel.inputNumbers = "3"
        viewModel.checkAdd = true
        viewModel.calculations()
        //value of result is now 7
        viewModel.result = viewModel.sinFunc(viewModel.result)
        assertEquals("4+3 = 7; sin(7) = 0.1219", 0.1219,viewModel.result, 0.0001)
    }

    @Test
    fun CheckCos(){//test7 checking for cos operation
        viewModel.result = 2.0
        viewModel.inputNumbers = "4"
        viewModel.checkDiv = true
        viewModel.calculations()
        //value of result is now 0.5
        viewModel.result = viewModel.cosFunc(viewModel.result)
        assertEquals("2/4 = 0.5; cos(0.5) = 0.99996", 0.99996,viewModel.result, 0.0001)
    }

    @Test
    fun CheckLn(){//test8 checking for natural log operation
        viewModel.inputNumbers = "7"
        viewModel.result = viewModel.lnFunc(viewModel.inputNumbers.toDouble())
        assertEquals("ln(7) = 1.9459", 1.9459, viewModel.result, 0.0001)
    }

    @Test
    fun CheckLog(){//test9 checking for log base 10 operation
        viewModel.inputNumbers = "50"
        viewModel.result = viewModel.logFunc(viewModel.inputNumbers.toDouble())
        assertEquals("log(50) = 1.6990", 1.6990, viewModel.result, 0.0001)
    }

    @Test
    fun CheckPercent(){//test10 checking for percent button operation functionality
        viewModel.inputNumbers = "75"
        viewModel.result = viewModel.percentFunc(viewModel.inputNumbers.toDouble())
        assertEquals("75/100", 0.75, viewModel.result, 0.0001)
    }


}