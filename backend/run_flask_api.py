from flask import Flask, request, jsonify
from spotify_module import Spotishower
from flask_mysqldb import MySQL
from db_module import DBController

app = Flask(__name__)
sp = Spotishower()

app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'root'
app.config['MYSQL_DB'] = 'SaveSound'
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_PASSWORD'] = ''
mysql = MySQL(app)


@app.route("/db")
def bye():
    cur = mysql.connection.cursor()
    cur.execute('''SELECT user, host FROM mysql.user''')
    rv = cur.fetchall()
    return str(rv)

#user + type + duration + date

@app.route("/createDatabase")
def createDatabase():
    cur = mysql.connection.cursor()
    cur.execute('''CREATE TABLE IF NOT EXISTS users (
                      id VARCHAR(32),
                      PRIMARY KEY ( id )
    );''')
    cur.execute('''CREATE TABLE IF NOT EXISTS showers (
                      user_id VARCHAR(32),
                      shower_type VARCHAR(32),
                      duration VARCHAR(32),
                      date_time VARCHAR(32),
                      PRIMARY KEY ( user_id, date_time)
    );''')
    cur.execute('''CREATE TABLE IF NOT EXISTS contratos (
                      id VARCHAR(32),
                      municipio VARCHAR(64),
                      distrito VARCHAR(64),
                      PRIMARY KEY ( id )
    );''')
    cur.execute('''CREATE TABLE IF NOT EXISTS consumos (
                      id INT NOT NULL AUTO_INCREMENT,
                      fecha VARCHAR(16),
                      consumo INT,
                      contrato_id VARCHAR(32),
                      PRIMARY KEY (id),
                      FOREIGN KEY (contrato_id) REFERENCES contratos(id)
    );''')
    rv = cur.fetchall()
    return "Database created"

@app.route("/initDatabase")
def initDatabase():
    query = "INSERT INTO contratos(id, municipio, distrito) VALUES"
    cur = mysql.connection.cursor()
    f = open("contratos.csv", 'rb')
    for ind, row in enumerate(f):
        str_split = str(row).split(',')
        municipio = str_split[0].split('"')[1]
        distrito = str_split[1].split('"')[1]
        id_contrato = str_split[4].split('"')[1]

        if ind is not 0: query += ','
        query += '(' + id_contrato + ', ' + municipio + ', ' + distrito + ')'

    query += ';'
    cur.execute(query)
    rv = cur.fetchall()
    return "Yeah"


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
