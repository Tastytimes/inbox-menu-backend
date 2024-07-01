import express, { Application } from "express";
const app = express();
import globalConfig from "./src/config/global.config";
const PORT = globalConfig.getPort();
import { Server } from 'socket.io';
import { appRouter } from "./src/app";
import { createServer } from 'node:http';

// const server = createServer(app);
const server = createServer(app);
const io = new Server(server, {
  cors: {
    origin: 'http://localhost:4200', // Angular app URL
    methods: ['GET', 'POST']
  }
});
app.use(appRouter);

io.on('connection', (socket) => {
  console.log('a user connected');

  socket.on('disconnect', () => {
    console.log('user disconnected');
  });

  socket.on('message', (msg) => {
    console.log('message: ' + msg);
    io.emit('message', msg);
  });
});
app.listen(PORT, () => {
  console.log("Express server listening on port " + PORT);
});
