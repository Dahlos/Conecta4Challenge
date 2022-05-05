package com.dahlos.conecta4challenge.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.dahlos.conecta4challenge.GameManager
import com.dahlos.conecta4challenge.R
import com.dahlos.conecta4challenge.databinding.ActivityMainBinding
import com.dahlos.conecta4challenge.utils.Players.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPlayer = PLAYER_1
    private lateinit var GameManager: GameManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GameManager = GameManager(7, 6, 4)
        generateGridButtons(7, 6)
    }


    private fun generateGridButtons(numberOfColumns: Int, numberOfRows: Int) {
        Log.v("COLUMNS ROWS", numberOfColumns.toString() + numberOfRows.toString())
        // matriz of buttons
        val gridButtons = Array(numberOfColumns) { arrayOfNulls<Button>(numberOfRows) }
        // create buttons
        for (i in 0 until numberOfColumns) {
            for (j in 0 until numberOfRows) {

                val button = Button(this)

                button.text = "$i $j"
                button.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(j)
                    columnSpec = GridLayout.spec(i)
                    width = 160
                    height = 160
                    bottomMargin = 10
                    topMargin = 10
                    leftMargin = 10
                    rightMargin = 10
                }

                button.setOnClickListener {
                    if (!GameManager.isValidateMove(i, j)) return@setOnClickListener
                    val indexMove = GameManager.getRowValidMove(i, currentPlayer)
                    val buttonC = gridButtons[i][indexMove]
                    when (currentPlayer) {
                        PLAYER_1 -> {
                            buttonC?.setBackgroundColor(getColor(R.color.red))
                            currentPlayer = PLAYER_2
                            binding.tvCurrentPlayer.text = "Player 2"
                        }
                        PLAYER_2 -> {
                            buttonC?.setBackgroundColor(getColor(R.color.yellow))
                            currentPlayer = PLAYER_1
                            binding.tvCurrentPlayer.text = "Player 1"
                        }
                    }
                }

                gridButtons[i][j] = button
                binding.gridContainer.addView(button)
            }
        }
    }
}