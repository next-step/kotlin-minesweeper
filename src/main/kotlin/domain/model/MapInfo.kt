package domain.model

class MapInfo(val width: Int, val height: Int) {
    init {
        require(height > 0) { "높이 값은 0보다 커야합니다" }
        require(width > 0) { "너비 값은 0보다 커야합니다" }
    }
}
