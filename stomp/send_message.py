import stomp

# Create a connection to ActiveMQ
conn = stomp.Connection12([('localhost', 61613)], auto_content_length=False) #https://github.com/jasonrbriggs/stomp.py/issues/216
conn.connect(login='admin', passcode='admin', wait=True)

# Send a message to a specific destination (queue)
destination = 'MyFirstQueue'
message = 'Hello, ActiveMQ!'
conn.send(body=message, destination=destination, headers={'persistent' :'true'})

# Disconnect from ActiveMQ
conn.disconnect()
