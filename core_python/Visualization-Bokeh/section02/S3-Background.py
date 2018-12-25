#Plotting flower species

#Importing libraries
from bokeh.plotting import figure
from bokeh.io import output_file, show
from bokeh.sampledata.iris import flowers

#Define the output file path
output_file("iris.html")

#Create the figure object
f=figure()

#Style the plot area
f.plot_width=1100
f.plot_height=650
f.background_fill_color="olive"
f.background_fill_alpha=0.3
f.border_fill_color = "grey"

f.circle(x=flowers["petal_length"], y=flowers["petal_width"])
#Save and show the figure
show(f)
print(dir(f.axis))
