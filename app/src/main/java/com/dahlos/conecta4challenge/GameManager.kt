package com.dahlos.conecta4challenge

import android.util.Log
import com.dahlos.conecta4challenge.utils.Players

class GameManager(
    private val numberOfColumns: Int,
    private val numberOfRows: Int,
    private val numberOfConnectToWin: Int
) {

    private var gridButtons = Array(numberOfColumns) { arrayOfNulls<Players>(numberOfRows) }

    fun isValidateMove(column: Int, row: Int): Boolean {
        return gridButtons[column].contains(null)
    }

    fun getRowValidMove(column: Int, player: Players): Int {
        val rowIndex = gridButtons[column].indexOfLast { it == null }
        gridButtons[column][rowIndex] = player
        isWinMove(column, rowIndex, player)
        return rowIndex
    }

    fun isWinMove(column: Int, row: Int, player: Players): Boolean {
        val index = gridButtons[column][row] ?: return false
        val horizontal = getHorizontal(column, row, player)
        Log.v("horizontal", horizontal.toString())
//        val vertical = getVertical(column, row, player)
//        val diagonal = getDiagonal(column, row, player)
//        return horizontal || vertical || diagonal
        return horizontal
    }

    private fun getHorizontal(column: Int, row: Int, player: Players): Boolean {

        // Check horizontal right
        for (i in 0 until numberOfConnectToWin) {
            if (column + i >= numberOfColumns) {
                return false
            }
            if (gridButtons[column + i][row] != player) {
                return false
            }
        }

        // Check horizontal left
        for (i in numberOfConnectToWin until 0) {
            if (column - i < 0) {
                return false
            }
            if (gridButtons[column - i][row] != player) {
                return false
            }
        }

        return true
    }

    private fun getVertical(column: Int, row: Int, player: Players): Boolean {
        TODO("Not yet implemented")
    }

    private fun getDiagonal(column: Int, row: Int, player: Players): Boolean {
        TODO("Not yet implemented")
    }

}

