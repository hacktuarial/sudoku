import scala.collection.mutable.ListBuffer

class SudokuSolver() {
  // based on https://en.wikipedia.org/wiki/Depth-first_search
  def solve(inputBoard: SudokuBoard): SudokuBoard = {
    var discovered = Set[SudokuBoard]()
    val stack = ListBuffer[SudokuBoard]()

    stack.insert(0, inputBoard)
    while (stack.nonEmpty) {
      val board = stack.remove(0)
      if (board.isCorrect) return board;
      if (! (discovered contains board)) {
        discovered += board
        board.generateCandidates().map(stack.insert(0, _))
      }
    }
    println("ERROR no solution found")
    inputBoard
  }

}
