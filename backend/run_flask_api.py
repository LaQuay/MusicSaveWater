from flask import Flask, request, jsonify
from spotify_module import Spotishower
from db_module import DBController

app = Flask(__name__)
sp = Spotishower()


@app.route("/helloworld")
def hello():
    return "A ella le gusta la gasoliiinaa! XD"


@app.route("/<type>/tracks/<token>", methods=['GET'])
def tracks(type, token):
    sp.set_user_token(token)
    return jsonify(sp.current_user_top_tracks())


@app.route("/user/<user_id>/achievements", methods=['PUT'])
def put_user_achievement(user_id):
    return


@app.route("/contract/<contract_id>/achievements", methods=['PUT'])
def put_contract_achievement(contract_id):
    return


@app.route("/user/<user_id>/achievements", methods=['GET'])
def get_user_achievements(user_id):
    return


@app.route("/contract/<contract_id>/achievements", methods=['GET'])
def get_contract_achievements(contract_id):
    return


@app.route("/user/<user_id>/showers", methods=['PUT'])
def put_user_shower(user_id):
    return


if __name__ == "__main__":
    app.run(port=80,
            host="0.0.0.0")
