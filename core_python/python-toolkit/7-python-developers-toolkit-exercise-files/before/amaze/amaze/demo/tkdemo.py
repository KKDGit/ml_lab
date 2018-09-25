"""
tkdemo.py
~~~~~~~~~~

This module defines the MazeDemo class, which implements a simple Tkinter
GUI for generating and running mazes.
"""

__author__ = 'Reindert-Jan Ekker'

try:
    import tkinter
    from tkinter import messagebox
    from time import process_time
except ImportError:
    import Tkinter as tkinter
    import tkMessageBox as messagebox
    from time import clock as process_time
import _tkinter
import platform

from amaze import Kruskal, Recursive, run


class MazeDemo(tkinter.Tk):
    draw_update_sec = 0.01
    screen_width = 500
    screen_height = 300

    def __init__(self, parent):
        tkinter.Tk.__init__(self, parent)
        self.parent = parent
        self.wm_title("Maze")
        self.initialize()
        self._stop = False

    def initialize(self):
        self.canvas = tkinter.Canvas(self, width=MazeDemo.screen_width+1, height=MazeDemo.screen_height+1,
                                     highlightthickness=0)
        self.canvas.pack(side=tkinter.LEFT, padx=5, pady=5)
        self.canvas.create_rectangle(0, 0, MazeDemo.screen_width, MazeDemo.screen_height)

        self.inputframe = tkinter.Frame()

        tkinter.Label(self.inputframe, text="Width: ").pack(anchor="w")
        self.width_txt = tkinter.StringVar()
        self.width_entry = tkinter.Entry(self.inputframe,
                                         textvariable=self.width_txt)
        self.width_txt.set("100")
        self.width_entry.pack(anchor="w")

        tkinter.Label(self.inputframe, text="Height: ").pack(anchor="w")
        self.height_txt = tkinter.StringVar()
        self.height_entry = tkinter.Entry(self.inputframe,
                                          textvariable=self.height_txt)
        self.height_txt.set("60")
        self.height_entry.pack(anchor="w")

        self.animate = tkinter.IntVar()
        self.animate.set(1)
        self.animate_check = tkinter.Checkbutton(self.inputframe,
                                                 text="animate",
                                                 variable=self.animate)
        self.animate_check.pack(anchor="w")

        self.algo = tkinter.IntVar()
        self.algo.set(0)
        self.algo_radio0 = tkinter.Radiobutton(self.inputframe, text="Kruskal",
                                          variable=self.algo, value=0)
        self.algo_radio1 = tkinter.Radiobutton(self.inputframe, text="Recursive",
                                          variable=self.algo, value=1)
        self.algo_radio0.pack(anchor="w")
        self.algo_radio1.pack(anchor="w")

        self.draw_button = tkinter.Button(self.inputframe, text="Draw Maze",
                                          command=self.generate_maze)
        self.draw_button.pack(anchor="w")
        self.run_button = tkinter.Button(self.inputframe, text="Run Maze",
                                         command=self.run_maze,
                                         state=tkinter.DISABLED)
        self.run_button.pack(anchor="w")

        self.inputframe.pack(side=tkinter.LEFT, anchor="n", padx=5, pady=10)

    def generate_maze(self):
        try:
            w = int(self.width_txt.get())
            h = int(self.height_txt.get())
        except ValueError:
            messagebox.showerror("Bad input",
                                 "('{}', '{}') are invalid dimensions.".format(
                                     self.width_txt.get(),
                                     self.height_txt.get()))
            return

        self.height_mult = MazeDemo.screen_height // h
        self.width_mult = MazeDemo.screen_width // w
        self.canvas.delete("all")
        self.canvas.create_rectangle(0, 0, MazeDemo.screen_width, MazeDemo.screen_height)
        self.draw_pre_maze(h, w)
        self.last_update = process_time()

        self.config(cursor="watch")
        self.run_button.config(state=tkinter.DISABLED)

        self.last_update = process_time()
        self.draw_button.config(state=tkinter.DISABLED)
        generator_class = Kruskal if self.algo.get() == 0 else Recursive
        generator = generator_class(w, h)
        for p in generator.generate():
            self.remove_wall(p)
            self.update_canvas()
        self.maze = generator.maze
        self.activate_ui()

    def draw_pre_maze(self, height, width):
        for y in range(height):
            for x in range(width):
                if x < (width -1):
                    self.draw_wall(((x, y), (x+1, y)))
                if y < (height -1):
                    self.draw_wall(((x, y), (x, y+1)))

    def activate_ui(self):
        self.config(cursor="arrow")
        self.run_button.config(state=tkinter.ACTIVE)
        self.draw_button.config(text="Draw Maze", command=self.generate_maze,
                                state=tkinter.ACTIVE)

    def line_coords(self, wall):
        """Coordinates of the line to draw (in maze coordinates,
        not canvas coordinates)"""
        (first, second) = wall
        if second[0] > first[0]:
            # vertical edge
            return (
                first[0] + 1, first[1],
                first[0] + 1, first[1] + 1
            )
        elif first[0] > second[0]:
            return (
                second[0] + 1, second[1],
                second[0] + 1, second[1] + 1
            )
        elif second[1] > first[1]:
            return (
                first[0], first[1] + 1,
                first[0] + 1, first[1] + 1
            )
        elif first[1] > second[1]:
            return (
                second[0], second[1] + 1,
                second[0] + 1, second[1] + 1
            )

    def draw_wall(self, wall):
        coords = self.line_coords(wall)
        self.canvas.create_line(coords[0] * self.width_mult,
                                coords[1] * self.height_mult,
                                coords[2] * self.width_mult,
                                coords[3] * self.height_mult)

    def remove_wall(self, wall):
        coords = self.line_coords(wall)
        self.canvas.create_line(coords[0] * self.width_mult,
                                coords[1] * self.height_mult,
                                coords[2] * self.width_mult,
                                coords[3] * self.height_mult,
                                fill="white")

    def update_canvas(self):
        if self.animate.get() == 1 and process_time() - self.last_update >\
                MazeDemo.draw_update_sec:
            self.canvas.update_idletasks()
            self.last_update = process_time()

    def run_maze(self):
        self.canvas.delete("crumb")
        for (x, y) in run(self.maze):
            self.update_runner(x, y)

    def update_runner(self, x, y):
        r_x = max(1, self.width_mult // 4)
        r_y = max(1, self.height_mult // 4)
        self.canvas.create_oval((x * self.width_mult) + r_x,
                                (y * self.height_mult) + r_y,
                                ((x + 1) * self.width_mult) - r_x,
                                ((y + 1) * self.height_mult) - r_y,
                                fill="blue",
                                tags="crumb")
        self.update_canvas()

def main():
    try:
        app = MazeDemo(None)
        app.mainloop()
    except _tkinter.TclError as e:
        if platform.system() == 'Windows':
            print(e)
            print("Seems tkinter will not run; try running this program outside a virtualenv.")
