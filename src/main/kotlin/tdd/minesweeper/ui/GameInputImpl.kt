package tdd.minesweeper.ui

private const val REQUEST_NUMBER_MESSAGE = "유효한 숫자를 입력하세요."

object GameInputImpl : GameInput {

    override fun requestHeight(): Int =
        promptAndNotNull(prompt = "높이를 입력하세요.\n", failedPrompt = REQUEST_NUMBER_MESSAGE) {
            it.toIntOrNull()
        }

    override fun requestWidth(): Int =
        promptAndNotNull(prompt = "너비를 입력하세요.\n", failedPrompt = REQUEST_NUMBER_MESSAGE) {
            it.toIntOrNull()
        }

    override fun requestMineCapacity(): Int =
        promptAndNotNull(prompt = "지뢰는 몇 개인가요?\n", failedPrompt = REQUEST_NUMBER_MESSAGE) {
            it.toIntOrNull()
        }

    override fun requestMarkingPoint(): Pair<Int, Int> =
        promptAndNotNull(prompt = "너비를 입력하세요.\n", failedPrompt = REQUEST_NUMBER_MESSAGE) { input ->
            input.split(",")
                .mapNotNull { it.trim().toIntOrNull() }
                .takeIf { it.size == 2 }?.let { it[0] to it[1] }
        }

    private fun <T> promptAndNotNull(prompt: String, failedPrompt: String, converter: (String) -> T?): T {
        print(prompt)

        return readln().let(converter) ?: run {
            println(failedPrompt)
            promptAndNotNull(prompt, failedPrompt, converter)
        }
    }
}
