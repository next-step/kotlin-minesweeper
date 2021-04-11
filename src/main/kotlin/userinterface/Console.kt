package userinterface

import dto.BlockDto
import dto.MineBoardDto
import dto.MineSweeperInitDto

object Console : UserInterface {

    override fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto {
        val height = inputNaturalNumber("높이를 입력하세요.")
        val width = inputNaturalNumber("너비를 입력하세요.")
        val mineCount = inputMineCount("지뢰는 몇 개인가요?", height * width)

        return (MineSweeperInitDto(height = height, width = width, mineCount = mineCount))
    }

    override fun outputMineSweeper(mineBoardDto: MineBoardDto) {
        mineBoardDto.board.windowed(size = mineBoardDto.width, step = mineBoardDto.width)
            .map { row -> row.joinToString(separator = " ") { it.toView() } }
            .forEach(::println)
    }

    override fun inputCheckCoordinate(): Pair<Int, Int> {
        print("open: ")
        val input = readLine()
            ?.split(",")
            ?.map { it.trim() }
            ?.mapNotNull { it.toIntOrNull() }
            ?: throw RuntimeException()

        return if (input.size == 2) (input[0] to input[1]) else inputCheckCoordinate()
    }

    private tailrec fun inputMineCount(message: String, maximumMineCount: Int): Int {
        val mineCount = inputNaturalNumber(message)
        return if (mineCount <= maximumMineCount) mineCount else inputMineCount(message, maximumMineCount)
    }

    private fun inputNaturalNumber(message: String): Int {
        println(message)
        val input = readLine()
            ?.trim()
            ?.toIntOrNull()
            ?: inputNaturalNumber(message)

        return if (input > 0) input else inputNaturalNumber(message)
    }
}

private fun BlockDto.toView(): String {
    return when (this.isChecked) {
        true -> if (isMine) "■" else mineCount.toString()
        false -> "□"
    }
}
