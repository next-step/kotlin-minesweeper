package view

import domain.PositiveNumber

object InputView {
    private const val VERTICAL_MESSAGE = "높이를 입력하세요."
    private const val HORIZONTAL_MESSAGE = "너비를 입력하세요."
    private const val MINE_MESSAGE = "지뢰는 몇 개인가요?"

    fun getVertical(): PositiveNumber =
        readInput(VERTICAL_MESSAGE)

    fun getHorizontal(): PositiveNumber =
        readInput(HORIZONTAL_MESSAGE)

    fun getMineCount(): PositiveNumber =
        readInput(MINE_MESSAGE)

    private fun readInput(message: String): PositiveNumber = inputErrorCatch {
        println(message)
        val input = requireNotNull(readln().toIntOrNull())
        PositiveNumber(input)
    }

    private fun <T> inputErrorCatch(action: () -> T): T {
        while (true) {
            runCatching { return action() }
                .onFailure { println("다시 입력해주세요.") }
        }
    }
}
