from flask import Flask, jsonify, request
import pickle

model = pickle.load(open('model.pkl', 'rb'))

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/predict', methods=['POST'])
def predict():
    start = int(request.args.get('start'))
    end = int(request.args.get('end'))

    pred_aqidata = model.predict(start=start,end=end)
    return jsonify(pred_aqidata.tolist())

if __name__ == '__main__':
    app.run(debug=True)