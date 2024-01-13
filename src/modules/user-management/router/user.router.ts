import express from "express";
import { getAllUsersController, login, registration, getUserByIdController, updateUserByIdController, resetUserPasswordController, deleteUserByIdController } from "../controller/user.controller";
import { adminTokenDecode } from "../../../middleware/auth/auth";

const router = express.Router();

router.post('/', adminTokenDecode, registration);
router.post('/login', login);
router.get('/all-users', adminTokenDecode, getAllUsersController);
router.get('/:userId', getUserByIdController);
router.put('/:userId', adminTokenDecode, updateUserByIdController);
router.delete("/:userId", adminTokenDecode, deleteUserByIdController);
router.post("/reset-password", adminTokenDecode, resetUserPasswordController);

export { router as userRouter }