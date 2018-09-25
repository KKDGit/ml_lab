"""
generate.py
~~~~~~~~~~

Use the generate method to generate a new maze:

>>> generate(3,3)

TODO: pictures here

You can specify the algorithm with which to generate the maze here as well:

>>> from amaze import Kruskal
>>> generate(5,5, Kruskal)

"""

__author__ = 'Reindert-Jan Ekker'

from amaze.kruskal import Kruskal

def generate(width, height, generator_class=Kruskal):
    """Generate a new Maze instance.

     Keyword arguments:
     width -- the width of the maze
     height -- the height of the maze
     generator_class -- the class that implements the generating algorithm. Default is *Kruskal*.

    Returns a Maze instance.
    """
    gen = generator_class(width, height)
    for passage in gen.generate():
        pass
    return gen.maze

