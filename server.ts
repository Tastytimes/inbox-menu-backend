import express, { Application } from "express";
const app = express();
import globalConfig from "./src/config/global.config";
const PORT = globalConfig.getPort();
import { appRouter } from "./src/app";


app.use(appRouter);
app.listen(PORT, () => {
  console.log("Express server listening on port " + PORT);
});
