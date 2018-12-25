#Plotting flower species

#Importing libraries
from bokeh.plotting import figure
from bokeh.io import output_file, show
from bokeh.sampledata.iris import flowers
from bokeh.models import Range1d, PanTool, ResetTool, HoverTool

#Define the output file path
output_file("iris.html")

#Create the figure object
f=figure()

#Style the plot area
#Style the plot area
f.plot_width=1100
f.plot_height=650
f.background_fill_color="olive"
f.background_fill_alpha=0.3
f.border_fill_color = (255,250,250)


#Style the title
f.title.text="Iris Morphology"
f.title.text_color="olive"
f.title.text_font="times"
f.title.text_font_size="25px"
f.title.align="center"

#adding glyphs
f.circle(x=flowers["petal_length"], y=flowers["petal_width"])

#Save and show the figure
show(f)
