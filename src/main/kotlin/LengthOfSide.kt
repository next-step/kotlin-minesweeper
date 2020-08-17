class LengthOfSide(length: Int) {

    init {
        require(length in NUMBER_RANGE) {
            "높이 혹은 너비는 10 - 30까지만 가능합니다."
        }
    }

    companion object {
        private val NUMBER_RANGE = 10..30
    }
}
