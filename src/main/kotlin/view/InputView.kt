package view

import domain.Column
import domain.Coordinate
import domain.MineCount
import domain.Row

object InputView {
    fun inputHeight(): Row {
        println("높이를 입력하세요.")
        return Row(readln().toInt())
    }

    fun inputWidth(): Column {
        println("너비를 입력하세요.")
        return Column(readln().toInt())
    }

    fun inputMineCount(row: Row, column: Column): MineCount {
        println("지뢰는 몇 개인가요?")
        val number = readln().toInt()
        return MineCount.from(row, column, number)
    }

    fun inputCellToOpen(): Coordinate {
        print("open: ")
        val input = readln()
            .replace(" ", "")
            .split(",")
        return Coordinate(input[0].toInt() to input[1].toInt())
    }
}
