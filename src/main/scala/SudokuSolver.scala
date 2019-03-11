import scala.collection.mutable.ListBuffer

class SudokuSolver() {

  def solve(inputBoard: SudokuBoard): SudokuBoard = {
    var discovered:Set[SudokuBoard] = Set()
    val stack = ListBuffer[SudokuBoard]()

    // see https://en.wikipedia.org/wiki/Depth-first_search
    stack.insert(0, inputBoard)
    while (stack.nonEmpty) {
      val board = stack.remove(0)
      if (board.isCorrect) return board;
      if (! (discovered contains board)) {
        discovered += board
        //board.generateCandidates().foreach {stack.insert(0, _)}
        for {
          board <- board.generateCandidates()
        } {
          //println(board)
          stack.insert(0, board)
        }

      }
      //println("stack has " + stack.length.toString + " elements")
      // board is already discovered, do nothing
    }
    println("ERROR no solution found")
    inputBoard
  }

}
