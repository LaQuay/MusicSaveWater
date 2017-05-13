from flask import Flask
app = Flask(__name__)

@app.route("/callback")
def hello():
    return "Hello World!"

if __name__ == "__main__":
    app.run(port=8888)