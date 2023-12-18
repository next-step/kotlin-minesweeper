package minesweeper.view

object InputView {

    fun getHeight(): Int {
        println("높이를 입력하세요.")
        val height = readln()
        println()

        require(height.toIntOrNull() != null) {
            "높이는 숫자여야 합니다."
        }

        return height.toInt()
    }

    fun getWidth(): Int {
        println("넓이를 입력하세요.")
        val width = readln()
        println()

        require(width.toIntOrNull() != null) {
            "넓이는 숫자여야 합니다."
        }

        return width.toInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val mineCount = readln()
        println()

        require(mineCount.toIntOrNull() != null) {
            "지뢰 수는 숫자여야 합니다."
        }

        return mineCount.toInt()
    }
}
