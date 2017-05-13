from spotifymodule import Spotishower
from flask import Flask
app = Flask(__name__)
sp = Spotishower()

@app.route("/callback")
def hello():
    return "Hello World!"

@app.route("/tracks/<string:token>", methods=['GET'])
def tracks(token):
    sp.set_user_token(token)
    return sp.current_user_top_tracks()

if __name__ == "__main__":
    app.run(port=8888)