import pika
import time
from datetime import datetime


connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='host.docker.internal'))
channel = connection.channel()

channel.queue_declare(queue='lab5')

while(True):
    date = datetime.now()
    str_date = date.strftime("%d/%m/%Y %H:%M:%S")
    channel.basic_publish(exchange='', routing_key='lab5', body=str_date)
    print(" Current Time: " + str_date)
    time.sleep(3)
connection.close()