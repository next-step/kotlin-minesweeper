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

    private tailrec fun inputMineCount(message: String, maximumMineCount: Int): Int {
        val mineCount = inputNaturalNumber(message)
        return if (mineCount <= maximumMineCount) mineCount else inputMineCount(message, maximumMineCount)
    }

    override fun outputMineSweeper(mineBoardDto: MineBoardDto) {
        println("지뢰찾기 게임 시작")
        mineBoardDto.board.windowed(size = mineBoardDto.width, step = mineBoardDto.width)
            .map { row -> row.joinToString(separator = " ") { it.toView() } }
            .forEach(::println)
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
    return when (this.isMine) {
        true -> "■"
        false -> mineCount.toString()
    }
}
