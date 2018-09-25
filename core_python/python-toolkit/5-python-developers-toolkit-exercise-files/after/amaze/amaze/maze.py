"""
maze.py
~~~~~~~

This module provides the base Maze class and the following constants:

NORTH = (0,-1)
SOUTH = (0, 1)
WEST = (-1, 0)
EAST = (1, 0)
"""

__author__ = 'Reindert-Jan Ekker'

NORTH = (0, -1)
SOUTH = (0, 1)
WEST = (-1, 0)
EAST = (1, 0)


class Maze(object):
    """A base class for Maze objects. Instances of this class have no walls at
        all. Use a maze generator to instantiate a subclass that has walls
        instead.
    """
    def __init__(self, width, height):
        self._height = height
        self._width = width

    def connected(self, x1, y1, x2, y2):
        """Is there a wall between the given positions?"""
        return True

    @property
    def height(self):
        return self._height

    @property
    def width(self):
        return self._width

    def out_of_bounds(self, x, y):
        """Return true if the given location is out of bounds of the maze. S
        >>> m = Maze(4, 4)
        >>> m.out_of_bounds(-1,2)
        True
        >>> m.out_of_bounds(2,-2)
        True
        >>> m.out_of_bounds(2,4)
        True
        >>> m.out_of_bounds(4,2)
        True
        >>> m.out_of_bounds(1,1)
        False
        """
        return x < 0 or y < 0 or x >= self._width or y >= self._height

    def neighbour(self, x, y, direction):
        """Returns the neighbouring square from (x,y) in given direction.
        Returns None if the neighbour is out of bounds.

        >>> m = Maze(4, 4)
        >>> m.neighbour(0, 1, NORTH)
        (0, 0)
        >>> m.neighbour(0, 0, WEST) is None
        True
        """
        new_x = x + direction[0]
        new_y = y + direction[1]
        if self.out_of_bounds(new_x, new_y):
            return None
        return new_x, new_y

    def can_move(self, x, y, direction):
        """Returns True if we can move in *direction* from (x,y).

        >>> m = Maze(4, 4)
        >>> m.can_move(0, 1, NORTH)
        True
        >>> m.can_move(0, 0, WEST)
        False
        """
        new_x = x + direction[0]
        new_y = y + direction[1]
        if self.out_of_bounds(new_x, new_y) or \
                not self.connected(x, y, new_x, new_y):
            return False
        return True

    def move(self, x, y, direction):
        """Returns coordinates for next square when moving in *direction* from
        (x,y). If you cannot move in that direction because of a wall or edge,
        this returns your original (x,y). Otherwise, it returns
        (x + direction[0], y + direction[1]).

        >>> m = Maze(4, 4)
        >>> m.move(0, 1, NORTH)
        (0, 0)
        >>> m.move(0, 0, WEST)
        (0, 0)
        """
        if not self.can_move(x, y, direction):
            return x, y
        return x + direction[0], y + direction[1]

    def __str__(self):
        lines = ["+-" * self._width + "+"]
        for y in range(self._height):
            hline = "|"
            vline = "+"
            for x in range(self._width):
                if self.connected(x, y, x+1, y):
                    hline += "  "
                else:
                    hline += " |"
                if self.connected(x, y, x, y+1):
                    vline += " +"
                else:
                    vline += "-+"
            lines.append(hline)
            lines.append(vline)
        return "\n".join(lines)

if __name__ == "__main__":
    import doctest
    doctest.testmod()



