package view

import domain.Column
import domain.MineCount
import domain.Row

object InputView {
    fun inputHeight(): Row {
        println("높이를 입력하세요.")
        val input = readln().toInt()
        return Row(validateInput(input))
    }

    fun inputWidth(): Column {
        println("너비를 입력하세요.")
        val input = readln().toInt()
        return Column(validateInput(input))
    }

    fun inputMineCount(row: Row, column: Column): MineCount {
        println("지뢰는 몇 개인가요?")
        val number = readln().toInt()
        return MineCount.from(row, column, number)
    }

    private fun validateInput(input: Int): Int {
        require(input > 0) { "1 이상의 수여야 합니다." }
        return input
    }
}
