import express from "express";

import { adminTokenDecode } from "../../../middleware/auth/auth";
import {
  createInvController,
  getAllInvForAdmin,
  getAllInventoryList,
  getSingleInventory,
  updateInventory,
} from "../controller/inventory.controller";

const router = express.Router();

router.post("/create", adminTokenDecode, createInvController);
router.get("/", adminTokenDecode, getAllInventoryList);
router.get("/inv/:invId", getSingleInventory);
router.put("/inv/:invId", adminTokenDecode, updateInventory);
router.get("/all-inv-list", adminTokenDecode, getAllInvForAdmin);

export { router as inventoryRouter };
