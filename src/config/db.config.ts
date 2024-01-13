import globalConfigs from "./global.config";
import mongoose from "mongoose";

const dbConnection = async () => {
  try {
    await mongoose.connect(globalConfigs.getDbUrl());
    console.log("Connected to DB..");
  } catch (err) {
    console.error("Db connection failed..");
  }
};

export default dbConnection;
