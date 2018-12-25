#Plotting periodic table elements

#Importing libraries
from bokeh.plotting import figure
from bokeh.io import output_file, show
from bokeh.sampledata.periodic_table import elements
from bokeh.models import Range1d, PanTool, ResetTool, HoverTool, ColumnDataSource, LabelSet
import pandas
from numpy import nan

elements.dropna(inplace=True)
colormap={'gas':'yellow', 'liquid':'orange', 'solid':'red', nan:'grey'}
elements['color'] = [colormap[x] for x in elements['standard state']]
elements['size'] = elements['van der Waals radius'] / 10
gas = ColumnDataSource(elements[elements['standard state']=='gas'])
liquid = ColumnDataSource(elements[elements['standard state']=='liquid'])
solid = ColumnDataSource(elements[elements['standard state']=='solid'])
unknown = ColumnDataSource(elements[pandas.isnull(elements['standard state'])])


#Define the output file path
output_file("elements.html")

#Create the figure object
f = figure()

#adding glyphs
f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Gas', source=gas)

f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Liquid', source=liquid)

f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Solid', source=solid)

f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Unknown', source=unknown)

f.xaxis.axis_label="Atomic radius"
f.yaxis.axis_label="Boiling point"

#Save and show the figure
show(f)
