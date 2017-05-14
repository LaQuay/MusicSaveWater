import MySQLdb
db = MySQLdb.connect(host="localhost",    # your host, usually localhost
                     user="root",         # your username
                     passwd="",  # your password
                     db="SaveSound")        # name of the data base


# app.config['MYSQL_USER'] = 'root'
# app.config['MYSQL_DB'] = 'SaveSound'
# app.config['MYSQL_HOST'] = 'localhost'
# app.config['MYSQL_PASSWORD'] = ''

query = ''
f = open("contratos.csv", 'rb')
for ind, row in enumerate(f):
    if ind is 0: continue
    str_split = str(row).split(',')
    municipio = str_split[0].split('"')[1]
    distrito = str_split[1].split('"')[1]
    id_contrato = str_split[4].split('"')[1]
    query += "('" + id_contrato + "', '" + municipio + "', '" + distrito + "')"

    if ind%1000 is 0:
        cur = db.cursor()
        q = "INSERT INTO contratos(id, municipio, distrito) VALUES" + query + ';'
        cur.execute(q)
        rv = cur.fetchall()
        query = ''
    else:
        query += ','

cur = db.cursor()
q = "INSERT INTO contratos(id, municipio, distrito) VALUES" + query[:-1] + ';'
cur.execute(q)
rv = cur.fetchall()


####


query = ''
f = open("consumos.csv", 'rb')
for ind, row in enumerate(f):
    if ind is 0: continue
    str_split = str(row).split(',')
    fecha = str_split[0].split("'")[1]
    consumo = str_split[1]
    contrato_id = str_split[2].split('"')[1]
    query += "('" + fecha + "', " + consumo + ", '" + contrato_id + "')"

    if ind%1000 is 0:
        cur = db.cursor()
        q = "INSERT INTO consumos(fecha, consumo, contrato_id) VALUES" + query + ';'
        #print(q)
        cur.execute(q)
        rv = cur.fetchall()
        query = ''
    else:
        query += ','

cur = db.cursor()
q = "INSERT INTO consumos(fecha, consumo, contrato_id) VALUES" + query[:-1] + ';'
cur.execute(q)
rv = cur.fetchall()


db.close()