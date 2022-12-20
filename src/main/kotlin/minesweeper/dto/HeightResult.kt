package minesweeper.dto

import minesweeper.utils.Validation

class HeightResult(height: String) {

    var height: Int
        private set

    init {
        require(height.isNotEmpty()) { "공백이 들어옴" }
        require(Validation.isNumeric(height)) { "숫자가 아닌 문자가 들어옴" }
        this.height = height.toInt()
    }

}
