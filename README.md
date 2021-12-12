# ChatClientMaven
Message exchange between server and client using TCP sockets.
A threaded server that can serve multiple chatting clients.

Build a GUI that allows the user to configure both server and client part of the application (i.e. choosing a client name, IP address to connect to at the client-side, the port number, etc. )
Messages are encrypted using Caesar cipher. (i.e. key can be hardcoded at this stage)

In addition to the requirements of the B grade,
Messages are stored in a database at the server side (i.e. or on the cloud. Please see the A+ section).

A login screen at the client-side. The client sends the user credentials and the server checks them against the saved ones.
Clients can chat with one another while the server keeps track of the messages exchanged.

Messages are sent encrypted using DES/AES. It is fine to manually exchange the key (i.e. just copy the key files on both server and client).
Deploying the server application on EC2.
Using Firestore to save data (i.e. messages).
Develop an instant messaging service that uses the client-server model (i.e. Socket Programming). The functionality is to be elicited from the following rubrics.
![screen](https://github.com/pennyliangzhao/ChatClientMaven/blob/master/image/with%20EC2%20messages.jpg)
