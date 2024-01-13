import express from "express";

import { adminTokenDecode } from "../../../middleware/auth/auth";
import { createInvController, getAllInventoryList } from "../controller/inventory.controller";

const router = express.Router();

router.post("/create", adminTokenDecode, createInvController);
router.get("/", adminTokenDecode, getAllInventoryList);

export { router as inventoryRouter }