import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense
import joblib

data = pd.DataFrame({
    'date': pd.date_range(start='1/1/2020', periods=100, freq='D'),
    'price': np.random.rand(100) * 100  # Random price data
})


def prepare_data(data, sequence_length=60):
    prices = data['price'].values.reshape(-1, 1)
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaled_data = scaler.fit_transform(prices)

    x, y = [], []
    for i in range(sequence_length, len(scaled_data)):
        x.append(scaled_data[i-sequence_length:i, 0])
        y.append(scaled_data[i, 0])

    x, y = np.array(x), np.array(y)
    x = x.reshape((x.shape[0], x.shape[1], 1))  # Reshape for LSTM
    return x, y, scaler

x_train, y_train, scaler = prepare_data(data)

model = Sequential([
    LSTM(50, return_sequences=True, input_shape=(x_train.shape[1], 1)),
    LSTM(50, return_sequences=False),
    Dense(1)
])

model.compile(optimizer='adam', loss='mean_squared_error')


model.fit(x_train, y_train, epochs=10, batch_size=32)


model.save('stock_price_prediction_model.h5')
joblib.dump(scaler, 'scaler.pkl')

print("Model and scaler saved!")
