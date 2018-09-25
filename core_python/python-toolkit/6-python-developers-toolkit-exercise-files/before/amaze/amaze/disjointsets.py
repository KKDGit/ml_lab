
"""
The DisjointSets module provides a class that represents sets of integers in
the half-open interval [0..n).

When first creating an instance of the class, it will contain n sets, with
each integer in a set of its own:

    >>> ds = DisjointSets(5)
    >>> ds.size
    5
    >>> str(ds)
    '0: [0], 1: [1], 2: [2], 3: [3], 4: [4]'

The union() method lets us combine two sets:
    >>> ds = DisjointSets(4)
    >>> str(ds)
    '0: [0], 1: [1], 2: [2], 3: [3]'
    >>> ds.union(0,2)
    >>> str(ds)
    '1: [1], 2: [0, 2], 3: [3]'

Each set is identified by its root, which is simply one of the numbers in the
set. We can find out to which set a number belongs by asking for it's root with
the find() method:

    >>> ds.find(0)
    2
    >>> ds.find(2)
    2

Two numbers are in the same set if find() returns the same root for both
numbers.
"""

# pylint: disable=invalid-name
__author__ = 'Reindert-Jan Ekker'


class DisjointSets(object):
    """Represents sets of integers in the range [0..n]"""
    def __init__(self, size):
        """Creates a new instance containing all the integers from the interval
         [0..size-1]. Each integer will be in a set of its own, so the number
         of sets will be equal to size.
        """
        self._data = [-1]*size
        self._size = size

    @property
    def size(self):
        """Current number of sets."""
        return self._size

    def find(self, x):
        """Finds the root node of the current set."""
        if self._data[x] < 0:
            return x
        self._data[x] = self.find(self._data[x])
        return self._data[x]

    def union(self, x, y):
        """Combines sets x and y"""
        assert self._data[x] < 0 and self._data[y] < 0
        if x == y:
            return

        x_size = self._data[x]
        y_size = self._data[y]
        if x_size < y_size:
            self._data[y] = x
            self._data[x] = x_size + y_size
        else:
            self._data[x] = y
            self._data[y] = x_size + y_size
        self._size -= 1

    def __str__(self):
        sets = {}
        for i in range(len(self._data)):
            sets.setdefault(self.find(i), []).append(i)
        strings = []
        for k in sets.keys():
            strings.append("{}: {}".format(k, sets[k]))
        return ", ".join(strings)

if __name__ == "__main__":
    import doctest
    doctest.testmod()
