"""
recurse.py
~~~~~~~~~~

Provides a Maze generator using recursive memory. It

This algorithm runs quite fast, but takes some memory (proportional to
height*widht)
TODO: insert example image
"""

from random import randint, shuffle

__author__ = 'Reindert-Jan Ekker'

from amaze.maze import NORTH, SOUTH, WEST, EAST, Maze

DIRECTIONS = [EAST, NORTH, SOUTH, WEST]
WALL_FLAGS = [2, 4, 8, 16]
OPPOSITE_FLAGS = [16, 8, 4, 2]
VISITED = 1
ALL_WALLS = 2 | 4 | 8 | 16


class Recursive(object):
    """
    Implements a simple recursive algorithm for generating a maze.The result
    will be a maze with less cul-de-sacs and longer passages.

    Normally you won't want to use this class directly. Give it as an argument
    to :func:`generate` instead.

    :param width: The width of the maze.
    :param height: The height of the maze.
    """
    def __init__(self, width, height):
        self._cells = [[ALL_WALLS]*height for i in range(width)]
        self._maze = RecursiveMaze(width, height, self._cells)

    def generate(self):
        """
        Generator for maze generation. Everytime it removes a wall from
        the maze this will ``yield`` that wall.

        This function is mostly useful when you want to show the maze being
        generated as an animation.
        """
        width, height = self._maze.width, self._maze.height
        n_cells = width * height
        start_cell = randint(0, n_cells)
        x, y = start_cell % width, start_cell // width
        self._cells[x][y] |= VISITED
        visit_count = 1
        cell_queue = [(x, y)]
        while visit_count < n_cells:
            indices = [0, 1, 2, 3]
            shuffle(indices)
            for idx in indices:
                dx, dy = DIRECTIONS[idx]
                new_x, new_y = x + dx, y + dy
                if self._maze.out_of_bounds(new_x, new_y) or \
                                self._cells[new_x][new_y] & VISITED:
                    continue

                self._cells[new_x][new_y] ^= VISITED
                self._cells[new_x][new_y] ^= OPPOSITE_FLAGS[idx]
                self._cells[x][y] ^= WALL_FLAGS[idx]
                visit_count += 1
                cell_queue.append((x, y))
                yield (x, y), (new_x, new_y)
                x, y = new_x, new_y
                break
            else:
                x, y = cell_queue.pop()

    @property
    def maze(self):
        return self._maze



class RecursiveMaze(Maze):
    def __init__(self, width, height, cells):
        super(RecursiveMaze, self).__init__(width, height)
        self._cells = cells

    def connected(self, x1, y1, x2, y2):
        if self.out_of_bounds(x1, y1) or self.out_of_bounds(x2, y2):
            return False
        if x2 - 1 == x1:
            if y2 == y1:
                # east
                return not self._cells[x1][y1] & 2
        elif x1 - 1 == x2:
            if y2 == y1:
                # west
                return not self._cells[x1][y1] & 16
        elif x1 == x2:
            if y2 - 1 == y1:
                # south
                return not self._cells[x1][y1] & 8
            if y1 - 1 == y2:
                # north
                return not self._cells[x1][y1] & 4
        return False
