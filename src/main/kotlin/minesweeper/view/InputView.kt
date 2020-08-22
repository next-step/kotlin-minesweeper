package minesweeper.view

object InputView {
    private const val INPUT_HEIGHT = "높이를 입력하세요."
    private const val INPUT_WIDTH = "너비를 입력하세요."
    private const val INPUT_MINE_COUNT = "지뢰의 수를 입력하세요."
    private const val TRY_AGAIN = "다시 시도 하세요."

    private fun getQuestionAnswer(question: String): Int {
        println(question)
        return readLine()?.toIntOrNull() ?: 0
    }

    private fun getValidatedAnswer(
        question: String,
        maxCount: Int? = null
    ): Int =
        try {
            val answer = getQuestionAnswer(question)
            require(answer > 0) { "입력값은 1보다 커야한다." }
            maxCount?.let {
                require(answer < it) { "입력값은 maxCount보다 작아야한다." }
            }
            answer
        } catch (e: Exception) {
            println(TRY_AGAIN + " - ${e.localizedMessage}")
            getValidatedAnswer(question)
        }

    fun getHeight(): Int = getValidatedAnswer(INPUT_HEIGHT)

    fun getWidth() = getValidatedAnswer(INPUT_WIDTH)

    fun getMineCount(maxCount: Int) = getValidatedAnswer(INPUT_MINE_COUNT, maxCount)
}
