package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var lastDot = false
    var lastNumeric = false
    var lastOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onDigitClicked(view: View) {
        view as Button
        lastNumeric = true
        binding.calcDisplay.append(view.text)

    }

    fun onClearClicked(view: View) {
        if ((view as Button).text.equals("CLR")) {
            binding.calcDisplay.text = ""
        }
        lastDot = false
        lastNumeric = false
    }

    fun onDecimalPointClicked(view: View) {
        view as Button
        if (lastNumeric && !lastDot) {
            binding.calcDisplay.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperatorClicked(view: View) {
        view as Button
        if (lastNumeric && !isOperatorAdded(binding.calcDisplay.text.toString())) {
            binding.calcDisplay.append(view.text)
            lastNumeric = false
            lastDot = false
            lastOperator = true
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("*")
        }
    }

    fun onEqualPressed(view: View) {
        view as Button
        var input: String? = null
        if (lastNumeric) {
            input = binding.calcDisplay.text.toString()
            try {
                if (input.contains("-")) {
                        val splitValue = input.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        binding.calcDisplay.text = (one.toDouble() - two.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                Log.d("EXCEPTION", e.toString())
            }
        }
    }
}