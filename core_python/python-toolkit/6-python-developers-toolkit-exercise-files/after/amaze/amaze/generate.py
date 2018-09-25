"""
generate.py
~~~~~~~~~~

Provides a convenient function to generate a maze.

"""

__author__ = 'Reindert-Jan Ekker'

from amaze.kruskal import Kruskal

def generate(width, height, generator_class=Kruskal):
    """
    Generate a new Maze instance.

    :param width: The width of the maze
    :param height: The height of the maze
    :param generator_class: The class that implements the generating algorithm.
                            Default is :class:`Kruskal`. You can also specify
                            :class:`Recursive`.

    Returns a Maze. This will be an instance of either :class:`KruskalMaze` or
    :class:`RecursiveMaze`, depending on which algorithm you chose to generate
    it with.
    """
    gen = generator_class(width, height)
    for passage in gen.generate():
        pass
    return gen.maze

