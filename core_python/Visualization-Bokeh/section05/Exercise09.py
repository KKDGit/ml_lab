# Orbiting planets

# importing libraries
from math import cos, sin, radians
from bokeh.io import curdoc
from bokeh.models import ColumnDataSource
from bokeh.plotting import figure

# Setting up the figure
p = figure(x_range=(-2, 2), y_range=(-2, 2))

# Drawing static glyphs
earth_orbit = p.circle(x=0, y=0, radius=1, line_color='blue', line_alpha=0.5, fill_color=None, line_width=2)
mars_orbit = p.circle(x=0, y=0, radius=0.7, line_color='red', line_alpha=0.5, fill_color=None, line_width=2)
sun = p.circle(x=0, y=0, radius=0.2, line_color=None, fill_color="yellow", fill_alpha=0.5)

# Creating columndatasources for the two moving circles
earth_source = ColumnDataSource(data=dict(x_earth=[earth_orbit.glyph.radius * cos(radians(0))],
                                          y_earth=[earth_orbit.glyph.radius * sin(radians(0))]))
mars_source = ColumnDataSource(
    data=dict(x_mars=[mars_orbit.glyph.radius * cos(radians(0))], y_mars=[mars_orbit.glyph.radius * sin(radians(0))]))

# Drawing the moving glyphs
earth = p.circle(x='x_earth', y='y_earth', size=12, fill_color='blue', line_color=None, fill_alpha=0.6,
                 source=earth_source)
mars = p.circle(x='x_mars', y='y_mars', size=12, fill_color='red', line_color=None, fill_alpha=0.6, source=mars_source)

# we will generate x and y coordinates every 0.1 seconds out of angles starting from an angle of 0 for both earth and mars
i_earth = 0
i_mars = 0


# the update function will generate coordinates
def update():
    global i_earth, i_mars  # this tells the function to use global variables declared outside the function
    i_earth = i_earth + 2  # we will increase the angle of earth by 2 in function call
    i_mars = i_mars + 1
    new_earth_data = dict(x_earth=[earth_orbit.glyph.radius * cos(radians(i_earth))],
                          y_earth=[earth_orbit.glyph.radius * sin(radians(i_earth))])
    new_mars_data = dict(x_mars=[mars_orbit.glyph.radius * cos(radians(i_mars))],
                         y_mars=[mars_orbit.glyph.radius * sin(radians(i_mars))])
    earth_source.stream(new_earth_data, rollover=1)
    mars_source.stream(new_mars_data, rollover=1)
    print(earth_source.data)  # just printing the data in the terminal
    print(mars_source.data)


# adding periodic callback and the plot to curdoc
curdoc().add_periodic_callback(update, 100)
curdoc().add_root(p)