#Making a basic Bokeh line graph

#importing Bokeh
from bokeh.plotting import figure
from bokeh.io import output_file, show

#prepare some data
x = [1,2,3,4,5]
y = [6,7,8,9,10]

#prepare the output file
output_file("Line.html")

#create a figure object
f = figure()

#create line plot
#f.line(x,y)
f.circle(x,y)
# f.triangle(x,y)

#write the plot in the figure object
show(f)
