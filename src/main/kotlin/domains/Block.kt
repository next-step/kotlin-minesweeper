package domains

abstract class Block protected constructor(open val position: Position) {
    abstract val marker: String

    companion object{
        fun from(position: Position, minePositions: Positions): Block {
            if (position in minePositions.values) {
                return MineBlock(position)
            }
            return NormalBlock(position)
        }
    }
}
