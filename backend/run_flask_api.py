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


@app.route("/user/<user_id>/showers", methods=['PUT'])
def put_user_shower(user_id):
    #user + type + duration + date
    return


#@app.route("/user/<user_id>/achievements", methods=['PUT'])
#def put_user_achievement(user_id):
#    return


@app.route("/user/<user_id>/achievements", methods=['GET'])
def get_user_achievements(user_id):
    #description + target + progress
    return


@app.route("/contract/<contract_id>/achievements", methods=['GET'])
def get_contract_achievements(contract_id):
    # description + target + progress
    return


@ app.route("/user/<user_id>/showers", methods=['GET'])
def get_user_showers(user_id):
    #type + duration + date
    return


@ app.route("/contract/<contract_id>/showers", methods=['GET'])
def get_contract_showers(contract_id):
    #type + duration + date + user
    return


@ app.route("/contract/<contract_id>/invoices", methods=['GET'])
def get_contract_invoices(contract_id):
    #date + consumption
    return


if __name__ == "__main__":
    app.run(port=80,
            host="0.0.0.0")
