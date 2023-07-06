package minesweeper.domain

object MapValidator {
    fun validate(rows: Int, cols: Int) {
        if (rows <= 0 || cols <= 0) throw IllegalArgumentException("row나 col이 0보다 작습니다.")
    }
}
