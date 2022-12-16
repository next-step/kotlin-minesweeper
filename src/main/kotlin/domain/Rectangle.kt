package domain

data class Rectangle(val height: Int, val width: Int) {
    init {
        require(height > 0 && width > 0) { "높이와 너비는 양수여야합니다." }
    }
}
