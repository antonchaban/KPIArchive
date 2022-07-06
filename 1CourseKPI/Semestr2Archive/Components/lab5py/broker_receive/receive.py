import pika, sys, os

def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='host.docker.internal'))
    channel = connection.channel()
# host.docker.internal
    channel.queue_declare(queue='lab5')

    def callback(ch, method, properties, body):
        print(" [x] Received %r" % body)
        f = open("log/receive_log.txt", "a")
        f.write(" [x] Received %r \n" % body)
        f.close()

    channel.basic_consume(queue='lab5', on_message_callback=callback, auto_ack=True)

    print(' [*] Waiting for messages. To exit press CTRL+C')
    channel.start_consuming()

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print('Interrupted')
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)