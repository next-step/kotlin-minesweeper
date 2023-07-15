package minesweeper.domain

/**
 * 지뢰 지도를 구성할때 사용하는 설정값들의 모음입니다.
 */
data class MineMapConfig(
    val height: Int,
    val width: Int,
    val mineCount: Int,
) {
    init {
        require(height > 0) { "height must be greater than zero, actual : $height" }
        require(width > 0) { "width must be greater than zero, actual : $width" }
        require(mineCount > 0) { "mineCount must be greater than zero, actual : $mineCount" }
        require(height * width >= mineCount) { "mineCount must be less than or equal to height x width, actual : $mineCount" }
    }
}
