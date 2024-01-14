import express from "express";

import { adminTokenDecode } from "../../../middleware/auth/auth";
import {registration,login,getAllSeller,getSellerById,updateSellerBy  }from "../controller/seller.controller";
const router = express.Router();

router.post('/create',registration);
router.post('/login',login);
router.get('/',getAllSeller);
router.get('/:sellerId',getSellerById );
router.patch('/:sellerId',updateSellerBy );


export {router as SellerRouter}
