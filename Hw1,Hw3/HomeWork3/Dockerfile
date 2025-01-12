FROM python:3.9-slim
WORKDIR /hw3
COPY . /hw3
RUN pip install --no-cache-dir -r requirements.txt
RUN pip install torch
CMD [ "python", "main.py" ]