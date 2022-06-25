package ui.input

import domain.NumberOfMines

object InputUI {

    tailrec fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: readWidth()
    }

    tailrec fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: readHeight()
    }

    tailrec fun readNumberOfMines(): NumberOfMines {
        println("지뢰는 몇 개인가요?")
        val value = readln().toIntOrNull() ?: return readNumberOfMines()
        return NumberOfMines(value)
    }
}
