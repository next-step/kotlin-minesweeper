package minesweeper.domain.mine

data class Mine(val mineType: MineType) {
    companion object {
        fun generateLand(): Mine {
            return Mine(MineType.LAND)
        }

        fun generateMine(): Mine {
            return Mine(MineType.MINE)
        }
    }
}
