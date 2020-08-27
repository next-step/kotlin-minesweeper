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

    private tailrec fun getValidatedAnswer(
        question: String,
        maxCount: Int? = null
    ): Int {
        val answer = getQuestionAnswer(question)
        return if (validate(answer, maxCount)) {
            answer
        } else {
            println(TRY_AGAIN)
            getValidatedAnswer(question, maxCount)
        }
    }

    private fun validate(answer: Int, maxCount: Int? = null): Boolean {
        return answer > 0 && answer <= (maxCount ?: Int.MAX_VALUE)
    }

    fun getHeight(): Int = getValidatedAnswer(INPUT_HEIGHT)

    fun getWidth() = getValidatedAnswer(INPUT_WIDTH)

    fun getMineCount(maxCount: Int) = getValidatedAnswer(INPUT_MINE_COUNT, maxCount)
}
