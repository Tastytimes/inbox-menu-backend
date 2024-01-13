import { Request, Response } from "express";
import { badRequestError } from "../../../middleware/errorResponse";
import { CustomRequest } from "../../../middleware/auth/auth";
import { Users } from "../../user-management/model/user.model";
import { inventoryValidation } from "../validator/inventory.validator";
import { IInventory, inventory } from "../model/inventory.model";
import { successResponse } from "@agnimura/common";

const createInvController = async (req: Request, res: Response) => {
    try {
        const data: any = req as CustomRequest;
        const id: string = data.token.id;
        let validateData = req.body;
        const validate: any = inventoryValidation(validateData);
        if (validate?.error?.details?.length > 0) return badRequestError('somefields are missing!', validate?.error?.details, res);
        validateData.userId = id;
        const respData: any = new inventory(validateData);
        await respData.save();
        return successResponse(201, "data saved successfully", respData, res);

    } catch (err) {
        return badRequestError("something went wrong!", err, res);
    }
};

const getAllInventoryList = async (req: Request, res: Response) => {
    try {
        const data: any = req as CustomRequest;
        const id: string = data.token.id;
        const details = await Users.findById({ _id: id });
        if (!details) return badRequestError('User not found', {}, res);
        const respData = await inventory.findById({ userId: details.id });
        return successResponse(200, "list", respData, res);
    } catch (err) {
        return badRequestError("something went wrong!", err, res);
    }
};

export { createInvController, getAllInventoryList };