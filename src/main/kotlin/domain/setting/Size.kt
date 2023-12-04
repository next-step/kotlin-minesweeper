package domain.setting

data class Size(val height: Height, val width: Width) {
    fun getPossibleLocationCount(): Int = height.value * width.value
}
