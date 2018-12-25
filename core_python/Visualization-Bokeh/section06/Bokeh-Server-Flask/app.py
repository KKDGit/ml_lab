#flask app

from flask import Flask, render_template
from datetime import datetime

#instantiate the flask app
app = Flask(__name__)

#create index page function
@app.route("/")
def index():
    return render_template("index.html", current_date=datetime.now())

#run the app
if __name__ == "__main__":
    app.run(debug=True)
