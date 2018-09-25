"""
kruskal.py
~~~~~~~~~~

Provides a Maze generator using Kruskal's algorithm:
`http://en.wikipedia.org/wiki/Kruskal%27s_algorithm`.
"""

__author__ = 'Reindert-Jan Ekker'

from random import shuffle, random

from .maze import Maze
from .disjointsets import DisjointSets
import collections

def kruskal_generate(width, height):
    """Generates the maze."""
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
            passages.append(edge)
            sets.union(set_a, set_b)
        else:
            walls.append(edge)
    walls += edges
    return KruskalMaze(width, height, walls, passages)


def generate_all_edges(width, height):
    squares = [(y*width+x, x, y) for x in range(width) for y in range(height)]
    # first vertical, then horizontal edges
    return [(s, s+1) for (s, x, y) in squares if x < width-1] + \
        [(s, s+width) for (s, x, y) in squares if y < height-1]


class KruskalMaze(Maze):
    def __init__(self, width, height, walls, passages):
        super(KruskalMaze, self).__init__(width, height)
        self._walls = walls
        self._passages = passages

    def connected(self, x1, y1, x2, y2):
        sq1 = y1*self._width + x1
        sq2 = y2*self._width + x2
        edge = (sq1, sq2) if sq1 < sq2 else (sq2, sq1)
        return edge in self._passages

    def _square_to_coords(self, square):
        """Transform square representation from square numbers to (x,y) pairs
        """
        return square % self._width, square // self._width

    def _edge_to_wall(self, edge):
        """Transform edge representation from square numbers to (x,y) pairs"""
        return self._square_to_coords(edge[0]), self._square_to_coords(edge[1])


    @property
    def height(self):
        return self._height

    @property
    def width(self):
        return self._width

    @property
    def passages(self):
        return [self._edge_to_wall(p) for p in self._passages]

    @property
    def walls(self):
        return [self._edge_to_wall(p) for p in self._walls]


    
if __name__ == "__main__":
    import doctest
    doctest.testmod()
