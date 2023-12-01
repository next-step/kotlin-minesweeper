package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.MineSweeper

object InputView {

    fun prepareMineSweeper() : MineSweeper{
        val gameBoard = GameBoard(getHeight(), getWidth())
        return MineSweeper(gameBoard, getMine())
    }

    private fun getHeight() : Int{
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    private fun getWidth() : Int{
        println("너비를 입력하세요.")
        return readln().toInt()
    }


    private fun getMine() : Int{
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }


}
