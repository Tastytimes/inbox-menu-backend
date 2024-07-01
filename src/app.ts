import express, { Application, NextFunction } from "express";
import bodyParser, { json } from "body-parser";
import mongoose from "mongoose";
import cors from "cors";
import indexRoutes from "./routes";
import Environment from "./config/global.config";
import dbConnection from "./config/db.config";
import { Server } from 'socket.io';
import { createServer } from 'node:http';

const newApp = express.application;
const app = express();
const server = createServer(app);
const io = new Server(server, {
  cors: {
    origin: 'http://localhost:4200', // Angular app URL
    methods: ['GET', 'POST']
  }
});
dbConnection();
app.use(cors());
app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin,X-Requested-with,Content-Type,Accept,Authorization"
  );

  if (req.method == "OPTIONS") {
    res.header("Access-Control-Allow-Methods", "PUT,POST,PATCH,DELETE,GET");
    return res.status(200).json({});
  }
  next();
});
app.set("trust proxy", true);
app.use(json());
// app.use("/menu-v1", indexRoutes);
app.use(cors());
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


app.get('/', (req, res) => {
  res.send('Socket.IO Server');
  io.emit('message', 'msg');
});

export { app as appRouter };
