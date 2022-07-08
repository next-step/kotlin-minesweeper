package domain

/**
 * 지뢰찾기 땅
 * Created by Jaesungchi on 2022.06.28..
 */
class Ground {
    var isMine: Boolean = false
        private set

    var mineCount: Int = 0
        private set

    fun installMine() {
        isMine = true
    }

    fun addMineCount(count: Int = 1) {
        mineCount += count
    }
}
