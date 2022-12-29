package view

import dto.MineInputDto
import dto.OpenCoordinate
import util.Parser

object InputView {
    private const val INPUT_HEIGHT = "높이를 입력하세요."
    private const val INPUT_WIDTH = "너비를 입력하세요."
    private const val INPUT_MINE_CNT = "지뢰는 몇 개인가요?"
    private const val INVALID_OPEN_COORDINATE = "좌표를 올바르게 입력해주세요."
    private const val INVALID_HEIGHT = "높이는 1부터 최대 높이까지 가능해요."
    private const val INVALID_WIDTH = "너비는 1부터 최대 높이까지 가능해요."
    private const val COORDINATE_SIZE = 2
    private const val COORDINATE_MIN_VALUE = 1
    private const val COORDINATE_THRESHOLD = 1

    fun readMineSweeperInput(): MineInputDto {
        println(INPUT_HEIGHT)
        val height = readInt()
        println()
        println(INPUT_WIDTH)
        val width = readInt()
        println()
        println(INPUT_MINE_CNT)
        val mineCnt = readInt()
        println()
        return MineInputDto(height, width, mineCnt)
    }

    fun readOpenCoordinate(height: Int, width: Int): OpenCoordinate {
        println()
        print("open: ")
        val input = readln()
        val openCoordinate = Parser.parse(input).apply { validateOpenCoordinate(height, width, this) }
        return OpenCoordinate(openCoordinate[0] - COORDINATE_THRESHOLD, openCoordinate[1] - COORDINATE_THRESHOLD)
    }

    private fun validateOpenCoordinate(height: Int, width: Int, openCoordinate: List<Int>) {
        require(openCoordinate.size == COORDINATE_SIZE) { INVALID_OPEN_COORDINATE }
        require(openCoordinate[0] in COORDINATE_MIN_VALUE..height) { INVALID_HEIGHT }
        require(openCoordinate[1] in COORDINATE_MIN_VALUE..width) { INVALID_WIDTH }
    }

    private fun readInt(): Int {
        return readln().toInt()
    }
}
