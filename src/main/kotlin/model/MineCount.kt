package model

class MineCount(val value: Int, private val maxCount: Int) {
    init {
        if (value !in 1..maxCount) throw Exception("범위가 1 ~ ${maxCount}사이에 존재해야 합니다.")
    }
}
