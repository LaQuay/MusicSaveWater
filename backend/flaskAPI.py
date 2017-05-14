from flask import Flask
from flask_mysqldb import MySQL

app = Flask(__name__)
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'root'
app.config['MYSQL_DB'] = 'SaveSound'
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_PASSWORD'] = ''
mysql = MySQL(app)

@app.route("/callback")
def hello():
    return "Hello World!"

@app.route("/db")
def bye():
    cur = mysql.connection.cursor()
    cur.execute('''SELECT user, host FROM mysql.user''')
    rv = cur.fetchall()
    return str(rv)

@app.route("/createDatabase")
def createDatabase():
    cur = mysql.connection.cursor()
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

if __name__ == "__main__":
    app.run(port=8888)