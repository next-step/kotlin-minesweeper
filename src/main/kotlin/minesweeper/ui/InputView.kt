package minesweeper.ui

import minesweeper.domain.Size

object InputView {

    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_COUNT_MESSAGE = "지뢰는 몇 개인가요?"

    private val inputMap: Map<InputType, String> = mapOf(
        InputType.HEIGHT to INPUT_HEIGHT_MESSAGE,
        InputType.WIDTH to INPUT_WIDTH_MESSAGE,
        InputType.COUNT to INPUT_COUNT_MESSAGE
    )

    fun inputSize(inputType: InputType): Size {
        println(inputMap[inputType])

        val result = runCatching {
            Size(readln())
        }

        return result.getOrElse { e ->
            println(e.message)
            inputSize(inputType)
        }
    }
}
