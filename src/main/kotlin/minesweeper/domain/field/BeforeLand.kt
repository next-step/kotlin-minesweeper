package minesweeper.domain.field

class BeforeLand : Land {
    override fun mine(): Land {
        return Mine()
    }

    override fun safe(aroundMineCount: Int): Land {
        return Safe(aroundMineCount)
    }

    override fun aroundMineCount(): Int {
        throw IllegalStateException("아직 지도가 완성되지 않았습니다.")
    }

    override fun toString(): String {
        throw IllegalStateException("아직 지도가 완성되지 않았습니다.")
    }
}
