package minesweeper.ui

object InputView {

    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_COUNT_MESSAGE = "지뢰는 몇 개인가요?"

    fun inputHeight(): Int {
        println(INPUT_HEIGHT_MESSAGE)
        val inputHeight = readln().toInt()

        runCatching {
            checkValidHeight(inputHeight)
        }.onFailure { e ->
            println(e.message)
            return inputHeight()
        }
        return inputHeight
    }

    private fun checkValidHeight(height: Int) {
        require(height in 1..100) { "높이는 1 이상 100 이하의 숫자여야 합니다." }
    }

    fun inputWidth(): Int {
        println()
        println(INPUT_WIDTH_MESSAGE)
        val inputWidth = readln().toInt()

        runCatching {
            checkValidWidth(inputWidth)
        }.onFailure { e ->
            println(e.message)
            return inputWidth()
        }
        return inputWidth
    }

    private fun checkValidWidth(width: Int) {
        require(width in 1..100) { "너비는 1 이상 100 이하의 숫자여야 합니다." }
    }

    fun inputCount(): Int {
        println()
        println(INPUT_COUNT_MESSAGE)
        val inputCount = readln().toInt()

        runCatching {
            checkValidCount(inputCount)
        }.onFailure { e ->
            println(e.message)
            return inputCount()
        }
        return inputCount
    }

    private fun checkValidCount(count: Int) {
        require(count in 1..100) { "지뢰 개수는 1 이상 100 이하의 숫자여야 합니다." }
    }
}
