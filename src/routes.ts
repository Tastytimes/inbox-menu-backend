import express from "express";
const router = express.Router();
import hotelRegistration from "./modules/hotel-registration/router/hotel.registration";
import { userRouter } from "./modules/user-management/router/user.router";
import { inventoryRouter } from "./modules/inventory/router/inventory.route";

router.use("/user-management", userRouter);
router.use("/inventory", inventoryRouter)


export default router;
