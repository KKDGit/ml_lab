# Creating span annotations depicting averages

# Importing libraries
from bokeh.plotting import figure
from bokeh.io import output_file, show
from bokeh.sampledata.periodic_table import elements
from bokeh.models import Range1d, PanTool, ResetTool, HoverTool, ColumnDataSource, LabelSet
from bokeh.models.annotations import Span, BoxAnnotation, Label, LabelSet

# Remove rows with NaN values and then map standard states to colors
elements.dropna(inplace=True)  # if inplace is not set to True the changes are not written to the dataframe
colormap = {'gas': 'yellow', 'liquid': 'orange', 'solid': 'red'}
elements['color'] = [colormap[x] for x in elements['standard state']]
elements['size'] = elements['van der Waals radius'] / 10

# Create three ColumnDataSources for elements of unique standard states
gas = ColumnDataSource(elements[elements['standard state'] == 'gas'])
liquid = ColumnDataSource(elements[elements['standard state'] == 'liquid'])
solid = ColumnDataSource(elements[elements['standard state'] == 'solid'])

# Define the output file path
output_file("elements_annotations.html")

# Create the figure object
f = figure()

# adding glyphs
f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Gas', source=gas)

f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Liquid', source=liquid)

f.circle(x="atomic radius", y="boiling point", size='size',
         fill_alpha=0.2, color="color", legend='Solid', source=solid)

# Add axis labels
f.xaxis.axis_label = "Atomic radius"
f.yaxis.axis_label = "Boiling point"

# Calculate the average boiling point for all three groups by dividing the sum by the number of values
gas_average_boil = sum(gas.data['boiling point']) / len(gas.data['boiling point'])
liquid_average_boil = sum(liquid.data['boiling point']) / len(liquid.data['boiling point'])
solid_average_boil = sum(solid.data['boiling point']) / len(solid.data['boiling point'])

# Create three spans
span_gas_average_boil = Span(location=gas_average_boil, dimension='width', line_color='yellow', line_width=2)
span_liquid_average_boil = Span(location=liquid_average_boil, dimension='width', line_color='orange', line_width=2)
span_solid_average_boil = Span(location=solid_average_boil, dimension='width', line_color='red', line_width=2)

# Add spans to the figure
f.add_layout(span_gas_average_boil)
f.add_layout(span_liquid_average_boil)
f.add_layout(span_solid_average_boil)

# Save and show the figure
show(f)