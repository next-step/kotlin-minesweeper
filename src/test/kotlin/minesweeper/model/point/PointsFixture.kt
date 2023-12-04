// package minesweeper.model.point
//
// import minesweeper.model.vison.VisionStrategy
//
// object PointsFixture {
//    fun make(vararg pairs: Pair<Int, Int>): Points {
//        return Points(
//            pairs.asSequence()
//                .map { Coordinate(Vertical(it.first), Horizontal(it.second)) }
//                .map { it to Attribute.MINE }
//                .toMap()
//        )
//    }
//
//    fun make(vararg pairs: Pair<Int, Int>, visionStrategy: VisionStrategy): Points {
//        return Points(
//            pairs.asSequence()
//                .map { Coordinate(Vertical(it.first), Horizontal(it.second)) }
//                .map { it to Attribute.MINE }
//                .toMap(),
//            visionStrategy = visionStrategy
//        )
//    }
// }
