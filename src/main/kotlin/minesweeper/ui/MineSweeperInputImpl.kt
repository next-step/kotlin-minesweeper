package minesweeper.ui

private const val REQUEST_NUMBER_MESSAGE = "숫자를 입력해 주세요."

class MineSweeperInputImpl : MineSweeperInput {

    override fun requestWidth(): Int = promptAndReadNumberNotNull(prompt = "\n높이를 입력하세요\n")

    override fun requestHeight(): Int = promptAndReadNumberNotNull(prompt = "\n너비를 입력하세요.\n")

    override fun requestMineCapacity(): Int = promptAndReadNumberNotNull(prompt = "\n지뢰는 몇 개인가요?\n")

    override fun requestMarkingPoint(): List<Int> = promptAndReadln(prompt = "\nopen: ")?.let {
        return it.split(",")
            .map(String::trim)
            .mapNotNull(String::toIntOrNull)
    } ?: emptyList()

    private tailrec fun promptAndReadNumberNotNull(prompt: String, failedPrompt: String = REQUEST_NUMBER_MESSAGE): Int {
        val result = promptAndReadNumber(prompt)

        return if (result == null) {
            println(failedPrompt)
            promptAndReadNumberNotNull(prompt, failedPrompt)
        } else {
            result
        }
    }

    private fun promptAndReadNumber(prompt: String): Int? {
        return promptAndReadln(prompt)?.toIntOrNull()
    }

    private fun promptAndReadln(prompt: String): String? {
        print(prompt)
        return readlnOrNull()?.trim()
    }
}
