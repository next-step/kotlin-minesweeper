package minesweeper.dto

import minesweeper.utils.Validation

class NumberOfMinesResult(number: String) {

    var number: Int
        private set

    init {
        require(number.isNotEmpty()) { "공백이 들어옴" }
        require(Validation.isNumeric(number)) { "숫자가 아닌 문자가 들어옴" }
        this.number = number.toInt()
    }

}
