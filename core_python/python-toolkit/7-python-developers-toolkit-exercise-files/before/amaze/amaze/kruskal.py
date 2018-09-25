"""
kruskal.py
~~~~~~~~~~

Provides a Maze generator using Kruskal's algorithm, as well as a Maze subclass
called KruskalMaze.
"""

__author__ = 'Reindert-Jan Ekker'

from random import shuffle, random

from amaze.maze import Maze
from amaze.disjointsets import DisjointSets


class Kruskal(object):
    """
    Implements `Kruskal's algorithm <http://en.wikipedia.org/wiki/Kruskal%27s_algorithm>`_
    for generating a maze.The result will be a maze with lots of short twisting
    passages and many cul-de-sacs.

    Normally you won't want to use this class directly. Give it as an argument
    to :func:`generate` instead.

    :param width: The width of the maze.
    :param height: The height of the maze.
    """
    def __init__(self, width, height):
        self._width, self._height = width, height
        self._maze = None

    def generate(self):
        """
        Generator for maze generation. Everytime it removes a wall from
        the maze this will ``yield`` that wall.

        This function is mostly useful when you want to show the maze being
        generated as an animation.
        """
        width, height = self._width, self._height
        sets = DisjointSets(height*width)
        edges = generate_all_edges(width, height)
        shuffle(edges, random=random)
        passages = []
        walls = []
        while sets.size > 1:
            edge = edges.pop()
            set_a = sets.find(edge[0])
            set_b = sets.find(edge[1])
            if set_a != set_b:
                yield _edge_to_coords(edge, width)
                passages.append(edge)
                sets.union(set_a, set_b)
        self._maze = KruskalMaze(width, height, walls, passages)

    @property
    def maze(self):
        return self._maze


def _edge_to_coords(edge, width):
    """Transform edge representation from square numbers to (x,y) pairs"""
    return ((edge[0] % width, edge[0] // width),
            (edge[1] % width, edge[1] // width))


def generate_all_edges(width, height):
    squares = [(y*width+x, x, y) for x in range(width) for y in range(height)]
    # first vertical, then horizontal edges
    return [(s, s+1) for (s, x, y) in squares if x < width-1] + \
        [(s, s+width) for (s, x, y) in squares if y < height-1]


class KruskalMaze(Maze):
    """The maze class for a maze generated with the kruskal algorithm."""
    def __init__(self, width, height, walls, passages):
        super(KruskalMaze, self).__init__(width, height)
        self._walls = walls
        self._passages = passages

    def connected(self, x1, y1, x2, y2):
        sq1 = y1*self._width + x1
        sq2 = y2*self._width + x2
        edge = (sq1, sq2) if sq1 < sq2 else (sq2, sq1)
        return edge in self._passages


if __name__ == "__main__":
    import doctest
    doctest.testmod()
