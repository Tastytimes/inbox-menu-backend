import {healthCheck} from "../controller/hotel-registration.controller";
import express from "express";
const router = express.Router();


router.post("/create", healthCheck);



export default router;


