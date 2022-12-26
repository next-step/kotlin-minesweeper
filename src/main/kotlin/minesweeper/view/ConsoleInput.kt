package minesweeper.view

class ConsoleInput {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        val input = readNotBlankLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException("height should be number")
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        val input = readNotBlankLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException("width should be number")
    }

    fun getCountOfMine(): Int {
        println("지뢰는 몇 개인가요?")
        val input = readNotBlankLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException("count of mine should be number")
    }

    private fun readNotBlankLine() = readln().ifBlank { throw IllegalArgumentException("input should be not blank") }.trim()
}
