import _tkinter
import platform

from amaze.demo import MazeDemo

__author__ = 'Reindert-Jan Ekker'


if __name__ == "__main__":
    try:
        app = MazeDemo(None)
        app.mainloop()
    except _tkinter.TclError as e:
        if platform.system() == 'Windows':
            print(e)
            print("Seems tkinter will not run; try running this program outside a virtualenv.")