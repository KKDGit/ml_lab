"""
amaze module
~~~~~~~~~~~~

This package contains code to:

* Generate mazes with various algorithms
* Solve the maze with a simple maze-running algorithm


"""

from amaze.maze import Maze, NORTH, SOUTH, EAST, WEST
from amaze.kruskal import Kruskal
from amaze.recursive import Recursive
from amaze.runner import run
from amaze.generate import generate

__author__ = 'Reindert-Jan Ekker'
