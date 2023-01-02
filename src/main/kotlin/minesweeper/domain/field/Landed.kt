package minesweeper.domain.field

abstract class Landed : Land {
    override fun mine(): Land {
        throw IllegalStateException("이미 설정된 필드입니다.")
    }

    override fun safe(aroundMineCount: Int): Land {
        throw IllegalStateException("이미 설정된 필드입니다.")
    }
}
