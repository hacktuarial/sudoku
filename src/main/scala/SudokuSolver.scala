class SudokuSolver() {

  def solve(inputBoard: SudokuBoard): SudokuBoard = {
    var discovered:Set[SudokuBoard] = Set()
    //var stack = ListBuffewr[SudokuBoard]

    // see https://en.wikipedia.org/wiki/Depth-first_search
    def depthFirstSearch(board: SudokuBoard): SudokuBoard = {
      // base condition: board is a complete solution
      if (board.isCorrect) return board
      // otherwise, mark as discovered and recurse
      discovered += board
      val candidateBoards = board.generateCandidates() diff discovered
      for {
        newBoard <- candidateBoards
      } return depthFirstSearch(newBoard)
    }

    depthFirstSearch(inputBoard)
  }

}
