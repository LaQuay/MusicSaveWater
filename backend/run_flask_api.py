from flask import Flask
from spotify_module import Spotishower
from db_module import DBController

app = Flask(__name__)
sp = Spotishower()


@app.route("/helloworld")
def hello():
    return "Hello World!"


@app.route("/<string:type>/tracks/<string:token>", methods=['GET'])
def tracks(type, token):
    sp.set_user_token(token)
    return sp.current_user_top_tracks()


@app.route("/achievements", methods=['PUT'])
def put_achievement(user_id):
    return


@app.route("/<string:user_id>/achievements", methods=['GET'])
def get_achievements(user_id):
    return

if __name__ == "__main__":
    app.run(port=80,
            host="0.0.0.0")
