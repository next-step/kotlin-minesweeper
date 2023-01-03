package minesweeper.view

import minesweeper.controller.dto.BuildMapRequest
import minesweeper.controller.dto.OpenCordRequest

class InputView {

    fun enterMapRequest(): BuildMapRequest {
        val height = enterHeight()

        val width = enterWidth()

        val mineCount = enterMineCount()

        return BuildMapRequest(height, width, mineCount)
    }

    private fun enterHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    private fun enterWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    private fun enterMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun enterCord(): OpenCordRequest {
        print("open: ")
        return OpenCordRequest(readln())
    }
}
