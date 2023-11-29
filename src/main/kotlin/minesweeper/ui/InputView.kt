package minesweeper.ui

import minesweeper.domain.Size

object InputView {

    private const val INPUT_HEIGHT_MESSAGE = "높이를 입력하세요."
    private const val INPUT_WIDTH_MESSAGE = "너비를 입력하세요."
    private const val INPUT_COUNT_MESSAGE = "지뢰는 몇 개인가요?"

    fun inputHeight(): Size {
        println(INPUT_HEIGHT_MESSAGE)

        val result = runCatching {
            val inputHeight = readln()
            Size(inputHeight)
        }

        return result.getOrElse { e ->
            println(e.message)
            inputHeight()
        }
    }

    fun inputWidth(): Size {
        println()
        println(INPUT_WIDTH_MESSAGE)
        val result = runCatching {
            val inputWidth = readln()
            Size(inputWidth)
        }

        return result.getOrElse { e ->
            println(e.message)
            inputWidth()
        }
    }

    fun inputCount(): Size {
        println()
        println(INPUT_COUNT_MESSAGE)
        val result = runCatching {
            val inputCount = readln()
            Size(inputCount)
        }

        return result.getOrElse { e ->
            println(e.message)
            inputCount()
        }
    }
}
