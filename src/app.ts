import express, { Application } from "express";
import bodyParser, { json } from "body-parser";
import mongoose from "mongoose";
import cors from "cors";
import indexRoutes from "./routes";
import Environment from "./config/global.config";
import dbConnection from "./config/db.config";

const newApp = express.application;
const app = express();
dbConnection();

app.set("trust proxy", true);
app.use(json());
app.use("/menu-v1", indexRoutes);
app.use(cors())


export { app as appRouter };
