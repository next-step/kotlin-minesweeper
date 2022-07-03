package domain

/**
 * 지뢰찾기 땅
 * Created by Jaesungchi on 2022.06.28..
 */
data class Ground(val isMine: Boolean, var mineCount: Int = 0) {
    fun addMineCount(count: Int = 1) {
        mineCount += count
    }
}
