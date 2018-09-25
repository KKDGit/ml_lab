.. _api:

API Documentation
=================

.. module:: amaze

For normal use, all you will have to do to get started is::

    import amaze

This will import the following:

* The :func:`generate` function for creating a maze
* The class :class:`Maze` and its subclasses
* Two classes implementing maze generation algorithms: :class:`Kruskal`
  and :class:`Recursive`
* The :func:`run` function for solving the maze
* Several `constants`_ representing the four directions.

Generating a maze
-----------------

.. autofunction:: generate()

Call this function with the width and height of the maze and it will return
a :class:`Maze` instance with those dimensions::

    m = generate(10,20)

Maze generation algorithm: Kruskal
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. autoclass:: Kruskal

Maze generation algorithm: Recursive
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. autoclass:: Recursive

The Maze class
--------------

.. autoclass:: Maze
    :members:


Running the maze
----------------

.. autofunction:: run()

Constants
---------

.. _constants:

Four directions constants have been defined:

- EAST: with value ``(1, 0)``
- NORTH: is ``(0, -1)``
- WEST: is ``(-1, 0)``
- SOUTH: is ``(0, 1)``