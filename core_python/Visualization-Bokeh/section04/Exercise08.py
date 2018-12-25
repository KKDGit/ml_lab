# Tip 1
#
# In the last video lecture, we had to change the text of the labels, so we accessed the text with:
#
# labels.text
#
# and we assigned the value of the select widget to the labels text with labels.text=select.value
#
# In this case we need to access the span location, so we do something like:
#
# span_solid_boil.location=select.value
#
# Tip 2
#
# Note that according to the reference guide for Select widgets the options attribute accepts as value a list of either strings or tuples of strings.
#
# So, you cannot pass something like:
#
# options=[ (min(solid.data['boiling point']), "Solid Minimum Boiling Point "), (etc...), (etc...) ]
#
# You should convert those values to strings:
#
# options=[ (str(min(solid.data['boiling point'])), "Solid Minimum Boiling Point "), (etc...), (etc...) ]
#
# Tip 3
#
# So, select.value is always a string, therefore when in your callback function you should convert it to a float:
#
# float(select.value)
#
#
#
# Solution in the next article


from bokeh.plotting import figure
from bokeh.io import curdoc
from bokeh.sampledata.periodic_table import elements
from bokeh.models import Range1d, PanTool, ResetTool, HoverTool, ColumnDataSource, LabelSet, Select
from bokeh.models.annotations import Span, BoxAnnotation, Label, LabelSet
from bokeh.layouts import layout

# Remove rows with NaN values and then map standard states to colors
elements.dropna(inplace=True)  # if inplace is not set to True the changes are not written to the dataframe
colormap = {'gas': 'yellow', 'liquid': 'orange', 'solid': 'red'}
elements['color'] = [colormap[x] for x in elements['standard state']]
elements['size'] = elements["van der Waals radius"] / 10

# Create three ColumnDataSources for elements of unique standard states
gas = ColumnDataSource(elements[elements['standard state'] == 'gas'])
liquid = ColumnDataSource(elements[elements['standard state'] == 'liquid'])
solid = ColumnDataSource(elements[elements['standard state'] == 'solid'])

# Create the figure object
f = figure()

# adding glyphs
f.circle(x="atomic radius", y="boiling point",
         size='size',
         fill_alpha=0.2, color="color", legend='Gas', source=gas)

f.circle(x="atomic radius", y="boiling point",
         size='size',
         fill_alpha=0.2, color="color", legend='Liquid', source=liquid)

f.circle(x="atomic radius", y="boiling point",
         size='size',
         fill_alpha=0.2, color="color", legend='Solid', source=solid)

# Add axis labels
f.xaxis.axis_label = "Atomic radius"
f.yaxis.axis_label = "Boiling point"

# Calculate the average boiling point for all three groups by dividing the sum by the number of values
gas_average_boil = sum(solid.data['boiling point']) / len(solid.data['boiling point'])
liquid_average_boil = sum(liquid.data['boiling point']) / len(liquid.data['boiling point'])
solid_average_boil = sum(solid.data['boiling point']) / len(solid.data['boiling point'])
solid_min_boil = min(solid.data['boiling point'])
solid_max_boil = max(solid.data['boiling point'])

# Create three spans
span_gas_average_boil = Span(location=gas_average_boil, dimension='width', line_color='yellow', line_width=2)
span_liquid_average_boil = Span(location=liquid_average_boil, dimension='width', line_color='orange', line_width=2)
span_solid_boil = Span(location=solid_average_boil, dimension='width', line_color='red',
                       line_width=2)  # Location for this span will be updated when the Select widget is changed by the user

# Add spans to the figure
f.add_layout(span_gas_average_boil)
f.add_layout(span_liquid_average_boil)
f.add_layout(span_solid_boil)


# Create a function that updates the location attribute value for span_solid_boil span
# Also note that select.value returns values as strings so we need to convert the returned value to float
def update_span(attr, old, new):
    span_solid_boil.location = float(select.value)


# Select widgets expect a list of tuples of strings, so List[Tuple(String, String)], that's why you should convert average, max, and min to strings
options = [(str(solid_average_boil), "Solid Average Boiling Point"),
           (str(solid_min_boil), "Solid Minimum Boiling Point"), (str(solid_max_boil), "Solid Maximum Boiling Point")]

# Create the Select widget
select = Select(title="Select span value", options=options)
select.on_change("value", update_span)

# Add Select widget to layout and then the layout to curdoc
lay_out = layout([[select]])
curdoc().add_root(f)
curdoc().add_root(lay_out)

