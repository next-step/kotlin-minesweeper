package minesweeper.dto

import minesweeper.utils.Validation

class WidthResult(width: String) {

    var width: Int
        private set

    init {
        require(width.isNotEmpty()) { "공백이 들어옴" }
        require(Validation.isNumeric(width)) { "숫자가 아닌 문자가 들어옴" }
        this.width = width.toInt()
    }
}
