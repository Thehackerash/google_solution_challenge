from logging import Logger
from flask import Flask, jsonify, request
import pickle
import pandas as pd

delhi = pickle.load(open('delhi.pkl', 'rb'))
bangalore = pickle.load(open('bangalore.pkl', 'rb'))
lucknow = pickle.load(open('lucknow.pkl', 'rb'))
patna = pickle.load(open('patna.pkl', 'rb'))

# list = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/predict/delhi', methods=['GET'])
def delhi_predict():
    start = int(request.args.get('start'))
    end = int(request.args.get('end'))

    pred_aqidata = delhi.predict(start=start,end=end)
    data = pd.Series(pred_aqidata)
    data_dict = data.to_dict()
    json_data = {}
    for key, value in data_dict.items():
        year = key.year
        month = key.strftime("%B")
        if year not in json_data:
            json_data[year] = {}
        json_data[year][month] = value

    return jsonify(json_data)

@app.route('/predict/bangalore', methods=['GET'])
def bangalore_predict():
    start = int(request.args.get('start'))
    end = int(request.args.get('end'))

    pred_aqidata = bangalore.predict(start=start,end=end)
    data = pd.Series(pred_aqidata)
    data_dict = data.to_dict()
    json_data = {}
    for key, value in data_dict.items():
        year = key.year
        month = key.strftime("%B")
        if year not in json_data:
            json_data[year] = {}
        json_data[year][month] = value

    return jsonify(json_data)

@app.route('/predict/lucknow', methods=['GET'])
def lucknow_predict():
    start = int(request.args.get('start'))
    end = int(request.args.get('end'))

    pred_aqidata = lucknow.predict(start=start,end=end)
    data = pd.Series(pred_aqidata)
    data_dict = data.to_dict()
    json_data = {}
    for key, value in data_dict.items():
        year = key.year
        month = key.strftime("%B")
        if year not in json_data:
            json_data[year] = {}
        json_data[year][month] = value

    return jsonify(json_data)

@app.route('/predict/patna', methods=['GET'])
def patna_predict():
    start = int(request.args.get('start'))
    end = int(request.args.get('end'))

    pred_aqidata = patna.predict(start=start,end=end)
    data = pd.Series(pred_aqidata)
    data_dict = data.to_dict()
    json_data = {}
    for key, value in data_dict.items():
        year = key.year
        month = key.strftime("%B")
        if year not in json_data:
            json_data[year] = {}
        json_data[year][month] = value

    return jsonify(json_data)

if __name__ == '__main__':
    app.run(debug=True)