package model.minemark

sealed class MineMark

object Safety : MineMark()
object Mine : MineMark()
data class MineCount(val count: Int) : MineMark() {
    init {
        require(count >= 0) { "count must be greater than or equal to 0. but provided `$count`" }
    }
}
