"""
runner.py
~~~~~~~~~

This module provides a simple maze runner class. You can use it like this:

    >> from amaze import run, kruskal_generate
    >> m = kruskal_generate(100,60)
    >> for (x,y) in run(m):
            print(x, y)
    (0,0)
    (0,1)
    (1,1)
    ...
"""

__author__ = 'Reindert-Jan Ekker'

from amaze.maze import NORTH, SOUTH, WEST, EAST

DIRECTIONS = [EAST, NORTH, WEST, SOUTH]
LEFT_HAND_DIR = [NORTH, WEST, SOUTH, EAST]


def run(maze):
    """Solves the maze by a simple search: always keep your left hand to the
    wall, starting at top left and ending at bottom right. Starts at (0, 0),
    destination is bottom right cell in the maze.

    This is a *generator* function that will *yield* a location as an ``(x,y)``
    tuple until the destination is reached.
    """
    runner = Runner(maze)
    yield runner.location
    while not runner.destination_reached():
        runner.step()
        yield runner.location


class Runner(object):
    """This class runs the maze by keeping its 'left hand' to the wall.
    It starts at the top left and ends at the bottom right.
    """
    def __init__(self, maze):
        self._dir = 0
        self._x = 0
        self._y = 0
        self._maze = maze

    @property
    def location(self):
        return self._x, self._y

    @property
    def direction(self):
        return self._dir

    def step(self):
        """Move to the next position."""
        if not self._wall_at_left_hand():
            self._rotate_left()
        else:
            while not self._can_go_forward():
                self._rotate_right()
        self._move_forward()

    def _can_go_forward(self):
        return self._maze.can_move(self._x, self._y, DIRECTIONS[self._dir])

    def _wall_at_left_hand(self):
        return not self._maze.can_move(self._x, self._y,
                                       LEFT_HAND_DIR[self._dir])

    def destination_reached(self):
        """Are we there yet?"""
        return self._x == self._maze.width-1 and self._y == self._maze.height-1

    def _move_forward(self):
        self._x, self._y = self._maze.move(self._x, self._y,
                                           DIRECTIONS[self._dir])

    def _rotate_left(self):
        self._dir = (self._dir + 1) % 4

    def _rotate_right(self):
        self._dir = (self._dir + 3) % 4
