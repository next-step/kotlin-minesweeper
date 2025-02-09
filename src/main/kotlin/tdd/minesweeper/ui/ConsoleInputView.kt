package tdd.minesweeper.ui

import tdd.minesweeper.domain.Location

object ConsoleInputView : InputView {
    override fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return getPositiveInt(readln().trim().toIntOrNull())
    }

    override fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return getPositiveInt(readln().trim().toIntOrNull())
    }

    override fun inputCountOfMines(): Int {
        println("지뢰는 몇 개인가요?")
        return getPositiveInt(readln().trim().toIntOrNull())
    }

    override fun inputLocation(): Location {
        print("open: ")
        val input = readln().trim()
        val split = input.split(",")
        require(split.size == 2) {
            "위치는 쉼표로 구분된 두 개의 숫자를 입력해야 합니다: input=$input"
        }
        val row = split[0].trim().toIntOrNull()
        val col = split[1].trim().toIntOrNull()

        require(row != null && col != null) {
            "각 위치 좌표는 숫자를 입력해주세요: row=${split[0].trim()}; col=${split[1].trim()}"
        }

        require(row > 0 && col > 0) {
            "각 위치 좌표는 양수를 입력해주세요: row=$row; col=$col"
        }
        return Location(
            row = row,
            col = col,
        )
    }

    private fun getPositiveInt(input: Int?): Int {
        require(input != null && input > 0) {
            "양수를 입력해주세요: input=$input"
        }
        println()
        return input
    }
}
